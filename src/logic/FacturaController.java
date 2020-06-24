/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Entidad.Libro;
import Entidad.LibroComprado;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author juan8
 */
public class FacturaController {
 
    @FXML
    private TextField direccionLib;
    @FXML
    private TextField total;
    @FXML
    private TextField ciudadLib;
    @FXML
    private TextField telefonoLib;
    @FXML
    private TextField nombreCliente;
    @FXML
    private TextField direccionCliente;
    @FXML
    private TextField facturaid;
    @FXML
    private TextField fechaFactura;
    @FXML
    private TextField cedula;
    @FXML
    private TextField entrega;
    @FXML
    private TableView<LibroComprado> tabla;
    @FXML
    private TableColumn<LibroComprado, String> descripcion;
    @FXML
    private TableColumn<LibroComprado, String> cantidad;
    @FXML
    private TableColumn<LibroComprado, String> precio;
    @FXML
    private TableColumn<LibroComprado, String> subtotal;
    private ObservableList<LibroComprado> listaLibros;

    public TextField getTotal() {
        return total;
    }

    public void setTotal(TextField total) {
        this.total = total;
    }
    
    public TextField getDireccionLib() {
        return direccionLib;
    }

    public void setDireccionLib(TextField direccionLib) {
        this.direccionLib = direccionLib;
    }

    public TextField getCiudadLib() {
        return ciudadLib;
    }

    public void setCiudadLib(TextField ciudadLib) {
        this.ciudadLib = ciudadLib;
    }

    public TextField getTelefonoLib() {
        return telefonoLib;
    }

    public void setTelefonoLib(TextField telefonoLib) {
        this.telefonoLib = telefonoLib;
    }

    public TextField getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(TextField nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public TextField getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(TextField direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public TextField getFacturaid() {
        return facturaid;
    }

    public void setFacturaid(TextField facturaid) {
        this.facturaid = facturaid;
    }

    public TextField getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(TextField fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public TextField getCedula() {
        return cedula;
    }

    public void setCedula(TextField cedula) {
        this.cedula = cedula;
    }

    public TextField getEntrega() {
        return entrega;
    }

    public void setEntrega(TextField entrega) {
        this.entrega = entrega;
    }
    
    public void llenarTablaCart() {        
        listaLibros = FXCollections.observableArrayList(calcularSubtotal());
        descripcion.setCellValueFactory(new PropertyValueFactory<LibroComprado, String>("nombre"));
        cantidad.setCellValueFactory(new PropertyValueFactory<LibroComprado, String>("cantidad"));
        precio.setCellValueFactory(new PropertyValueFactory<LibroComprado, String>("precio"));
        subtotal.setCellValueFactory(new PropertyValueFactory<LibroComprado, String>("subtotal"));
        tabla.setItems(listaLibros);
        listaLibros.removeAll();
    }
    public ArrayList<LibroComprado> calcularSubtotal()
    {   ArrayList<LibroComprado> lista = new ArrayList<>();
        for(Libro l : AirBook.cart)
        {
            LibroComprado lc = new LibroComprado();
            lc.setCantidad(l.getExistencia());
            lc.setIsbn(l.getIsbn());
            lc.setNombre(l.getNombre());
            lc.setPrecio(l.getPrecio());
            int cant = Integer.parseInt(l.getExistencia());
            int precio = Integer.parseInt(l.getPrecio());
            int subtotal = cant*precio;
            lc.setSubtotal(""+subtotal);
            lista.add(lc);
        }
        return lista;
    }
    
}
