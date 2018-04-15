package jlwcrews.flaggameclient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.io.EOFException;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Page2Controller implements Initializable{

    @FXML
    private RadioButton radioButtonEasy;

    @FXML
    private RadioButton radioButtonMedium;

    @FXML
    private RadioButton radioButtonHard;

    @FXML
    private Label nameLabel;

    @FXML
    private ToggleGroup difficultyToggle;

    @FXML
    private Button startGameButton;

    private String difficultySelected = "";
    private ArrayList<Flag> flags;

    public void initialize(URL url, ResourceBundle rb){
        this.radioButtonEasy.setUserData("easy");
        this.radioButtonMedium.setUserData("medium");
        this.radioButtonHard.setUserData("hard");
    }

    public void difficultyChanged(){
        if(difficultyToggle.getSelectedToggle().equals(radioButtonEasy))
            difficultySelected = "easy";
        if(difficultyToggle.getSelectedToggle().equals(radioButtonMedium))
            difficultySelected = "medium";
        if(difficultyToggle.getSelectedToggle().equals(radioButtonHard))
            difficultySelected = "hard";
    }

    public void setNameLabel(String userName){
        this.nameLabel.setText(userName+":");
    }

    public void startGame(){
        flags = new ArrayList<>();
        FlagNetClient fnc = new FlagNetClient(difficultySelected);

    }
}
