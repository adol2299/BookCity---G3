package logic;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;



public class CarritoController {

    @FXML
    private void onClicSeguirCompra(ActionEvent event) {
        MainMenuController.onClicIrEnvio(event);
    }
}
