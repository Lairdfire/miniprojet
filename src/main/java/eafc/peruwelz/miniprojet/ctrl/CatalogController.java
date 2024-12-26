package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.Utils.WindowConfig;
import eafc.peruwelz.miniprojet.Utils.WindowHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class CatalogController {

    @FXML
    private VBox catalogView ;
    @FXML
    private Button btn_CatalogPlay ;
    @FXML
    private Button btn_CatalogEdit ;
    @FXML
    private Button btn_CatalogDelete ;

    @FXML
    private void CatalogOnPlay() {

    }

    @FXML
    private void CatalogOnAdd() {
        WindowHelper.openWindow(WindowConfig.ADD_TRACKS_VIEW, WindowConfig.ADD_TRACKS_TITLE, (Stage) catalogView.getScene().getWindow());
    }

    @FXML
    private void CatalogOnEdit() {
        WindowHelper.openWindow(WindowConfig.EDIT_TRACK_VIEW, WindowConfig.EDIT_TRACK_TITLE, (Stage) catalogView.getScene().getWindow());
    }

    @FXML
    private void CatalogOnDelete() {
        System.out.println("CatalogOnDelete");
    }
}
