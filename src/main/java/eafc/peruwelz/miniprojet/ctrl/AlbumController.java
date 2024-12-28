package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.Utils.WindowConfig;
import eafc.peruwelz.miniprojet.Utils.WindowHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


@Controller
public class AlbumController {

    @FXML
    private VBox albumView;

    @FXML
    private Button btn_AlbumDelete, btn_AlbumEdit, btn_AlbumPLay ;

    @FXML
    private void AlbumOnDelete() {
        System.out.println("AlbumOnDelete");
    }

    @FXML
    private void AlbumOnEdit() {
        WindowHelper.openWindow(WindowConfig.EDIT_ALBUM_VIEW, WindowConfig.EDIT_ALBUM_TITLE, (Stage) albumView.getScene().getWindow());
    }

    @FXML
    private void AlbumOnPlay() {
        System.out.println("AlbumOnPlay");
    }



}
