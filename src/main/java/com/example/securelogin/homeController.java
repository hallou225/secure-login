package com.example.securelogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class homeController implements Initializable {
    @FXML
    private Button logoutButton;

    @FXML
    private Label welcomeMsgLabel;

    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWelcomeMsg(data.getUserName());
    }

    // Logout
    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        System.out.println("You clicked on the Logout up button!");
        Utils.changeScene(event, "Main.fxml", "Zion - Login", 600, 450, null);
    }
    public void setWelcomeMsg(String username){
        welcomeMsgLabel.setText("Welcome " +  username + "!");
    }
} // end of home controller