package eafc.peruwelz.miniprojet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.Set;


@Entity
public class TTrack {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer traId;

    @Column(nullable = false, length = 150)
    private String traTitle;

    @Column(nullable = false)
    private Integer traYear;

    @Column(nullable = false)
    private String traPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artiste_id", nullable = false)
    private TArtiste artiste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private TAlbum album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private TGenre genre;

    @ManyToMany(mappedBy = "playlistTrackTracks")
    private Set<TPlaylist> playlistTrackPlaylists;

    public Integer getTraId() {
        return traId;
    }

    public void setTraId(final Integer traId) {
        this.traId = traId;
    }

    public String getTraTitle() {
        return traTitle;
    }

    public void setTraTitle(final String traTitle) {
        this.traTitle = traTitle;
    }

    public Integer getTraYear() {
        return traYear;
    }

    public void setTraYear(final Integer traYear) {
        this.traYear = traYear;
    }

    public String getTraPath() {
        return traPath;
    }

    public void setTraPath(final String traPath) {
        this.traPath = traPath;
    }

    public TArtiste getArtiste() {
        return artiste;
    }

    public void setArtiste(final TArtiste artiste) {
        this.artiste = artiste;
    }

    public TAlbum getAlbum() {
        return album;
    }

    public void setAlbum(final TAlbum album) {
        this.album = album;
    }

    public TGenre getGenre() {
        return genre;
    }

    public void setGenre(final TGenre genre) {
        this.genre = genre;
    }

    public Set<TPlaylist> getPlaylistTrackPlaylists() {
        return playlistTrackPlaylists;
    }

    public void setPlaylistTrackPlaylists(final Set<TPlaylist> playlistTrackPlaylists) {
        this.playlistTrackPlaylists = playlistTrackPlaylists;
    }

}
