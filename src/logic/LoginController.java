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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 *
 * @author juan8
 */
public class LoginController {
    @FXML
    private TextField TextPassw;
    @FXML
    private TextField TextCedula;  
    
    public void LoginButtonPushed(ActionEvent event) throws IOException{
        login();
    }
    
    public void login()
    {   Usuario usu = new Usuario(TextCedula.getText(),TextPassw.getText());
        SQL_Sentencias sql = new SQL_Sentencias();
        if(sql.Login(usu)>0)
        {   Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bienvenido", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) 
               alert.close(); 
        }
        else
        {   Alert alert = new Alert(Alert.AlertType.ERROR, "Datos incorrectos", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) 
               alert.close();
        }
    }
}
