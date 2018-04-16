package jlwcrews.flaggame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Page3Controller implements Initializable{

    @FXML
    private Button oneButton;

    @FXML
    private Button threeButton;

    @FXML
    private ImageView imageBox;

    @FXML
    private Button twoButton;

    @FXML
    private Button fourButton;

    private ArrayList<Flag> flags;
    private ArrayList<String> usedFlags;
    private ObjectInputStream imageStream;
    private String difficulty;

    public Page3Controller(){
        /*FlagNetClient fnc = new FlagNetClient(difficulty);
        flags = fnc.start();
        for(Flag flag : flags){
            System.out.println(flag.getCountry());
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //playGame();
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    private void playGame() {

        /*Random random = new Random(flags.size());
        String currentCountry = null;
        String currentFlag = null;
        Image flagImage = null;
        while (usedFlags.size() < flags.size()) {
            int r = random.nextInt();
            currentCountry = flags.get(r).getCountry();
            currentFlag = flags.get(r).getFlag();
            try {
                flagImage = new Image(decodeToImage(currentFlag));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (!usedFlags.contains(currentFlag)) {
                usedFlags.add(currentCountry);
                oneButton.setText(currentCountry);
                if (flagImage != null) {
                    imageBox.setImage(flagImage);
                }
            }
            usedFlags.add(currentCountry);
        }*/
    }

    private InputStream decodeToImage(String imageString) throws IOException{

        InputStream in = Base64.getDecoder().wrap(new ByteArrayInputStream(imageString.getBytes()));
        return in;
    }

    private void endGame(){

    }
}
