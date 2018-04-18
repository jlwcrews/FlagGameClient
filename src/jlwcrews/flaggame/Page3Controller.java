package jlwcrews.flaggame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.beans.EventHandler;
import java.io.*;
import java.util.*;
import java.net.URL;

public class Page3Controller implements Initializable{

    @FXML
    private ImageView imageBox;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button oneButton;

    @FXML
    private void handleOneButton(){
        checkAnswer(oneButton.getText().toString());
    }

    @FXML
    private Button twoButton;

    @FXML
    private void handleTwoButton(){
        checkAnswer(twoButton.getText().toString());
    }

    @FXML
    private Button threeButton;

    @FXML
    private void handleThreeButton(){
        checkAnswer(threeButton.getText().toString());
    }

    @FXML
    private Button fourButton;

    @FXML
    private void handleFourButton(){
        checkAnswer(fourButton.getText().toString());
    }

    @FXML
    private Label difficultyLabel;

    private ArrayList<Flag> flags;
    private ArrayList<String> usedFlags;
    private ArrayList<String> buttonNames;
    private ObjectInputStream imageStream;
    private String difficulty = null;
    private String currentCountry = null;
    private String currentFlag = null;
    private Image flagImage = null;
    private int score;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        difficulty = GUI.getDifficulty();
        difficultyLabel.setText("Difficulty: " + difficulty);
        FlagNetClient fnc = new FlagNetClient(difficulty);
        flags = fnc.start();
        usedFlags = new ArrayList<>();
        buttonNames = new ArrayList<>();
        playGame();
    }
    private void playGame() {
        if (usedFlags.size() < flags.size()) {
            getNextFlag();
        }else{
            endGame();
        }
    }
    private void getNextFlag(){
        Random random = new Random();
        int r = random.nextInt(flags.size());
        currentCountry = flags.get(r).getCountry();
        currentFlag = flags.get(r).getFlag();
        if(!usedFlags.contains(currentCountry)){
            try {
                flagImage = new Image(decodeToImage(currentFlag));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (flagImage != null) {
                imageBox.setImage(flagImage);
            }
            usedFlags.add(currentCountry);
            setButtons();
        }else{
            getNextFlag();
        }
    }

    private InputStream decodeToImage(String imageString) throws IOException{
        InputStream in = Base64.getDecoder().wrap(new ByteArrayInputStream(imageString.getBytes()));
        return in;
    }

    private void setButtons(){
        ArrayList<String> usedButtonNames = new ArrayList<>();
        buttonNames.clear();
        buttonNames.add(currentCountry);
        while(buttonNames.size() < 4){
            buttonNames.add(generateButtonNames());
        }
        Random rng = new Random();
        int r = rng.nextInt(4 );
        oneButton.setText(buttonNames.get(r));
        buttonNames.remove(r);
        r = rng.nextInt(3);
        twoButton.setText(buttonNames.get(r));
        buttonNames.remove(r);
        r = rng.nextInt(2);
        threeButton.setText(buttonNames.get(r));
        buttonNames.remove(r);
        fourButton.setText(buttonNames.get(0));
    }

    private String generateButtonNames(){
        Random rng = new Random();
        while(true){
            int r = rng.nextInt(4) + 1;
            if(!buttonNames.contains(flags.get(r).getCountry())){
                return(flags.get(r).getCountry());
            }
        }
    }

    private void checkAnswer(String buttonText){
        if(buttonText.equalsIgnoreCase(currentCountry)){
            answerAlert("Right answer!", buttonText + " was the correct answer!");
            score++;
        }else{
            answerAlert("Wrong answer!", "Sorry, you chose " + buttonText + " but the correct answer was " + currentCountry);
        }
        playGame();
    }

    protected void answerAlert(String title, String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(text);
        Optional<ButtonType> result = alert.showAndWait();

    }

    private void endGame(){
        GUI.setScore(score);
        GUI.setMaxScore(flags.size());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("page4.fxml"));
        try{
            loader.load();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        Page4Controller p4c = loader.getController();
        loader.getController();
        Parent p = loader.getRoot();
        Scene page4scene = new Scene(p);
        Stage stage = (Stage)borderPane.getScene().getWindow();
        stage.setScene(page4scene);
        stage.show();

    }
}
