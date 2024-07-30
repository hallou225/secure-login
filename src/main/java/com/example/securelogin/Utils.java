package com.example.securelogin;

import java.io.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;


public class Utils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, int width, int height, String username) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(Utils.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

}
