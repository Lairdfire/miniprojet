package eafc.peruwelz.miniprojet.ctrl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainController {

    public MenuItem menuItemAlbums;
    public MenuItem menuItemPlaylists;
    public MenuItem menuItemAddSong;
    public MenuItem menuItemClose;
    public VBox mainView;


    @FXML
    public void initialize() {
    }

    @FXML
    public void onShowAlbums() {
        loadViewInNewWindow("AlbumsView.fxml", "Albums");
    }

    @FXML
    public void onShowPlaylists() {
        loadViewInNewWindow("PlaylistView.fxml", "Playlists");
    }

    @FXML
    public void onShowAddSong() {
        loadViewInNewWindow("AddSongView.fxml", "Albums");
    }

    @FXML
    public void onClose() {
        System.exit(0);
    }

    @FXML
    public void onClickPreviousTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    public void onClickPlayTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    public void onClickPauseTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    public void onClickStopTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    public void onClickNextTrack() {
        System.out.println("Good Click !");
    }

    @FXML
    public void setVolume() {
        System.out.println("Good Click !");
    }

    @FXML
    public void setProgress() {
        System.out.println("Good Click !");
    }



    public void loadViewInNewWindow(String fxmlFile, String title) {
        try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        VBox root = loader.load();

        Scene scene = new Scene(root);

        Stage newStage = new Stage();
        newStage.setTitle(title);
        newStage.setScene(scene);
        newStage.initOwner(mainView.getScene().getWindow());
        newStage.setResizable(false);
        newStage.initModality(Modality.WINDOW_MODAL); // Désactive la fenêtre principale
        newStage.show();

    } catch (IOException e) {
        e.printStackTrace();
        System.err.println("Error loading view: " + fxmlFile);
        }
    }
}
