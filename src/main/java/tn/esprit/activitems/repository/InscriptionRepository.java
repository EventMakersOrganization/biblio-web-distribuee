package tn.esprit.activitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.activitems.entity.Inscription;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    boolean existsByUserIdAndActiviteId(Long userId, Long activiteId);

    List<Inscription> findByActiviteId(Long activiteId);

    List<Inscription> findByUserId(Long userId);

    long countByActiviteId(Long activiteId);

    void deleteByUserIdAndActiviteId(Long userId, Long activiteId);
}
