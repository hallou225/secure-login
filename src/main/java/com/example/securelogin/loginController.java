package com.example.securelogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class loginController {
//    Private Variables
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    DataSingleton data = DataSingleton.getInstance();

    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException {
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false){
            if (validateLogin()){
                data.setUserName(usernameTextField.getText());
                Utils.changeScene(event, "home.fxml", "Zion - Home", 600, 400, null);
            }
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    public boolean validateLogin() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin ="SELECT count(1) FROM users WHERE username = '"+ usernameTextField.getText() +"' AND password = '"+ passwordPasswordField.getText() +"'";

        Statement statement = connectDB.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyLogin);
        while(queryResult.next()){
            if(queryResult.getInt(1) == 1){
                loginMessageLabel.setText("You are logged in!");
            } else {
                loginMessageLabel.setText("Invalid Login. Please try again.");
                return false;
            }
        }
        return true;
    }


    // Sign up
    public void signupButtonOnAction(ActionEvent event) throws IOException {
        System.out.println("You clicked on the Sign up button!");
        Utils.changeScene(event, "register.fxml", "Zion - Sign Up", 600, 650, null);
    }
} // end of login controller