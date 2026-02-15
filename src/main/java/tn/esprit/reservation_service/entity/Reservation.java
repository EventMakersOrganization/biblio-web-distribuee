package tn.esprit.reservation_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import tn.esprit.reservation_service.model.ReservationStatus;

import java.time.LocalDate;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long bookId;

    private LocalDate reservationDate;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
