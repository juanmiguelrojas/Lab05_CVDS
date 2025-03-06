package edu.eci.cvds.project.service;

import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.model.Reservation;
import edu.eci.cvds.project.repository.ReservationMongoRepository;
import edu.eci.cvds.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class ReservationService implements ServicesReservation {

    @Autowired
    private ReservationMongoRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        if (isLaboratoryAvilable(reservation.getLaboratory(), reservation.getStartDateTime(), reservation.getEndDateTime())){
            return reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Laboratory is not available during the given time.");
        }
    }

    @Override
    public boolean cancelReservation(String id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Reservation> getReservationsInRange(LocalDateTime start, LocalDateTime end) {
        return reservationRepository.findByStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(start, end);
    }


    private boolean isLaboratoryAvilable(Laboratory laboratory, LocalDateTime start, LocalDateTime end) {
        List<Reservation> reservations = reservationRepository.findByLaboratory(laboratory);

        for (Reservation reservation : reservations) {
            LocalDateTime existingStart = reservation.getStartDateTime();
            LocalDateTime existingEnd = reservation.getEndDateTime();
            if (start.isBefore(existingEnd) && end.isAfter(existingStart)) {
                return false;
            }
        }
        return true;
    }
    private String generateId() {
        Random random = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }
        return id;
    }
}
