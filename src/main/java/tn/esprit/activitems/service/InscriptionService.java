package tn.esprit.activitems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.activitems.entity.*;
import tn.esprit.activitems.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InscriptionService {

    private final ActiviteRepository activiteRepository;
    private final InscriptionRepository inscriptionRepository;

    public void inscrire(Long activiteId, Long userId) {

        Activite activite = activiteRepository.findById(activiteId)
                .orElseThrow(() -> new RuntimeException("Activité inexistante"));

        if (activite.getStatut() == StatutActivite.TERMINE)
            throw new RuntimeException("Activité déja terminée");

        if (inscriptionRepository.existsByUserIdAndActiviteId(userId, activiteId))
            throw new RuntimeException("Utilisateur déjà inscrit");

        if (activite.getNbreInscriptions() >= activite.getMaxParticipants())
            throw new RuntimeException("Nombre des participants complet");

        Inscription inscription = new Inscription();
        inscription.setUserId(userId);
        inscription.setActiviteId(activiteId);
        inscription.setDateInscription(LocalDate.now());

        inscriptionRepository.save(inscription);

        activite.setNbreInscriptions(activite.getNbreInscriptions() + 1);
        activiteRepository.save(activite);
    }


    public void desinscrire(Long activiteId, Long userId) {

        inscriptionRepository.deleteByUserIdAndActiviteId(userId, activiteId);

        Activite activite = activiteRepository.findById(activiteId)
                .orElseThrow();

        activite.setNbreInscriptions(activite.getNbreInscriptions() - 1);
        activiteRepository.save(activite);
    }


    public List<Inscription> getParticipants(Long activiteId) {
        return inscriptionRepository.findByActiviteId(activiteId);
    }


    public List<Inscription> getUserActivities(Long userId) {
        return inscriptionRepository.findByUserId(userId);
    }
}
