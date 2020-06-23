/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Entidad.Domicilio;
import Entidad.Factura;
import Entidad.Factura_has_libro;
import dao.ControlBd;
import Entidad.Libro;
import dao.SQL_Sentencias;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
import javafx.scene.layout.Pane;
import javafx.stage.Window;

public class MainMenuController implements Initializable {


    @FXML
    private TextField textSearchHome;

    private ControlBd control;
    @FXML
    private ComboBox<String> menu_filter;

    private ObservableList<String> filtrosLibros = FXCollections.observableArrayList(
            "Nombre", "Editorial", "Autor", "ISBN");

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
    private AnchorPane anchorEnvio;

    @FXML
    private Button btnVerMasDetallesLibro;
    @FXML
    private Button login;
    @FXML
    private Button registro;
    @FXML
    private Button button_search;
    @FXML
    private Button carrito;
    private CarritoController carritoController;
    @FXML
    private TextField txtDireccionEnvio;
    @FXML
    private TextField txtDatosAdicionales;
    @FXML
    private CheckBox cboxNormal;
    @FXML
    private CheckBox cBoxPremium;
    @FXML
    private CheckBox cBoxDia;
    private int lastIdDomicilio,lastIdFactura;
    @FXML
    private TextField txtValorTotal;
    private float valorDomicilio;
    private String tipoEntrega;

