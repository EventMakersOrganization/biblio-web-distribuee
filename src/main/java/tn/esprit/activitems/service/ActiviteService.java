package tn.esprit.activitems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.activitems.entity.Activite;
import tn.esprit.activitems.entity.StatutActivite;
import tn.esprit.activitems.repository.ActiviteRepository;
import tn.esprit.activitems.repository.InscriptionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiviteService {

    private final ActiviteRepository activiteRepository;
    private final InscriptionRepository inscriptionRepository;

    public Activite createActivite(Activite activite) {

        if (activite.getDateFin().isBefore(activite.getDateDebut()))
            throw new RuntimeException("Date fin doit etre superieur date debut");

        if (activite.getMaxParticipants() <= 0)
            throw new RuntimeException("Capacité invalide");

        activite.setNbreInscriptions(0);
        activite.setStatut(StatutActivite.OUVERT);

        return activiteRepository.save(activite);
    }


    public Activite updateActivite(Long id, Activite updated) {

        Activite activite = activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));

        activite.setTitre(updated.getTitre());
        activite.setDescription(updated.getDescription());
        activite.setType(updated.getType());
        activite.setDateDebut(updated.getDateDebut());
        activite.setDateFin(updated.getDateFin());
        activite.setMaxParticipants(updated.getMaxParticipants());

        return activiteRepository.save(activite);
    }


    public void deleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }


    public List<Activite> getAll() {
        updateStatutAutomatique();
        return activiteRepository.findAll();
    }


    public Activite getById(Long id) {
        return activiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));
    }


    public List<Activite> getOpenActivities() {
        updateStatutAutomatique();
        return activiteRepository.findByStatut(StatutActivite.OUVERT);
    }


    public void updateStatutAutomatique() {

        List<Activite> list = activiteRepository.findAll();

        for (Activite a : list) {

            if (a.getDateFin().isBefore(LocalDate.now())) {
                a.setStatut(StatutActivite.TERMINE);
            }

            else if (a.getNbreInscriptions() >= a.getMaxParticipants()) {
                a.setStatut(StatutActivite.COMPLET);
            }

            else {
                a.setStatut(StatutActivite.OUVERT);
            }

            activiteRepository.save(a);
        }
    }
}