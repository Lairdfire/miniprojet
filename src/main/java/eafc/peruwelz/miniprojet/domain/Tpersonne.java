package eafc.peruwelz.miniprojet.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Tpersonne {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer persId;

    @Column(length = 50)
    private String persNom;

    @Column(length = 50)
    private String persPrenom;

    public Integer getPersId() {
        return persId;
    }

    public void setPersId(final Integer persId) {
        this.persId = persId;
    }

    public String getPersNom() {
        return persNom;
    }

    public void setPersNom(final String persNom) {
        this.persNom = persNom;
    }

    public String getPersPrenom() {
        return persPrenom;
    }

    public void setPersPrenom(final String persPrenom) {
        this.persPrenom = persPrenom;
    }

}
