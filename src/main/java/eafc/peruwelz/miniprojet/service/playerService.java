package eafc.peruwelz.miniprojet.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class playerService {

    private Clip audioClip;
    private FloatControl volumeControl;
    private List<String> playlist;
    private int currentTrackIndex = -1;

    private double volume = 1.0 ;

    public void setPlaylist(List<String> playlist) {
        this.playlist = playlist;
        currentTrackIndex = playlist.isEmpty() ? -1 : 0;
    }

    public void play(String filePath) {
        stop(); // Arrêter tout fichier en cours

        try {
            // Ouvrir le fichier audio
            File audioFile = new File(filePath);

            //Charger le fichier audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Convertir le fichier en PCM si il faut
            AudioFormat baseFormat = audioStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat, audioStream);

            // Charger le fichier dans un Clip
            audioClip = AudioSystem.getClip();
            audioClip.open(decodedAudioStream);

            // Récupérer le contrôle de volume
            if (audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                volumeControl = null;
                System.out.println("Volume control not supported.");
            }

            // Démarrer la lecture
            audioClip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.err.println("Error playing audio file: " + e.getMessage());
        }
    }

    public void stop() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            audioClip.close();
        }
    }

    public void pause() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    public void resume() {
        if (audioClip != null && !audioClip.isRunning()) {
            audioClip.start();
        }
    }


    public void nextTrack() {
        if (playlist == null || playlist.isEmpty() || currentTrackIndex == -1) {
            System.out.println("No next track available.");
            return;
        }

        currentTrackIndex = (currentTrackIndex + 1) % playlist.size();
        play(playlist.get(currentTrackIndex));
    }

    public void previousTrack() {
        if (playlist == null || playlist.isEmpty() || currentTrackIndex == -1) {
            System.out.println("No previous track available.");
            return;
        }

        currentTrackIndex = (currentTrackIndex - 1 + playlist.size()) % playlist.size();
        play(playlist.get(currentTrackIndex));
    }

    public int getCurrentTrackIndex() {
        return currentTrackIndex;
    }

    public void setCurrentTrackIndex(int index) {
        if (index >= 0 && index < (playlist != null ? playlist.size() : 0)) {
            currentTrackIndex = index;
        } else {
            System.out.println("Invalid track index.");
        }
    }

    public void setVolume(float volume) {
        if (audioClip != null) {
            // Afficher les types de contrôles disponibles pour debug
            for (Control control : audioClip.getControls()) {
                System.out.println("Available control: " + control.getType());
            }

            if (audioClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);

                float originalMin = volumeControl.getMinimum(); // Par exemple, -80.0
                float adjustedMin = -30.0f;
                float max = volumeControl.getMaximum(); // Par exemple, 6.0

                float gain;
                if (volume == 0.0f) {
                    gain = originalMin; // Volume coupé si le slider est à 0
                } else {
                    gain = adjustedMin + (volume * (max - adjustedMin)); // Volume progressif
                }

                volumeControl.setValue(gain);

                System.out.println("Volume adjusted to: " + gain);
            } else {
                System.out.println("MASTER_GAIN control not supported.");
            }
        } else {
            System.out.println("No active clip to set volume.");
        }
    }
}
