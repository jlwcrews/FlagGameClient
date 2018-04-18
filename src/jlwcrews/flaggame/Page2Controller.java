package jlwcrews.flaggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
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
    public ArrayList<Flag> flags;

    public void initialize(URL url, ResourceBundle rb){
        this.nameLabel.setText(GUI.getUsername() + ":");
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

    public void startGame(ActionEvent event) throws IOException{
        GUI.setDifficulty(difficultySelected);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("page3.fxml"));
        try{
            loader.load();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        Page3Controller p3c = loader.getController();
        loader.getController();
        Parent p = loader.getRoot();
        Scene page3scene = new Scene(p);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(page3scene);
        stage.show();
    }

}
