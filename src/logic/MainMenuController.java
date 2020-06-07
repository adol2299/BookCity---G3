/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import dao.ControlBd;
import Entidad.Libro;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;


public class MainMenuController implements Initializable {
    
    
    @FXML
    private Button login;
    @FXML
    private Button registro;
    @FXML
    private TextField textSearchHome;
    @FXML
    private Button button_search;
    
    private ControlBd control= new  ControlBd("root", "");
    @FXML
    private ComboBox<String> menu_filter;

    private ObservableList<String> filtrosLibros=FXCollections.observableArrayList(
    "Nombre","Editorial","Autor","ISBN");

    @FXML
    private AnchorPane anchorBusquedaLibros;
    private ArrayList<Libro> arrayLibros = new ArrayList<>();
    private ObservableList<Libro> listaLibros;
    @FXML
    private TableColumn<Libro, String> columnNombreBusquedaLibro;
    @FXML
    private TableColumn<Libro, String> columnAutorBusquedaLibro;
    @FXML
    private TableColumn<Libro, String> columnEditorialBusquedaLibro;
    @FXML
    private TableColumn<Libro, String> columnIsbnBusquedaLibro;
    @FXML
    private TableView<Libro> tableBusquedaLibros;
    @FXML
    private AnchorPane anchorHome;
    @FXML
    private TextField textSearchBusquedaLibros;
    @FXML
    private ComboBox<String> menuFilterBusquedaLibros;
    @FXML
    private AnchorPane anchorBook;
    int flag2,flag;

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

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Login.fxml"));         
            
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

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Registro.fxml"));         
            
        Scene scene = new Scene (root);      
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    //Método  para llamar a popup Login//
    
    @FXML
        public void loginButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/MainMenu.fxml"));         
            
        Scene scene = new Scene (root);      
        
        Stage stage= new Stage();
        stage.setScene(scene);

        popupLogin(stage);
         }
        
    //Método  para llamar a popup Registro//   
        
    @FXML
        public void registroButtonPushed(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/MainMenu.fxml"));         
            
        Scene scene = new Scene (root);      
        
        Stage stage= new Stage();
        stage.setScene(scene);

        popupRegistro(stage);
         }
    
        
        
        
     
       
    public Object[][] searchByIsbn(){
        Object[][] tabla=control.getLibrosByIsbn(textSearchHome.getText());
        int cont=1;
        for(Object[] fila:tabla){
            System.out.print("Libro #"+(cont++)+"//   ");
            for(Object dato: fila){
                if(dato!=null) System.out.print("//  "+dato+"// ");
            }
            System.out.println("");
        }
        return tabla;
    }

    public String getMenu_filter() {
        return menu_filter.getValue();
    }

   
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        anchorHome.toFront();
        menu_filter.setItems(filtrosLibros);
        menu_filter.setValue("Nombre");
        menuFilterBusquedaLibros.setItems(filtrosLibros);
        menuFilterBusquedaLibros.setValue("Nombre");
    }

    @FXML
    public void onClicSearchLibroHome(ActionEvent event) {
        anchorBusquedaLibros.toFront();
        llenarTablaBusquedaLibros(menu_filter.getValue(),textSearchHome.getText());
        
    }

    @FXML
    public void onClicTxtBusquedaLibro(KeyEvent event) {
        llenarTablaBusquedaLibros(menuFilterBusquedaLibros.getValue(),textSearchBusquedaLibros.getText());
    }

    public void llenarTablaBusquedaLibros(String filtro,String busqueda) {
        Object[][] libros=control.getLibrosBy(filtro,busqueda);
        for (Object[] currentLibro : libros) {
            boolean flag=false;
            for (Object object : currentLibro) {
                if(object==null){
                    flag=true;
                    break;
                }
            }
            if(flag==false){
            Libro libro=new Libro(currentLibro[0].toString(), currentLibro[1].toString(),
                    currentLibro[2].toString(), currentLibro[3].toString(),
                    currentLibro[4].toString(), currentLibro[5].toString(),
                    currentLibro[6].toString(), currentLibro[7].toString());
            arrayLibros.add(libro);
            }
        }
        listaLibros = FXCollections.observableArrayList(arrayLibros);
        columnNombreBusquedaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("nombre"));
        columnAutorBusquedaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
        columnEditorialBusquedaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        columnIsbnBusquedaLibro.setCellValueFactory(new PropertyValueFactory<Libro, String>("isbn"));
        tableBusquedaLibros.setItems(listaLibros);
        listaLibros.removeAll();
        arrayLibros.removeAll(listaLibros);
    }
    
    public void Detail_Search(MouseEvent event){
        System.out.println("clicked on " + tableBusquedaLibros.getSelectionModel().getSelectedItem());
         flag2=1;
    }
    
    

    @FXML
    public void onClicVolverBusquedaLibros(ActionEvent event) {
        anchorHome.toFront();
    }
    
    public void onClicDetalles(MouseEvent event) {
        flag=1;
        libroDetalles(flag,flag2);  
    }
    
    public void libroDetalles(int flag,int flag2){
        if(flag==1&&flag2==1){
            anchorBook.toFront();
        }
    }
    
    public void onClicVolverDetalles(ActionEvent event) {
        anchorBusquedaLibros.toFront();
        flag=0;
        flag2=0;
    }
}
