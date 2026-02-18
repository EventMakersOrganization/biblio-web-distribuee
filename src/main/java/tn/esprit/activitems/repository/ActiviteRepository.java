package tn.esprit.activitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.activitems.entity.Activite;
import tn.esprit.activitems.entity.StatutActivite;
import tn.esprit.activitems.entity.TypeActivite;

import java.time.LocalDate;
import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    List<Activite> findByStatut(StatutActivite statut);

    List<Activite> findByType(TypeActivite type);

    List<Activite> findByDateDebutAfter(LocalDate date);

    List<Activite> findByDateFinBefore(LocalDate date);
}
