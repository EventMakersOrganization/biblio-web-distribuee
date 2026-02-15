package tn.esprit.reservation_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.reservation_service.entity.Reservation;
import tn.esprit.reservation_service.model.ReservationStatus;
import tn.esprit.reservation_service.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;

    private static final int MAX_ACTIVE_RESERVATIONS = 3;


    // CREATE
    public Reservation createReservation(Long userId, Long bookId) {

        // 🔹 RÈGLE 1 : Limite de réservations
        long activeReservations = repository
                .countByUserIdAndStatus(userId, ReservationStatus.ACTIVE);

        if (activeReservations >= MAX_ACTIVE_RESERVATIONS) {
            throw new RuntimeException("User reached maximum active reservations (3)");
        }

        // 🔹 RÈGLE 2 : Double réservation interdite
        boolean alreadyReserved = repository
                .existsByUserIdAndBookIdAndStatus(
                        userId,
                        bookId,
                        ReservationStatus.ACTIVE
                );

        if (alreadyReserved) {
            throw new RuntimeException("User already reserved this book");
        }

        // 🔹 Création normale
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setBookId(bookId);
        reservation.setReservationDate(LocalDate.now());
        reservation.setExpirationDate(LocalDate.now().plusDays(3));
        reservation.setStatus(ReservationStatus.ACTIVE);

        return repository.save(reservation);
    }



    // READ ALL
    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    // READ BY ID
    public Reservation getReservationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    // READ BY USER
    public List<Reservation> getUserReservations(Long userId) {
        return repository.findByUserId(userId);
    }

    // UPDATE
    public Reservation updateReservation(Long id, Reservation updatedReservation) {

        Reservation existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        existing.setBookId(updatedReservation.getBookId());
        existing.setUserId(updatedReservation.getUserId());
        existing.setExpirationDate(updatedReservation.getExpirationDate());
        existing.setStatus(updatedReservation.getStatus());

        return repository.save(existing);
    }

    // DELETE
    public void deleteReservation(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Reservation not found");
        }

        repository.deleteById(id);
    }

    // CANCEL (business logic)
    public Reservation cancelReservation(Long id) {

        Reservation reservation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);
        return repository.save(reservation);
    }


//vérifie toutes les réservations et expire celles dépassées.
    public void updateExpiredReservations() {

        List<Reservation> reservations = repository.findAll();

        for (Reservation reservation : reservations) {

            if (reservation.getStatus() == ReservationStatus.ACTIVE &&
                    reservation.getExpirationDate().isBefore(LocalDate.now())) {

                reservation.setStatus(ReservationStatus.EXPIRED);
                repository.save(reservation);
            }
        }
    }


    @Scheduled(fixedRate = 60000) // toutes les 60 secondes
    public void autoExpireReservations() {
        updateExpiredReservations();
    }


}
