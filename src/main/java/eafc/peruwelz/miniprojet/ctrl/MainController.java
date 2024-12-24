package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.MiniprojetApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import eafc.peruwelz.miniprojet.Utils.WindowConfig;

import java.io.IOException;

@Controller
public class MainController {

    private final ApplicationContext context;

    @Autowired
    public MainController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    private MenuItem menuItemAlbums;
    @FXML
    private MenuItem menuItemPlaylists;
    @FXML
    private MenuItem menuItemCatalog;
    @FXML
    private MenuItem menuItemAddSong;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private VBox mainView;

    @FXML
    public void initialize() {

    }

    @FXML
    private void onShowAlbums() {
        System.out.println("onShowAlbums");
    }

    @FXML
    private void onShowCatalog() {
        System.out.println("onShowCatalog");
    }

    @FXML
    private void onClose() {
        System.out.println("onClose");
    }

    @FXML
    private void onClickPreviousTrack() {
        System.out.println("onClickPreviousTrack");
    }

    @FXML
    private void onClickPlayTrack() {
        System.out.println("onClickPlayTrack");
    }

    @FXML
    private void onClickPauseTrack() {
        System.out.println("onClickPauseTrack");
    }

    @FXML
    private void onClickStopTrack() {
        System.out.println("onClickStopTrack");
    }

    @FXML
    private void onClickNextTrack() {
        System.out.println("onClickNextTrack");
    }

    @FXML
    private void setVolume() {
        System.out.println("setVolume");
    }

    @FXML
    private void setProgress() {
        System.out.println("setProgress");
    }

}

