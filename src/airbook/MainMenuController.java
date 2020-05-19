/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airbook;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
    
    @FXML
    private Button login;
    @FXML
    private Button registro;
    @FXML
    private TextField text_search;
    @FXML
    private TextField text_lleg;
    @FXML
    private Button button_search;
    //login
    @FXML
    private TextField TextUsuario;
    @FXML
    private TextField TextPassw;
    @FXML
    private Button InicioSesion;

    //Creación popup Login//
    
    public void popupLogin(final Stage stage) throws IOException {         
    final Popup popup = new Popup(); 
  
    Button show = new Button("Show");
    show.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        popup.show(stage);
      }
    });

    Button hide = new Button("Hide");
    hide.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        popup.hide();
      }
    });

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));         
            
        Scene scene = new Scene (root);      
        
        stage.setScene(scene);
        stage.show();
    }
    
    //Creación popup Registro//
    
   public void popupRegistro(final Stage stage) throws IOException {         
    final Popup popup = new Popup(); 
  
    Button show = new Button("Show");
    show.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        popup.show(stage);
      }
    });

    Button hide = new Button("Hide");
    hide.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        popup.hide();
      }
    });

        Parent root = FXMLLoader.load(getClass().getResource("Registro.fxml"));         
            
        Scene scene = new Scene (root);      
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    //Método  para llamar a popup Login//
    
        public void loginButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));         
            
        Scene scene = new Scene (root);      
        
        Stage stage= new Stage();
        stage.setScene(scene);

        popupLogin(stage);
         }
        
    //Método  para llamar a popup Registro//   
        
        public void registroButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));         
            
        Scene scene = new Scene (root);      
        
        Stage stage= new Stage();
        stage.setScene(scene);

        popupRegistro(stage);
         }
    
        
       public void inicioSesionButtonPushed(ActionEvent inicio) throws IOException{      
        InicioSesion.setOnAction(e ->{
            try {
                System.out.println(TextUsuario.getText());
                System.out.println(TextPassw.getText());
                Parent inicioSesionParent = FXMLLoader.load(getClass().getResource("User_Menu.fxml"));
                Scene inicioSesionScene = new Scene (inicioSesionParent);
                
                
                Stage window = new Stage();
                window .setScene(inicioSesionScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }        
        });   
    } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
