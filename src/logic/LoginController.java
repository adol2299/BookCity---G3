/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.SQL_Sentencias;
import Entidad.Usuario;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author juan8
 */
public class LoginController {

    @FXML
    private TextField TextPassw;
    @FXML
    private TextField TextCedula;
    private MainMenuController mainMenuController;
    
    public void LoginButtonPushed(ActionEvent event) throws IOException {
        Usuario usu = new Usuario(TextCedula.getText(), TextPassw.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, login(usu), ButtonType.OK);
        alert.showAndWait();
        if (login(usu).equals("Bienvenido")) {
            alert.close();
            mainMenuController.getBtnLogin().setVisible(false);
            mainMenuController.getBtnRegistro().setVisible(false);
            mainMenuController.getBtnCerrarSesion().setVisible(true);
            mainMenuController.getBtnCerrarSesion().toFront();
        }
        TextPassw.getScene().getWindow().hide();
        //Se abre el scene correspondiente al admin en caso de que el usuario que inicie sesión sea admin
        if (AirBook.usu.isAdministrador()) {
            FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("gui/MenuAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/Styles/TextStyle.css");
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            mainMenuController.hide();
        }
    }

    public String login(Usuario usu) {
        SQL_Sentencias sql = new SQL_Sentencias();
        AirBook.usu = sql.Login(usu);
        if (!AirBook.usu.getNombre().equals("")) {
            return "Bienvenido";
        } else {
            return "Datos incorrectos";
        }
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
    
    
}
