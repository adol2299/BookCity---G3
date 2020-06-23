package logic;

import Entidad.Libro;
import dao.ControlBd;
import java.io.IOException;
import java.util.Iterator;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static logic.AirBook.usu;



public class CarritoController {
    private Stage stage;
    @FXML
    private TextField subtotal;
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
    @FXML
    private AnchorPane anchorMain;
    private ObservableList<Libro> listaLibros;
    
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
        subtotal.setText(""+calcularTotal());
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

    @FXML
    private void onClicVolver(ActionEvent event) throws IOException {
         anchorMain.getScene().getWindow().hide();
    }

    @FXML
    private void onClicConfirmar(ActionEvent event) throws IOException {
        if (validarStockDisponible()) {
            if (AirBook.usu.getNombre() == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Debes iniciar sesión primero", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
            } else {
                mainMenuController.setTxtValorTotal(subtotal.getText());
                anchorMain.getScene().getWindow().hide();
                mainMenuController.onClicIrEnvio();
            }
        } 
        
    }
    
    
    public boolean validarStockDisponible() {
        for (Libro libro : AirBook.cart) {
            ControlBd control=mainMenuController.getControl();
            int librosRestantes=control.getExistenciaLibro(libro)-Integer.parseInt(libro.getExistencia());
            if(librosRestantes<0){
                String salida="Disculpa, nos hacen faltan "+Math.abs(librosRestantes)+
                        " ejemplares de "+libro.getNombre();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, salida, ButtonType.OK);
                alert.setHeaderText("");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }
    
    
    private int calcularTotal()
    {   int total = 0;
        Iterator it = AirBook.cart.iterator();
        while(it.hasNext())
        {   Libro l = (Libro) it.next();
            int cant = Integer.parseInt(l.getExistencia());
            int value = Integer.parseInt(l.getPrecio());
            total += cant*value;
        }
        return total;
    }
    
    @FXML
    private void onClicEliminar(ActionEvent event) throws IOException {
        Libro libroSeleccionado = tableCart.getSelectionModel().getSelectedItem();
        AirBook.cart.remove(libroSeleccionado);
        llenarTablaCart();
    }

    public TextField getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(TextField subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
