package eafc.peruwelz.miniprojet.ctrl;

import eafc.peruwelz.miniprojet.domain.Tartist;
import eafc.peruwelz.miniprojet.repos.TartistRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import eafc.peruwelz.miniprojet.repos.TtracksRepository;
import eafc.peruwelz.miniprojet.repos.TalbumRepository;
import eafc.peruwelz.miniprojet.repos.TgenreRepository;
import eafc.peruwelz.miniprojet.domain.Ttracks;
import eafc.peruwelz.miniprojet.domain.Talbum;
import eafc.peruwelz.miniprojet.domain.Tgenre;

import java.io.File;
import java.time.LocalTime;
import java.util.HashSet;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

@Controller
public class AddTrackController {

    @FXML
    private TextField txt_catalogTitle, txt_catalogArtist, txt_catalogAlbum, txt_catalogGenre, txt_catalogTrackNumber, txt_catalogDuration, txt_CatalogPath;

    @FXML
    private Button btn_CatalogFile, btn_CatalogAddTrack, btn_CatalogCancel;

    @Autowired
    private TtracksRepository ttracksRepository;

    @Autowired
    private TalbumRepository talbumRepository;

    @Autowired
    private TgenreRepository tgenreRepository;

    @Autowired
    private TartistRepository tartistRepository;

    @FXML
    private void CatalogOnChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Audio File");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            txt_CatalogPath.setText(file.getAbsolutePath());

            try {
                AudioFile audioFile = AudioFileIO.read(file);
                Tag tag = audioFile.getTag();

                if (tag != null) {
                    String title = tag.getFirst(org.jaudiotagger.tag.FieldKey.TITLE);
                    String artist = tag.getFirst(org.jaudiotagger.tag.FieldKey.ARTIST);
                    String album = tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM);
                    String genre = tag.getFirst(org.jaudiotagger.tag.FieldKey.GENRE);
                    String trackNumber = tag.getFirst(org.jaudiotagger.tag.FieldKey.TRACK);
                    int lengthInSeconds = audioFile.getAudioHeader().getTrackLength();


                    int minutes = lengthInSeconds / 60;
                    int seconds = lengthInSeconds % 60;
                    String duration = String.format("%02d:%02d", minutes, seconds);

                    txt_catalogTitle.setText(title != null ? title : "Unknown");
                    txt_catalogArtist.setText(artist != null ? artist : "Unknown");
                    txt_catalogAlbum.setText(album != null ? album : "Unknown");
                    txt_catalogGenre.setText(genre != null ? genre : "Unknown");
                    txt_catalogTrackNumber.setText(trackNumber != null ? trackNumber : "Unknown");
                    txt_catalogDuration.setText(duration);

                }
            } catch (CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException | NullPointerException e) {
                System.out.println("Error reading audio file metadata: " + e.getMessage());
                txt_catalogDuration.setText("Unknown");
            } catch (Exception e) {
                e.printStackTrace();
                txt_catalogDuration.setText("Error");
            }
        }
    }

    @FXML
    private void CatalogOnAddTrack() {
        try {
            String title = txt_catalogTitle.getText();
            String artistName = txt_catalogArtist.getText();
            String albumName = txt_catalogAlbum.getText();
            String genreName = txt_catalogGenre.getText();
            String trackNumberText = txt_catalogTrackNumber.getText();
            String duration = txt_catalogDuration.getText();
            String path = txt_CatalogPath.getText();

            if (title.isEmpty() || artistName.isEmpty() ||albumName.isEmpty() || path.isEmpty()) {
                System.out.println("Some fields are required!");
                return;
            }

            // Rechercher ou créer l'album
            Talbum album = talbumRepository.findAll().stream()
                    .filter(a -> a.getAlbTitre().equalsIgnoreCase(albumName))
                    .findFirst()
                    .orElseGet(() -> {
                        Talbum newAlbum = new Talbum();
                        newAlbum.setAlbTitre(albumName);
                        return talbumRepository.save(newAlbum);
                    });

            // Rechercher ou créer le genre
            Tgenre genre = tgenreRepository.findAll().stream()
                    .filter(g -> g.getGenName().equalsIgnoreCase(genreName))
                    .findFirst()
                    .orElseGet(() -> {
                        Tgenre newGenre = new Tgenre();
                        newGenre.setGenName(genreName);
                        return tgenreRepository.save(newGenre);
                    });

            // Rechercher ou créer l'artiste
            Tartist artist = tartistRepository.findAll().stream()
                    .filter(a -> a.getArtName().equalsIgnoreCase(artistName))
                    .findFirst()
                    .orElseGet(() -> {
                        Tartist newArtist = new Tartist();
                        newArtist.setArtName(artistName);
                        return tartistRepository.save(newArtist);
                    });

            // Créer la nouvelle piste
            Ttracks newTrack = new Ttracks();
            newTrack.setTraTitre(title);
            newTrack.setTraAlbum(album);
            newTrack.setTraPath(path);
            newTrack.setTraNr(Integer.parseInt(trackNumberText));
            newTrack.setTraDuree(LocalTime.parse(duration));

            if (newTrack.getTtrackGenreTgenres() == null) {
                newTrack.setTtrackGenreTgenres(new HashSet<>());
            }
            newTrack.getTtrackGenreTgenres().add(genre);

            if (newTrack.getTtrackArtistTartists() == null) {
                newTrack.setTtrackArtistTartists(new HashSet<>());
            }
            newTrack.getTtrackArtistTartists().add(artist);

            ttracksRepository.save(newTrack);
            System.out.println("Track added to database!");

            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while adding the track.");
        }
    }

    @FXML
    private void CatalogOnCancel() {
        clearFields();
    }

    private void clearFields() {
        txt_catalogTitle.clear();
        txt_catalogArtist.clear();
        txt_catalogAlbum.clear();
        txt_catalogGenre.clear();
        txt_catalogTrackNumber.clear();
        txt_catalogDuration.clear();
        txt_CatalogPath.clear();
    }
}
