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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
       
    public void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
           Alert alert = new Alert(alertType);
             alert.setTitle(title);
             alert.setHeaderText(null);
             alert.setContentText(message);
             alert.initOwner(owner);
             alert.show();
                }
    
    public String RegisterRestrictions(Usuario usu) {
        String usuario = usu.getNombre();
        String cedula = usu.getCedula();
        String password = usu.getContrasena();
        Validation val = new Validation();
        
        if(usuario.isEmpty())            
            return "Debe llenar el campo de Nombre";
        if(cedula.isEmpty())
            return "Debe llenar el campo de Cédula";
        if(password.isEmpty())  
            return "Debe llenar el campo de Contraseña";
        if(!val.longNombre(usuario))
            return "Longitud de nombre incorrecta";
        if(!val.longCedula(cedula))
            return "Longitud de cédula incorrecta";
        if(!val.longPassw(password))
            return "Longitud de contraseña incorrecta";       
        if(val.numericOnly(cedula))
            return "Solo se admiten números en la cédula";       
        if(val.textOnly(usuario))
            return "No se admiten números en el Nombre";
        if(val.testRegExPass(password))
            return "No se aceptan caracteres especiales en la contraseña";
        
        return "Correcto";
    }
    
    public void RegistrarButtonPushed(ActionEvent event) throws IOException{   
        Usuario usu = new Usuario(TextCedula.getText(),TextUsuario.getText(),TextPassw.getText());
        Alert alert = new Alert(AlertType.INFORMATION, Registrar(usu), ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) 
                   alert.close();
    }
    
    public String Registrar(Usuario usu)
    {   SQL_Sentencias sql = new SQL_Sentencias();
        String test = RegisterRestrictions(usu);
        //se hace la validación de formatos de texto
        if(test.equals("Correcto"))
        {   //se intenta registrar usuario en db después de pasar por las validaciones de formato
            if(sql.insertarUsuario(usu))
                return "Usuario registrado exitosamente";
            else
                return "Error al registrar usuario, por favor revise sus datos";
        }
        else
            return test;
    }
    
}
