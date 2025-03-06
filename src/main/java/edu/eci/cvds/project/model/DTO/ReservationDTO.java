package edu.eci.cvds.project.model.DTO;

import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.model.Reservation;
import edu.eci.cvds.project.model.User;

import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDTO {
    private Reservation reservation;


    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public String getId() {
        return reservation.getId();
    }
    public User getUser() {
        return reservation.getUser();
    }

    public Laboratory getLaboratory() {
        return reservation.getLaboratory();
    }
    public LocalDateTime getStartTime() {
        return reservation.getStartDateTime();}
    public LocalDateTime getEndTime() {
        return reservation.getEndDateTime();
    }
}
