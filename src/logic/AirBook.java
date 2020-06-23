/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Entidad.Factura;
import Entidad.Libro;
import Entidad.Usuario;
import dao.ControlBd;
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
    
    public static Usuario usu = new Usuario();
    public static ArrayList<Libro> cart = new ArrayList<>();
    
    @Override
    public void start(Stage stage) throws IOException {
        ControlBd control = new ControlBd("root", "");
        if (control.testConexion()) {
            FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("gui/MainMenu.fxml"));
            Parent root = loader.load();
            MainMenuController controller=loader.getController();
            controller.setControl(control);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/Styles/TextStyle.css");
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert= new Alert(Alert.AlertType.ERROR,"Error en la conexión"
                    + "con la base de datos", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
