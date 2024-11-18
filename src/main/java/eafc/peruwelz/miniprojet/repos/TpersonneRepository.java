package eafc.peruwelz.miniprojet.repos;

import eafc.peruwelz.miniprojet.domain.Tpersonne;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TpersonneRepository extends JpaRepository<Tpersonne, Integer> {
}
