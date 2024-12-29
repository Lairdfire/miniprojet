package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.Utils.WindowHelper;
import eafc.peruwelz.miniprojet.domain.Tartist;
import eafc.peruwelz.miniprojet.domain.Ttracks;
import eafc.peruwelz.miniprojet.repos.TtracksRepository;
import eafc.peruwelz.miniprojet.service.playerService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    private final playerService playerService = new playerService();
    private final ApplicationContext context;

    @Autowired
    public MainController(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    private TtracksRepository tracksRepository;

    @FXML
    private VBox mainView;

    @FXML
    private MenuItem menuItemAlbums, menuItemPlaylists, menuItemCatalog, menuItemAddSong, menuItemClose;

    @FXML
    private TableView<Ttracks> fileTableView;

    @FXML
    private TableColumn<Ttracks, String> colTrackTitle;

    @FXML
    private TableColumn<Ttracks, String> colArtist;

    @FXML
    private TableColumn<Ttracks, String> colAlbum;

    @FXML
    private Slider volumeSlider;


    @FXML
    public void initialize() {
        setupTableColumns();
        loadTracks();

        // Listener pour détecter les changements de tri
        fileTableView.getSortOrder().addListener((ListChangeListener<TableColumn<Ttracks, ?>>) change -> {
            updatePlaylistAfterSort();
        });

        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(100);

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> setVolume((float) newValue.doubleValue()));
    }

    private void setupTableColumns() {
        colTrackTitle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTraTitre()));

        colArtist.setCellValueFactory(data -> {
            Set<Tartist> artists = data.getValue().getTtrackArtistTartists();
            String artistNames = artists.stream()
                    .map(Tartist::getArtName)
                    .reduce((name1, name2) -> name1 + ", " + name2)
                    .orElse("Unknown");
            return new SimpleStringProperty(artistNames);
        });

        colAlbum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTraAlbum().getAlbTitre()));
    }

    public void loadTracks() {
        List<Ttracks> tracks = tracksRepository.findAll();
        fileTableView.getItems().setAll(tracks);

        // Charger la playlist dans le playerService
        List<String> trackPaths = tracks.stream()
                .map(Ttracks::getTraPath)
                .filter(path -> path != null && !path.isEmpty())
                .toList();

        playerService.setPlaylist(trackPaths);
    }

    @FXML
    private void onShowAlbums() {
        WindowHelper.openWindow("albums_view.fxml", "Albums", (Stage) mainView.getScene().getWindow());
    }

    @FXML
    private void onShowCatalog() {
        WindowHelper.openWindow("catalog_view.fxml", "Catalog", (Stage) mainView.getScene().getWindow());
    }

    @FXML
    private void onClose() {
        Platform.exit();
    }

    @FXML
    private void onClickPlayTrack() {
        Ttracks selectedTrack = fileTableView.getSelectionModel().getSelectedItem();

        if (selectedTrack != null && selectedTrack.getTraPath() != null) {
            playerService.play(selectedTrack.getTraPath());
        } else {
            System.out.println("No track selected or file path unavailable.");
        }
    }

    @FXML
    private void onClickPauseTrack() {
        playerService.pause();
    }

    @FXML
    private void onClickStopTrack() {
        playerService.stop();
    }

    @FXML
    private void onClickPreviousTrack() {
        if (!fileTableView.getItems().isEmpty()) {
            playerService.previousTrack();
            updateSelectedTrackInTable();
        } else {
            System.out.println("No tracks available to navigate.");
        }
    }

    @FXML
    private void onClickNextTrack() {
        if (!fileTableView.getItems().isEmpty()) {
            playerService.nextTrack();
            updateSelectedTrackInTable();
        } else {
            System.out.println("No tracks available to navigate.");
        }
    }

    private void updateSelectedTrackInTable() {
        int currentTrackIndex = playerService.getCurrentTrackIndex();
        if (currentTrackIndex >= 0 && currentTrackIndex < fileTableView.getItems().size()) {
            fileTableView.getSelectionModel().select(currentTrackIndex);
        }
    }

    private void updatePlaylistAfterSort() {
        List<String> sortedTrackPaths = fileTableView.getItems().stream()
                .map(Ttracks::getTraPath)
                .filter(path -> path != null && !path.isEmpty())
                .toList();

        playerService.setPlaylist(sortedTrackPaths);

        Ttracks selectedTrack = fileTableView.getSelectionModel().getSelectedItem();
        if (selectedTrack != null) {
            int newIndex = sortedTrackPaths.indexOf(selectedTrack.getTraPath());
            playerService.setCurrentTrackIndex(newIndex);
        }
    }

    public void setVolume(float volume) {
        // Ajuster le volume dans le service audio
        playerService.setVolume(volume / 100); // Supposons que le slider donne des valeurs de 0 à 100
        System.out.println("Volume set to: " + volume);
    }

    public void setProgress(MouseEvent mouseEvent) {
    }
}