    //Creación Popup Login//
    public void popupLogin(final Stage stage) throws IOException {
        final Popup popup = new Popup();

        Button show = new Button("Show");
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.show(stage);
            }
        });

        Button hide = new Button("Hide");
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    //Creación popup Registro//
    public void popupRegistro(final Stage stage) throws IOException {
        final Popup popup = new Popup();

        Button show = new Button("Show");
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.show(stage);
            }
        });

        Button hide = new Button("Hide");
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Registro.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    //Creación Popup Carrito//
    public void popupCarrito(final Stage stage) throws IOException {
        final Popup popup = new Popup();

        Button show = new Button("Show");
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.show(stage);
            }
        });

        Button hide = new Button("Hide");
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/Carrito.fxml"));
        Parent root = (Parent) loader.load();
        CarritoController controller = loader.getController();
        this.carritoController=controller;
        controller.setMainMenuController(this);
        controller.llenarTablaCart();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    //Método  para llamar a popup Login//
    @FXML
    public void loginButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/MainMenu.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);

        popupLogin(stage);
    }

    //Método  para llamar a popup Registro//   
    @FXML
    public void registroButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/MainMenu.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);

        popupRegistro(stage);
    }

    //Método para llamar a popup Carrito//    
    @FXML
    public void carritoButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("gui/Carrito.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);

        popupCarrito(stage);
    }

    public Object[][] searchByIsbn() {
        Object[][] tabla = control.getLibrosBy("isbn", textSearchHome.getText());
        int cont = 1;
        for (Object[] fila : tabla) {
            System.out.print("Libro #" + (cont++) + "//   ");
            for (Object dato : fila) {
                if (dato != null) {
                    System.out.print("//  " + dato + "// ");
                }
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
        btnVerMasDetallesLibro.setDisable(true);
        llenarTablaBusquedaLibros(menu_filter.getValue(), textSearchHome.getText());
    }

    @FXML
    public void onClicTxtBusquedaLibro(KeyEvent event) {
        llenarTablaBusquedaLibros(menuFilterBusquedaLibros.getValue(), textSearchBusquedaLibros.getText());
    }

    public void llenarTablaBusquedaLibros(String filtro, String busqueda) {
        Object[][] libros = control.getLibrosBy(filtro, busqueda);
        for (Object[] currentLibro : libros) {
            boolean flag = false;
            for (Object object : currentLibro) {
                if (object == null) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                Libro libro = new Libro(currentLibro[0].toString(), currentLibro[1].toString(),
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

    @FXML
    public void Detail_Search(MouseEvent event) {
        btnVerMasDetallesLibro.setDisable(false);
    }

    @FXML
    public void onClicVolverBusquedaLibros(ActionEvent event) {
        anchorHome.toFront();
    }

    @FXML
    public void onClicDetalles(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("gui/DetalleLibro.fxml"));
                Parent root=loader.load();
        DetalleLibroController controller= loader.getController();
        controller.setLibroSeleccionado(tableBusquedaLibros.getSelectionModel().getSelectedItem());
        controller.setMainMenuController(this);
        controller.llenarDetallesLibro();
        Stage stage = new Stage();
        controller.setStage(stage);
        stage.setScene(new Scene(root));
        stage.show();
    }

 
    

    public void onClicVolverDetalles(ActionEvent event) {
        anchorBusquedaLibros.toFront();
        btnVerMasDetallesLibro.setDisable(true);
    }

    public void onClicIrEnvio() {
        anchorEnvio.toFront();
    }

    @FXML
    public void volverABusquedaLibros(ActionEvent event) {
        anchorBusquedaLibros.toFront();
    }

    public void popupDetalleCarrito(Stage stage) throws IOException {
        final Popup popup = new Popup();

        Button show = new Button("Show");
        show.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.show(stage);
            }
        });

        Button hide = new Button("Hide");
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.hide();
            }
        });
        DetalleLibroController controller= new DetalleLibroController(
                tableBusquedaLibros.getSelectionModel().getSelectedItem());

    }

    public void ActualizarTablaCarrito() {
        if (this.carritoController != null) {
            carritoController.llenarTablaCart();
        }
    }

    @FXML
    public void onClicFinalizarCompra(ActionEvent event) {
        if (txtDireccionEnvio.getText().length() > 0 && txtDatosAdicionales.getText().length() > 0
                && (cboxNormal.isSelected() || cBoxPremium.isSelected() || cboxNormal.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, finalizarCompra(), ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("Mostrar Resumen compra");
            }
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR, "Favor llene todos los datos", ButtonType.OK);
            alert.showAndWait();
        }
    }
    
    public String finalizarCompra(){
        try{
            insertarDomicilio();
        insertarFactura();
        insertarFactura_has_libro();
            return "Compra Exitosa";
        }catch(Exception e){
            System.err.print(e);
            return "Fallo al generar la compra";
        }
    }

    public void insertarFactura_has_libro() {
        for (Libro libro : AirBook.cart) {
            Factura_has_libro f= new Factura_has_libro(lastIdFactura,libro.getIsbn(),Integer.parseInt(libro.getExistencia()));
            control.setFactura_has_Libro(f);
        }
    }

    public void insertarFactura() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calobj = Calendar.getInstance();
        Factura factura = new Factura(df.format(calobj.getTime()), 
                carritoController.getSubtotal().getText(),
                lastIdDomicilio, "8516548", AirBook.usu.getCedula());
        lastIdFactura=control.setFactura(factura);
    }

    public void insertarDomicilio() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calobj = Calendar.getInstance();
       
        
        Domicilio domicilio= new Domicilio(txtDireccionEnvio.getText(), df.format(calobj.getTime()),
                valorDomicilio, txtDatosAdicionales.getText(), tipoEntrega);
        lastIdDomicilio=control.setDomicilio(domicilio);
    }
    
     @FXML
    private void handleOpxionesEntrega(ActionEvent event) {
        if(event.getSource().equals(cboxNormal)){
            valorDomicilio=5000;
            tipoEntrega="normal";
            cBoxPremium.setSelected(false);
            cBoxDia.setSelected(false);
            actualizarValorTotal();
        }else if(event.getSource().equals(cBoxPremium)){
            valorDomicilio=10000;
            tipoEntrega="premium";
            cboxNormal.setSelected(false);
            cBoxDia.setSelected(false);
            actualizarValorTotal();
        }else{
            valorDomicilio=15000;
            tipoEntrega="mismo dia";
            cboxNormal.setSelected(false);
            cBoxPremium.setSelected(false);
            actualizarValorTotal();
        }
    }

    public void actualizarValorTotal() {
        setTxtValorTotal(String.valueOf(Integer.parseInt(carritoController.getSubtotal().getText())+valorDomicilio));
    }

    public TextField getTxtValorTotal() {
        return txtValorTotal;
    }

    public void setTxtValorTotal(String txtValorTotal) {
        this.txtValorTotal.setText(txtValorTotal);
    }

    public ControlBd getControl() {
        return control;
    }

    public void setControl(ControlBd control) {
        this.control = control;
    }

    
   
    
   
}
