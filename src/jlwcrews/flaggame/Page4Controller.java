package jlwcrews.flaggame;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//controller for page 4
public class Page4Controller implements Initializable{

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button exitGameButton;

    @FXML
    private void handleExitGameButton(){
        Platform.exit();
    }

    @FXML
    private Button playAgainButton;

    @FXML
    private void handlePlayAgainButton(){
        startNewGame();
    }

    @FXML
    private Label scoreLabel;

    //displays the username, score, max score, and chosen difficulty
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scoreLabel.setText(GUI.getUsername() + ", you scored " + GUI.getScore() + " out of a possible " + GUI.getMaxScore() + " on "
                + GUI.getDifficulty() + "!");
    }

    //if the player chooses to play again, resets global variables and loads the first scene again
    private void startNewGame() {
        GUI.setDifficulty(null);
        GUI.setMaxScore(0);
        GUI.setScore(0);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("page1.fxml"));
        try{
            loader.load();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        Page1Controller p1c = loader.getController();
        loader.getController();
        Parent p = loader.getRoot();
        Scene page1scene = new Scene(p);
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.setScene(page1scene);
        stage.show();

    }
}
