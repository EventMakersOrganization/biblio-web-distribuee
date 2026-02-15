package tn.esprit.reservation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.reservation_service.entity.Reservation;
import tn.esprit.reservation_service.model.ReservationStatus;

import java.util.List;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userId);
    long countByUserIdAndStatus(Long userId, ReservationStatus status);
    boolean existsByUserIdAndBookIdAndStatus(
            Long userId,
            Long bookId,
            ReservationStatus status
    );


}

