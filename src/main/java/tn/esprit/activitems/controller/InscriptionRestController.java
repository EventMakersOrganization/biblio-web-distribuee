package tn.esprit.activitems.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.activitems.entity.Inscription;
import tn.esprit.activitems.service.InscriptionService;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
@RequiredArgsConstructor
public class InscriptionRestController {

    private final InscriptionService service;

    //http://localhost:8088/inscriptions/2/user/10
    @PostMapping("/{activiteId}/user/{userId}")
    public void inscrire(@PathVariable Long activiteId,
                         @PathVariable Long userId) {
        service.inscrire(activiteId, userId);
    }

    //http://localhost:8088/inscriptions/2/user/10
    @DeleteMapping("/{activiteId}/user/{userId}")
    public void desinscrire(@PathVariable Long activiteId,
                            @PathVariable Long userId) {
        service.desinscrire(activiteId, userId);
    }

    //liste des inscriptions a une activite
    //http://localhost:8088/inscriptions/activity/2
    @GetMapping("/activity/{activiteId}")
    public List<Inscription> participants(@PathVariable Long activiteId) {
        return service.getParticipants(activiteId);
    }

    //liste des inscription d'un user
    //http://localhost:8088/inscriptions/user/10
    @GetMapping("/user/{userId}")
    public List<Inscription> userActivities(@PathVariable Long userId) {
        return service.getUserActivities(userId);
    }
}

