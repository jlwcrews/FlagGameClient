package jlwcrews.flaggameclient;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FlagNetClient {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket socket;
    private String ipAddress="localhost";
    private String gameDifficulty;

    public FlagNetClient(String gameDifficulty){
        this.gameDifficulty = gameDifficulty;
        start();
    }

    private void start(){
        try{
            connectToServer();
            setupStreams();
            whileConnected();
        }catch(EOFException eof){

        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            closeConnection();
        }
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
    private void whileConnected() throws IOException{
        try{
            output.writeObject(gameDifficulty);
            output.flush();
        }catch(IOException ioe){
            ioe.printStackTrace();
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


