package logic;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class CarritoController {
    private Stage stage;
    @FXML
    private AnchorPane anchorMain;
    private MainMenuController mainMenuController;

    @FXML
    private void onClicDetalles(MouseEvent event) {
        anchorMain.getScene().getWindow().hide();  
        mainMenuController.onClicIrEnvio();
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
    private void onCLicVolver(ActionEvent event) {
        anchorMain.getScene().getWindow().hide();
    }

    
}
