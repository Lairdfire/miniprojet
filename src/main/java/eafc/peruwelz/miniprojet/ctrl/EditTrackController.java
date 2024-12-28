package eafc.peruwelz.miniprojet.ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;

@Controller
public class EditTrackController {

    @FXML
    private void EditTracksOnEdit() {
        System.out.println("EditTracksOnEdit");
    }

    @FXML
    private void EditTracksOnCancel() {
        System.out.println("EditTracksOnCancel");
    }
}
