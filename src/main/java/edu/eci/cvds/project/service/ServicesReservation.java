package edu.eci.cvds.project.service;

import edu.eci.cvds.project.model.Reservation;

import java.util.List;

public interface ServicesReservation {
    List<Reservation> getAllReservations();
    Reservation createReservation(Reservation reservation);
    boolean cancelReservation(String id);
}
