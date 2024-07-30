package com.example.securelogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.sql.ResultSet;

public class registerController implements Initializable {

    @FXML
    private Label registrationMessageSuccessfull;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;


    @FXML
    private Label firstnameValidator;
    @FXML
    private Label lastnameValidator;
    @FXML
    private Label usernameValidator;
    @FXML
    private Label passwordValidator;
    @FXML
    private Label confirmPasswordValidator;

    DataSingleton data = DataSingleton.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

        public void registerButtonOnAction(ActionEvent event){
            boolean valid = true;
            // validate Firstname
            if(firstnameTextField.getText().isEmpty()){
                firstnameValidator.setText("Please enter your first name!");
                valid = false;
            } else {firstnameValidator.setText("");}

            if(lastnameTextField.getText().isEmpty()){
                lastnameValidator.setText("Please enter your last name!");
                valid = false;
            } else {lastnameValidator.setText("");}

            if(usernameTextField.getText().isEmpty()){
                usernameValidator.setText("Please enter your username!");
                valid = false;
            } else {usernameValidator.setText("");}

            if (passwordPasswordField.getText().isEmpty()){
                passwordValidator.setText("Please enter your password!");
                valid = false;
            } else {passwordValidator.setText("");}


            if (passwordPasswordField.getText().equals(confirmPasswordField.getText())){
                if (confirmPasswordField.getText().isEmpty()){
                    confirmPasswordValidator.setText("Please enter your password!");
                    valid = false;
                } else { confirmPasswordValidator.setText("");}
            } else {
                confirmPasswordValidator.setText("Passwords do not match!");
                valid = false;
            }

            if (valid){
                registerUser(event);
            }
        }
        public void registerUser(ActionEvent event){
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String firstname = firstnameTextField.getText();
            String lastname = lastnameTextField.getText();
            String username = usernameTextField.getText();
            String password = passwordPasswordField.getText();
//            INSERT INTO users (Firstname, Lastname, Username, Password, favActor) Values ('Henri', 'Allou', 'hallou', 'hallou', 'Keanu Reeves');
//            String insertFields = "INSERT INTO users (Firstname, Lastname, Username, Password, favActor) Values ('";
//            String insertValues = firstname + "', '" + lastname + "', '" + username + "', '" + password + "', '" + favActor + "');";

            String verifyLogin ="SELECT count(1) FROM users WHERE username = '"+ usernameTextField.getText() +"'";
            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);
                while(queryResult.next()){
                    if(queryResult.getInt(1) == 1){
                        usernameValidator.setText("\'" + username + "\'" + " username is already taken!");
                        System.out.println("Username is already taken!");
                        return;
                    } else {
                        usernameValidator.setText("");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            String insertFields = "INSERT INTO users (Firstname, Lastname, Username, Password) Values ('";
            String insertValues = firstname + "', '" + lastname + "', '" + username + "', '" + password + "');";
            String insertToRegister = insertFields + insertValues;
            try {
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                registrationMessageSuccessfull.setText("User has been registered Successfully!");
                data.setUserName(usernameTextField.getText());
                firstnameValidator.setText("");
                lastnameValidator.setText("");
                usernameValidator.setText("");
                passwordValidator.setText("");
                confirmPasswordValidator.setText("");
                Utils.changeScene(event, "home.fxml", "Zion - Home", 600, 400, usernameTextField.getText());

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        public void usernameTextFieldOnAction(ActionEvent event){
            System.out.println(usernameTextField.getText());
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            String username = usernameTextField.getText();
            String verifyLogin ="SELECT count(1) FROM users WHERE username = '"+ usernameTextField.getText() +"'";
            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);
                while(queryResult.next()){
                    if(queryResult.getInt(1) == 1){
                        usernameValidator.setText("This username is already taken!");
                    } else {
                        usernameValidator.setText("");
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        public void loginButtonOnAction(ActionEvent event) throws IOException {
            Utils.changeScene(event, "main.fxml","Zion - Login", 600, 450, usernameTextField.getText());
        }



} // end
