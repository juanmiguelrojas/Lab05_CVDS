package edu.eci.cvds.project.repository;

import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ReservationMongoRepository extends ReservationRepository,MongoRepository<Reservation, String> {
    List<Reservation> findByUser(String user);

    List<Reservation> findByLaboratory(Laboratory laboratory);

    List<Reservation> findByStartDateTimeAfter(LocalDateTime startDateTime);

    List<Reservation> findByStartDateTimeGreaterThanEqualAndEndDateTimeLessThanEqual(
            LocalDateTime start, LocalDateTime end);

    public List<Reservation> findByStatus(boolean status);

    @Override
    public default List<Reservation> findAllReservations(){
        return findAll();
    }

    @Override
    public default Reservation saveReservation(Reservation reservation) {
        reservation.setStatus(false);
        reservation.setEndDateTime(null);
        reservation.setStartDateTime(LocalDateTime.now());
        if(reservation.getId() == null){
            reservation.setId(generateId());
        }
        save(reservation);
        return reservation;
    }

    private String generateId() {
        String id = "";
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        do {
            for (int i = 0; i < 8; i++) {
                id += characters.charAt((int) Math.floor(Math.random() * characters.length()));
            }
        } while (existsById(id));
        return id;
    }
    @Override
    public default boolean existsById(String id){
        Reservation reservation= findById(id).orElse(null);
        return reservation != null;
    }


}
