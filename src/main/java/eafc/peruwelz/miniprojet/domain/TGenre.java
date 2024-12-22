package eafc.peruwelz.miniprojet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class TGenre {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genId;

    @Column(nullable = false, length = 50)
    private String genName;

    @OneToMany(mappedBy = "genre")
    private Set<TTrack> genreTracks;

    public Integer getGenId() {
        return genId;
    }

    public void setGenId(final Integer genId) {
        this.genId = genId;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(final String genName) {
        this.genName = genName;
    }

    public Set<TTrack> getGenreTracks() {
        return genreTracks;
    }

    public void setGenreTracks(final Set<TTrack> genreTracks) {
        this.genreTracks = genreTracks;
    }

    @Override
    public String toString() {
        return genName;
    }

}
