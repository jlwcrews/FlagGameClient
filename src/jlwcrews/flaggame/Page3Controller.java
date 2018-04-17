package jlwcrews.flaggame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.*;

import java.io.*;
import java.util.Base64;
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
    private String difficulty = null;
    private String currentCountry = null;
    private String currentFlag = null;
    private Image flagImage = null;
    private int score_current;
    private int score_max;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FlagNetClient fnc = new FlagNetClient(oneButton.getText());
        flags = fnc.start();
        playGame();
    }

    public void setDifficulty(String difficulty){
        this.oneButton.setText(difficulty);

    }

    private void playGame() {
        Random random = new Random();
        usedFlags = new ArrayList<>();
        while (usedFlags.size() < flags.size()) {
            int r = random.nextInt(flags.size());
            currentCountry = flags.get(r).getCountry();
            currentFlag = flags.get(r).getFlag();
            if (!usedFlags.contains(currentFlag)) {
                usedFlags.add(currentCountry);
                setButtonText(currentCountry);
                try {
                    flagImage = new Image(decodeToImage(currentFlag));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                if (flagImage != null) {
                    imageBox.setImage(flagImage);
                }
                if(checkAnswer(Button currentCountry)){

                }

            }
        }
    }

    private InputStream decodeToImage(String imageString) throws IOException{

        InputStream in = Base64.getDecoder().wrap(new ByteArrayInputStream(imageString.getBytes()));
        return in;
    }

    private boolean checkAnswer(Button button){
        if(button.getText().equals(currentCountry)){
            return true;
        }
        return false;
    }

    private void endGame(){

    }
}
