package com.example.securelogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainController {
//    Private Variables
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

//    ActionEvent Functions
    public void loginButtonOnAction(ActionEvent event) {
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false){
            //loginMessageLabel.setText("You try to login with " + usernameTextField.getText());
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin ="SELECT count(1) FROM users WHERE username = '"+ usernameTextField.getText() +"' AND password = '"+ passwordPasswordField.getText() +"'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("You are logged in!");
                } else {
                    loginMessageLabel.setText("Invalid Login. Please try again.");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}