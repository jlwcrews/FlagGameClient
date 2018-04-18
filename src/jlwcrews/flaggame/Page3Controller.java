package jlwcrews.flaggame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.net.URL;

//controller for the game proper
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
        //gets the difficulty set in the previous page
        difficulty = GUI.getDifficulty();
        difficultyLabel.setText("Difficulty: " + difficulty);
        //fires up a new instance of FlagNetClient, and passes the selected difficulty
        FlagNetClient fnc = new FlagNetClient(difficulty);
        //FlagNetClient returns an ArrayList<Flag>
        flags = fnc.start();
        usedFlags = new ArrayList<>();
        buttonNames = new ArrayList<>();
        playGame();
    }
    //as long as there are flags the player hasn't seen yet, keep going
    private void playGame() {
        if (usedFlags.size() < flags.size()) {
            getNextFlag();
        }else{
            endGame();
        }
    }

    //pulls the next random flag from the flags arraylist
    //making sure not to show duplicates

    private void getNextFlag(){
        Random random = new Random();
        int r = random.nextInt(flags.size());
        currentCountry = flags.get(r).getCountry();
        currentFlag = flags.get(r).getFlag();
        if(!usedFlags.contains(currentCountry)){
            try {
                //the flags are stored as a base64 string, so need to be converted back to png files
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

    //decodes the base64 string to an image
    private InputStream decodeToImage(String imageString) throws IOException{
        InputStream in = Base64.getDecoder().wrap(new ByteArrayInputStream(imageString.getBytes()));
        return in;
    }

    //adds text to the four buttons on the screen, making sure that one is the
    //proper answer, and the other three are randomly selected
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

    //returns a random country name to use as button text
    private String generateButtonNames(){
        Random rng = new Random();
        while(true){
            int r = rng.nextInt(4) + 1;
            if(!buttonNames.contains(flags.get(r).getCountry())){
                return(flags.get(r).getCountry());
            }
        }
    }

    //checks to see if the user got the right answer
    private void checkAnswer(String buttonText){
        if(buttonText.equalsIgnoreCase(currentCountry)){
            answerAlert("Right answer!", buttonText + " was the correct answer!");
            score++;
        }else{
            answerAlert("Wrong answer!", "Sorry, you chose " + buttonText + " but the correct answer was " + currentCountry);
        }
        playGame();
    }

    //the alert window that pops up after you answer
    protected void answerAlert(String title, String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(text);
        Optional<ButtonType> result = alert.showAndWait();

    }

    //once we've used all the flags, sets the score, then loads the final screen
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
