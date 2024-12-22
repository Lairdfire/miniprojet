package eafc.peruwelz.miniprojet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class TArtiste {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artId;

    @Column(nullable = false, length = 100)
    private String artName;

    @OneToMany(mappedBy = "artiste")
    private Set<TAlbum> artisteAlbums;

    @OneToMany(mappedBy = "artiste")
    private Set<TTrack> artisteTracks;

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(final Integer artId) {
        this.artId = artId;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(final String artName) {
        this.artName = artName;
    }

    public Set<TAlbum> getArtisteAlbums() {
        return artisteAlbums;
    }

    public void setArtisteAlbums(final Set<TAlbum> artisteAlbums) {
        this.artisteAlbums = artisteAlbums;
    }

    public Set<TTrack> getArtisteTracks() {
        return artisteTracks;
    }

    public void setArtisteTracks(final Set<TTrack> artisteTracks) {
        this.artisteTracks = artisteTracks;
    }

}
