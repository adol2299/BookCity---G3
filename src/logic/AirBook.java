/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.IOException;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import java.text.*;
import java.util.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.stage.*; 
import javafx.geometry.*;

/**
 *
 * @author Familia Ortega
 */
public class AirBook extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));         
            
        Scene scene = new Scene (root);   
        scene.getStylesheets().add("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
