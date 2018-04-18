package jlwcrews.flaggame;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//handles the connection to the game server
public class FlagNetClient {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket socket;
    private String ipAddress=GUI.getIpAddress();
    private String gameDifficulty;
    public ArrayList<Flag> flags;

    public FlagNetClient(String gameDifficulty){
        this.gameDifficulty = gameDifficulty;
    }

    public ArrayList<Flag> start(){
        try{
            connectToServer();
            setupStreams();
            sendDifficulty();
            getFlags();
        }catch(EOFException eof){

        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            closeConnection();
        }
        return flags;
    }

    //connect to flag server
    private void connectToServer() throws IOException{
        socket = new Socket(InetAddress.getByName(ipAddress),9898);
    }

    //set up streams to send and receive
    private void setupStreams() throws IOException{
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
    }

    //send game mode and receives flags for the game
    private void sendDifficulty() throws IOException{
        try{
            output.writeObject(gameDifficulty);
            output.flush();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    //read the serialized ArrayList<Flag> from the server
    private void getFlags() throws IOException{
        try{
            flags = (ArrayList<Flag>) input.readObject();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(ClassNotFoundException cnfe){
            System.out.println("Class not found");
        }
    }

    //close the streams and sockets
    private void closeConnection(){
        try{
            output.close();
            input.close();
            socket.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}


