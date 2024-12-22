package eafc.peruwelz.miniprojet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;


@Entity
public class TPlaylist {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plaId;

    @Column(nullable = false, length = 100)
    private String plaName;

    @ManyToMany
    @JoinTable(
            name = "PlaylistTrack",
            joinColumns = @JoinColumn(name = "plaId"),
            inverseJoinColumns = @JoinColumn(name = "traId")
    )
    private Set<TTrack> playlistTrackTracks;

    public Integer getPlaId() {
        return plaId;
    }

    public void setPlaId(final Integer plaId) {
        this.plaId = plaId;
    }

    public String getPlaName() {
        return plaName;
    }

    public void setPlaName(final String plaName) {
        this.plaName = plaName;
    }

    public Set<TTrack> getPlaylistTrackTracks() {
        return playlistTrackTracks;
    }

    public void setPlaylistTrackTracks(final Set<TTrack> playlistTrackTracks) {
        this.playlistTrackTracks = playlistTrackTracks;
    }

    @Override
    public String toString() {
        return plaName;
    }

}
