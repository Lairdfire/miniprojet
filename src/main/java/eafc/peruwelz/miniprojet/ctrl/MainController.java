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
    private MenuItem menuItemAddSong;
    @FXML
    private MenuItem menuItemClose;
    @FXML
    private VBox mainView;

    @FXML
    public void initialize() {
        // Méthode appelée automatiquement après l'injection des composants
    }

    @FXML
    private void onShowAlbums() {
        openNewWindow(WindowConfig.ALBUMS_VIEW, WindowConfig.ALBUMS_TITLE);
    }

    @FXML
    private void onShowPlaylists() {
        openNewWindow(WindowConfig.PLAYLISTS_VIEW, WindowConfig.PLAYLISTS_TITLE);
    }

    @FXML
    private void onShowAddSong() {
        openNewWindow(WindowConfig.ADD_SONG_VIEW, WindowConfig.ADD_SONG_TITLE);
    }

    @FXML
    private void onShowCatalog() {
        openNewWindow(WindowConfig.CATALOG_VIEW, WindowConfig.CATALOG_TITLE);
    }

    @FXML
    private void onClose() {
        System.exit(0);
    }

    @FXML
    private void onClickPreviousTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    private void onClickPlayTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    private void onClickPauseTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    private void onClickStopTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    private void onClickNextTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    private void setVolume() {
        System.out.println("Good Click !");
    }

    @FXML
    private void setProgress() {
        System.out.println("Good Click !");
    }

    private void openNewWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(context::getBean);
            VBox newView = loader.load();

            Stage newWindow = new Stage();
            newWindow.setScene(new Scene(newView));
            newWindow.setTitle(title);
            newWindow.setResizable(false);
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'ouverture de la fenêtre : " + title);
        }
    }
}
