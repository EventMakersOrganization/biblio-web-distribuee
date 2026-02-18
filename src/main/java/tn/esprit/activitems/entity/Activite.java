package tn.esprit.activitems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActivite;

    private String titre;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TypeActivite type;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private Integer maxParticipants;
    private Integer nbreInscriptions;

    @Enumerated(EnumType.STRING)
    private StatutActivite statut;



}
