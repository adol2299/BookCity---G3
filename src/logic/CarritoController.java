package logic;

import Entidad.Libro;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class CarritoController {
    private Stage stage;
    @FXML
    private TableView<Libro> tableCart;
    @FXML
    private TableColumn<Libro, String> columnNombre;
    @FXML
    private TableColumn<Libro, String> columnAutor;
    @FXML
    private TableColumn<Libro, String> columnPrecio;
    @FXML
    private TableColumn<Libro, String> columnCantidad;
    
    private ObservableList<Libro> listaLibros;
    @FXML
    private AnchorPane anchorMain;
    
    private MainMenuController mainMenuController;

    @FXML
    private void onClicDetalles(MouseEvent event) {
        anchorMain.getScene().getWindow().hide();  
        mainMenuController.onClicIrEnvio();
    }
    
    public void llenarTablaCart() {        
        listaLibros = FXCollections.observableArrayList(AirBook.cart);
        columnNombre.setCellValueFactory(new PropertyValueFactory<Libro, String>("Nombre"));
        columnAutor.setCellValueFactory(new PropertyValueFactory<Libro, String>("Autor"));
        columnCantidad.setCellValueFactory(new PropertyValueFactory<Libro, String>("existencia"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<Libro, String>("Precio"));
        tableCart.setItems(listaLibros);
        listaLibros.removeAll();
    }
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public AnchorPane getAnchorMain() {
        return anchorMain;
    }

    public void setAnchorMain(AnchorPane anchorMain) {
        this.anchorMain = anchorMain;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    
    public void onClicVolver(ActionEvent event) throws IOException {
        llenarTablaCart();
    }
}
