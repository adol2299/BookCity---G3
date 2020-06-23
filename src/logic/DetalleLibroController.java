/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Entidad.Libro;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author david
 */
public class DetalleLibroController implements Initializable {

    @FXML
    private AnchorPane anchorBook;
    @FXML
    private TextField tituloDetalleLibro;
    @FXML
    private TextField autorDetalleLibro;
    @FXML
    private TextField editorialDetalleLibro;
    @FXML
    private TextField anoDetalleLibro;
    @FXML
    private TextField isbnDetalleLibro;
    @FXML
    private TextField precioDetalleLibro;
    @FXML
    private Button btnAgregarAlCarrito;
    @FXML
    private ComboBox<String> cboxNumeroCopias;

    private Libro libroSeleccionado;
    private Stage stage;
    private MainMenuController mainMenuController;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void onClicVolverDetalles(ActionEvent event) {
    }

    @FXML
    public void onClicAddCart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, addToCart(), ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            mainMenuController.ActualizarTablaCarrito();
            alert.close();
            this.stage.hide();
        }
    }
    
    public String addToCart() {
        try {
            Libro l = new Libro();
            l.setIsbn(isbnDetalleLibro.getText());
            l.setNombre(tituloDetalleLibro.getText());
            l.setEditorial(editorialDetalleLibro.getText());
            l.setAutor(autorDetalleLibro.getText());
            l.setAno_publicacion(anoDetalleLibro.getText());
            l.setPrecio(precioDetalleLibro.getText());
            l.setExistencia(cboxNumeroCopias.getValue());
            l.setEstado("Disponible");
            AirBook.cart.add(l);
            return "Libro agregado";
        } catch (Exception ex) {
            return "Error al agregar el libro";
        }
    }
    public void llenarDetallesLibro() {
        tituloDetalleLibro.setText(libroSeleccionado.getNombre());
        autorDetalleLibro.setText(libroSeleccionado.getAutor());
        editorialDetalleLibro.setText(libroSeleccionado.getEditorial());
        anoDetalleLibro.setText(libroSeleccionado.getAno_publicacion());
        isbnDetalleLibro.setText(libroSeleccionado.getIsbn());
        precioDetalleLibro.setText(libroSeleccionado.getPrecio());
        ArrayList<String> ar = new ArrayList<>();
        if (Integer.parseInt(libroSeleccionado.getExistencia()) > 0) {
            btnAgregarAlCarrito.setDisable(false);
            cboxNumeroCopias.setValue("1");
            for (int i = 0; i < Integer.parseInt(libroSeleccionado.getExistencia()); i++) {
                int j = i + 1;
                ar.add(String.valueOf(j));
            }
            ObservableList<String> numeroCopias = FXCollections.observableArrayList(ar);
            cboxNumeroCopias.setItems(numeroCopias);
            cboxNumeroCopias.setDisable(false);
        } else {
            cboxNumeroCopias.setValue("Sin stock");
            cboxNumeroCopias.setDisable(true);
            btnAgregarAlCarrito.setDisable(true);
        }
    }

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public DetalleLibroController(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public DetalleLibroController() {
    }

    public void setStage(Stage stage) {
        this.stage=stage;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
    
  

    
}
