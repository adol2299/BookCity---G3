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
    
    public void RegisterRestrictions(ActionEvent event) {
        
                 String usuario = TextUsuario.getText();
                 String cedula = TextCedula.getText();
                 String password = TextPassw.getText();
                if(TextUsuario.getText().isEmpty()) {                 
                    Alert alert = new Alert(AlertType.ERROR, "Debe llenar el campo de Nombre", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }
                if(TextCedula.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR, "Debe llenar el campo de Cédula", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }
                if(TextPassw.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.ERROR, "Debe llenar el campo de Contraseña", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }    
                }    
                
                if(!longNombre(TextUsuario.getText())) {
                    Alert alert = new Alert(AlertType.ERROR, "Longitud de nombre incorrecta", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }      
            }
                if(!longCedula(TextCedula.getText())) {
                    Alert alert = new Alert(AlertType.ERROR, "Longitud de cédula incorrecta", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
            }
                if(!longPassw(TextPassw.getText())) {
                    Alert alert = new Alert(AlertType.ERROR, "Longitud de contraseña incorrecta", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
            }
                if(!getSpecialPassw(TextPassw.getText())) {
                    Alert alert = new Alert(AlertType.ERROR, "No se aceptan caracteres especiales en la contraseña", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
            }
    }
    
    public void RegistrarButtonPushed(ActionEvent event) throws IOException{
        Usuario usu = new Usuario(TextCedula.getText(),TextUsuario.getText(),TextPassw.getText());
        SQL_Sentencias sql = new SQL_Sentencias();
        RegisterRestrictions(event);        
        if(sql.insertarUsuario(usu))
            System.out.println("Usuario registrado exitosamente");
        else
            System.out.println("Error al registrar usuario, por favor revise sus datos");
    }
    
    public boolean longNombre(String usuario){
    return(usuario.length()>1 && usuario.length()<=16);
    }
    public boolean longCedula(String cedula){
    return(cedula.length()>4 && cedula.length()<=13);
    }
    public boolean longPassw(String password){
    return(password.length()>6 && password.length()<=13);
    }
    
    public boolean getSpecialPassw(String password) {
     Pattern p = Pattern.compile("[^A-Za-z0-9]");
     Matcher m = p.matcher(password);
     boolean b = m.matches();
     return(b);
 }
    
}
