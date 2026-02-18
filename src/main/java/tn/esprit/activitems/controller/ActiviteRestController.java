package tn.esprit.activitems.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.activitems.entity.Activite;
import tn.esprit.activitems.service.ActiviteService;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActiviteRestController {

    private final ActiviteService service;

    //http://localhost:8088/activities
    @PostMapping
    public Activite create(@RequestBody Activite activite) {
        return service.createActivite(activite);
    }

    //http://localhost:8088/activities
    @GetMapping
    public List<Activite> getAll() {
        return service.getAll();
    }

    //http://localhost:8088/activities/1
    @GetMapping("/{id}")
    public Activite getById(@PathVariable Long id) {
        return service.getById(id);
    }

    //http://localhost:8088/activities/1
    @PutMapping("/{id}")
    public Activite update(@PathVariable Long id,
                           @RequestBody Activite activite) {
        return service.updateActivite(id, activite);
    }

    //http://localhost:8088/activities/1
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteActivite(id);
    }

    //http://localhost:8088/activities/open
    @GetMapping("/open")
    public List<Activite> openActivities() {
        return service.getOpenActivities();
    }
}

