package jlwcrews.flaggameclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Page1Controller {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button continueButton;

    @FXML
    private Label nameLabel;


    public void continueButtonClicked(ActionEvent event) throws IOException{

        String userName = nameTextField.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("page2.fxml"));
        try{
            loader.load();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        Page2Controller p2c = loader.getController();
        p2c.setNameLabel(userName);
        Parent p = loader.getRoot();
        Scene page2scene = new Scene(p);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(page2scene);
        stage.show();
    }

}
