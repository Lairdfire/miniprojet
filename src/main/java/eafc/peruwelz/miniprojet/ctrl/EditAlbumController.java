package eafc.peruwelz.miniprojet.ctrl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class EditAlbumController {

    @FXML
    private DatePicker date_DateSelector;
    @FXML
    private TextField txt_EditAlbumTitle;
    @FXML
    private Button btn_EditAlbumCancel;

    @FXML
    private void btn_AlbumEditOnEdit() {
        String albumTitle = txt_EditAlbumTitle.getText();
        LocalDate releaseDate = date_DateSelector.getValue();

        if (releaseDate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertissement");
            alert.setHeaderText("No date selected");
            alert.setContentText("Select a valid date");
            alert.showAndWait();
        } else {
            int releaseDateYear = releaseDate.getYear();
            System.out.println("Album title : " + albumTitle);
            System.out.println("Album releaseDateYear : " + releaseDateYear);
        }
    }

    @FXML
    private void btn_AlbumEditOnCancel() {
        Stage stage = (Stage) btn_EditAlbumCancel.getScene().getWindow();
        stage.close();
    }
}
