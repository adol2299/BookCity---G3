/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.SQL_Sentencias;
import dao.Usuario;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author juan8
 */
public class RegistroController {
    @FXML
    private TextField TextUsuario;
    @FXML
    private TextField TextPassw;
    @FXML
    private TextField TextCedula;   
    
    public void RegistrarButtonPushed(ActionEvent event) throws IOException{
        Usuario usu = new Usuario(TextCedula.getText(),TextUsuario.getText(),TextPassw.getText());
        SQL_Sentencias sql = new SQL_Sentencias();
        if(sql.insertarUsuario(usu))
            System.out.println("Usuario registrado exitosamente");
        else
            System.out.println("Error al registrar usuario, por favor revise sus datos");
    }
}
