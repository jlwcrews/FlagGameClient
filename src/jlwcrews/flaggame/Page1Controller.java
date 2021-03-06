package jlwcrews.flaggame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//controller for the first fxml page
public class Page1Controller implements Initializable{

    @FXML
    private Label ipLabel;

    @FXML
    private TextField ipTextField;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField nameTextField;

    //when you press enter in the Name box, it sets focus to the IP address box
    @FXML
    private void textFieldOnEnter(){
        ipTextField.requestFocus();
    }

    @FXML
    private Button continueButton;

    @FXML
    private Label nameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //checks to see if you have played a previous game, and pre-fills the name and IP address
        nameTextField.setText(GUI.getUsername());
        ipTextField.setText(GUI.getIpAddress());
    }

    //launches page 2
    public void continueButtonClicked() throws IOException{
        String userName = nameTextField.getText();
        GUI.setUsername(userName);
        GUI.setIpAddress(ipTextField.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("page2.fxml"));
        try{
            loader.load();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        Page2Controller p2c = loader.getController();
        Parent p = loader.getRoot();
        Scene page2scene = new Scene(p);
        Stage stage = (Stage)(Stage)gridPane.getScene().getWindow();
        stage.setScene(page2scene);
        stage.show();
    }

}
