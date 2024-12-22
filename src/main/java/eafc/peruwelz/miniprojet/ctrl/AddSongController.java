package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.domain.TGenre;
import eafc.peruwelz.miniprojet.domain.TPlaylist;
import eafc.peruwelz.miniprojet.repos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;

@Controller
public class AddSongController {

    @Autowired
    private TTrackRepository tTrackRepository;

    @Autowired
    private TGenreRepository tGenreRepository;

    @Autowired
    private TPlaylistRepository tPlaylistRepository;

    @Autowired
    private TArtisteRepository tArtisteRepository;

    @Autowired
    private TAlbumRepository tAlbumRepository;

    @FXML
    private TextField AddsongTrackTitleField, AddsongArtistField, AddsongAlbumTitleField, AddsongYearField;

    @FXML
    private ComboBox<TGenre> AddsongGenreComboBox;

    @FXML
    private ComboBox<TPlaylist> AddsongSelectPlaylistComboBox;

    @FXML
    private Button selectFileButton, AddSongSaveButton, AddSongResetButton, AddSongCancelButton;

    @FXML
    private Label AddSongFilePathLabel;

    @FXML
    public void initialize() {
        loadGenres();
        loadPLaylists();
    }

    @FXML
    private void loadGenres() {
        ObservableList<TGenre> genres = FXCollections.observableArrayList(tGenreRepository.findAll());
        AddsongGenreComboBox.setItems(genres);
    }

    @FXML
    private void loadPLaylists() {
        ObservableList<TPlaylist> playlists = FXCollections.observableArrayList(tPlaylistRepository.findAll());
        AddsongSelectPlaylistComboBox.setItems(playlists);
    }

    @FXML
    private void onSelectFileButtonClick() {
        // 1) Create and configure the FileChooser
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Choose an audio file");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "Fichiers audio", "*.mp3", "*.wav", "*.flac" // etc. if you want more formats
        );
        filechooser.getExtensionFilters().add(extensionFilter);

        // 2) Show the open dialog
        File selectedFile = filechooser.showOpenDialog(selectFileButton.getScene().getWindow());
        if (selectedFile != null) {
            // 3) Display the chosen file path
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Chemin du fichier : " + filePath);
            AddSongFilePathLabel.setText(filePath);

            // 4) Extract metadata with Jaudiotagger
            try {
                // Read the audio file
                AudioFile audioFile = AudioFileIO.read(selectedFile);
                Tag tag = audioFile.getTag();

                // Check if we actually got some metadata
                if (tag != null) {
                    // Get the fields you want
                    String title = tag.getFirst(FieldKey.TITLE);
                    String artist = tag.getFirst(FieldKey.ARTIST);
                    String album = tag.getFirst(FieldKey.ALBUM);
                    String year = tag.getFirst(FieldKey.YEAR);

                    // 5) Display them in your TextFields
                    AddsongTrackTitleField.setText(title);
                    AddsongArtistField.setText(artist);
                    AddsongAlbumTitleField.setText(album);
                    AddsongYearField.setText(year);

                } else {
                    System.out.println("Aucune métadonnée trouvée pour ce fichier.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Impossible de lire les tags pour ce fichier : " + filePath);
            }
        }
    }


    public void onSaveTrackButtonClick(ActionEvent actionEvent) {

        String title = AddsongTrackTitleField.getText();
        if (title == null || title.isBlank()) {
            System.out.println("Title is required");
            return;
        }

        String artistName = AddsongArtistField.getText();
        if (title == null || artistName.isBlank()) {
            System.out.println("Artist name is required");
            return;
        }
    }

    public void onResetFields(ActionEvent actionEvent) {

    }

    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }
}
