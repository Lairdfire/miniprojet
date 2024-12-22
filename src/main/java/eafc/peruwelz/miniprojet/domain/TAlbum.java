package eafc.peruwelz.miniprojet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class TAlbum {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albId;

    @Column(nullable = false, length = 150)
    private String albTitle;

    @Column(nullable = false)
    private Integer albYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artiste_id", nullable = false)
    private TArtiste artiste;

    @OneToMany(mappedBy = "album")
    private Set<TTrack> albumTracks;

    public Integer getAlbId() {
        return albId;
    }

    public void setAlbId(final Integer albId) {
        this.albId = albId;
    }

    public String getAlbTitle() {
        return albTitle;
    }

    public void setAlbTitle(final String albTitle) {
        this.albTitle = albTitle;
    }

    public Integer getAlbYear() {
        return albYear;
    }

    public void setAlbYear(final Integer albYear) {
        this.albYear = albYear;
    }

    public TArtiste getArtiste() {
        return artiste;
    }

    public void setArtiste(final TArtiste artiste) {
        this.artiste = artiste;
    }

    public Set<TTrack> getAlbumTracks() {
        return albumTracks;
    }

    public void setAlbumTracks(final Set<TTrack> albumTracks) {
        this.albumTracks = albumTracks;
    }

}
