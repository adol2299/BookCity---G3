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
    
    public boolean RegisterRestrictions() {
        
        String usuario = TextUsuario.getText();
        String cedula = TextCedula.getText();
        String password = TextPassw.getText();
        
        if(usuario.isEmpty()) {                 
            Alert alert = new Alert(AlertType.ERROR, "Debe llenar el campo de Nombre", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
            return false;
        }
        if(cedula.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Debe llenar el campo de Cédula", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
            return false;
        }
        if(password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Debe llenar el campo de Contraseña", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }   
            return false;
        }    

        if(!longNombre(usuario)) {
            Alert alert = new Alert(AlertType.ERROR, "Longitud de nombre incorrecta", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            } 
            return false;
        }
        if(!longCedula(cedula)) {
            Alert alert = new Alert(AlertType.ERROR, "Longitud de cédula incorrecta", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
            return false;
        }
        if(!longPassw(password)) {
            Alert alert = new Alert(AlertType.ERROR, "Longitud de contraseña incorrecta", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
            return false;
        }
        
        if(numCedula(cedula))
        {   Alert alert = new Alert(AlertType.ERROR, "Solo se admiten números en la cédula", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK)
                alert.close();
            return false;
        }
        
        if(txtNombre(usuario))
        {   Alert alert = new Alert(AlertType.ERROR, "No se admiten números en el Nombre", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK)
                alert.close();
            return false;
        }
        
        if(testRegEx(password)) {
            Alert alert = new Alert(AlertType.ERROR, "No se aceptan caracteres especiales en la contraseña", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
            return false;
        }
        return true;
    }
    
    public void RegistrarButtonPushed(ActionEvent event) throws IOException{
        Registrar();
    }
    
    public void Registrar()
    {
        Usuario usu = new Usuario(TextCedula.getText(),TextUsuario.getText(),TextPassw.getText());
        SQL_Sentencias sql = new SQL_Sentencias();
        if(RegisterRestrictions())
        {   if(sql.insertarUsuario(usu))
            {   Alert alert = new Alert(AlertType.INFORMATION, "Usuario registrado exitosamente", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) 
                   alert.close(); 
            }
            else
            {   Alert alert = new Alert(AlertType.ERROR, "Error al registrar usuario, por favor revise sus datos", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) 
                   alert.close(); 
            }
        }
    }
    
    public boolean txtNombre(String nombre)
    {   String n;
        String[] array = nombre.split("");
        for (String tab : array) 
        {   if(tab.matches("\\d"))
                return true;
        }
        return false;
    }
    
    public boolean numCedula(String cedula)
    {   double n = 0;
        try
        {   n = Double.parseDouble(cedula); }
        catch(Exception ex)
        {   return true;    }
        return false;
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

    public boolean testRegEx(String password)
    {   // expresión regular que revisa si tiene alguno de los siguientes caracteres
        String REG_EXP = "\\_+|\\¿+|\\?+|\\°+|\\¬+|\\|+|\\!+|\\#+|\\$+|" +
        "\\%+|\\&+|\\+|\\=+|\\’+|\\¡+|\\++|\\*+|\\~+|\\[+|\\]" +
        "+|\\{+|\\}+|\\^+|\\<+|\\>+|\\\"+ ";
        Pattern pattern = Pattern.compile(REG_EXP);
        Matcher matcher = pattern.matcher(password);
        //retorna true si tiene alguno de los caracteres anteriores o false si no tiene ninguno
        return matcher.find(); 
    }
    
}
