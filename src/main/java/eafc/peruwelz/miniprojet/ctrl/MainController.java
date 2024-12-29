package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.MiniprojetApplication;
import eafc.peruwelz.miniprojet.Utils.WindowHelper;
import eafc.peruwelz.miniprojet.domain.Tartist;
import eafc.peruwelz.miniprojet.domain.Ttracks;
import eafc.peruwelz.miniprojet.repos.TalbumRepository;
import eafc.peruwelz.miniprojet.repos.TartistRepository;
import eafc.peruwelz.miniprojet.repos.TgenreRepository;
import eafc.peruwelz.miniprojet.repos.TtracksRepository;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import eafc.peruwelz.miniprojet.Utils.WindowConfig;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    private Media mediaPlayer ;

    private final ApplicationContext context;

    @Autowired
    public MainController(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    private TtracksRepository tracksRepository;

    @Autowired
    private TalbumRepository albumRepository;

    @Autowired
    private TgenreRepository genreRepository;

    @Autowired
    private TartistRepository artistRepository;

    @FXML
    private VBox mainView;

    @FXML
    private MenuItem menuItemAlbums, menuItemPlaylists, menuItemCatalog, menuItemAddSong, menuItemClose ;

    @FXML
    private TableView<Ttracks> fileTableView;

    @FXML
    private TableColumn<Ttracks, String> colTrackTitle;

    @FXML
    private TableColumn<Ttracks, String> colArtist;

    @FXML
    private TableColumn<Ttracks, String> colAlbum;

    @FXML
    public void initialize() {
        colTrackTitle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTraTitre()));

        colArtist.setCellValueFactory(data -> {
            // Récupérer les artistes liés à la piste
            Set<Tartist> artists = data.getValue().getTtrackArtistTartists();
            // Concaténer les noms des artistes en une seule chaîne
            String artistNames = artists.stream()
                    .map(Tartist::getArtName)
                    .reduce((name1, name2) -> name1 + ", " + name2)
                    .orElse("Unknown");
            return new SimpleStringProperty(artistNames);
        });

        colAlbum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTraAlbum().getAlbTitre()));

        loadTracks();
    }

    private void loadTracks() {
        List<Ttracks> tracks = tracksRepository.findAll();
        fileTableView.getItems().setAll(tracks);

    }

    @FXML
    private void onShowAlbums() {
        WindowHelper.openWindow(WindowConfig.ALBUMS_VIEW, WindowConfig.ALBUMS_TITLE, (Stage) mainView.getScene().getWindow());
    }

    @FXML
    private void onShowCatalog() {
        WindowHelper.openWindow(WindowConfig.TRACKS_CATALOG_VIEW, WindowConfig.TRACKS_CATALOG_TITLE, (Stage) mainView.getScene().getWindow());
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }

    @FXML
    private void onClickPreviousTrack() {
        System.out.println("onClickPreviousTrack");
    }

    @FXML
    private void onClickPlayTrack() {
        // Récupérer la piste sélectionnée
        Ttracks selectedTrack = fileTableView.getSelectionModel().getSelectedItem();

        if (selectedTrack != null) {
            String trackPath = selectedTrack.getTraPath();
            System.out.println("Playing track: " + selectedTrack.getTraTitre() + " from path: " + trackPath);

            // Passer à l'étape suivante pour lire le fichier
            //playTrack(trackPath);
        } else {
            System.out.println("No track selected!");
        }
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

