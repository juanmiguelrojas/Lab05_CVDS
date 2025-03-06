package edu.eci.cvds.project.repository;


import edu.eci.cvds.project.model.Reservation;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface ReservationRepository {
    Reservation saveReservation(Reservation reservation);
    Reservation findReservationById(String id);
    List<Reservation> findAllReservations();
    void deleteTask(Reservation reservation);
    Reservation updateTask(Reservation reservation);
    boolean existsById(String id);

}
