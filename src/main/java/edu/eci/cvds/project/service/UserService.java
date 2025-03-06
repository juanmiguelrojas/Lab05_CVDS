package edu.eci.cvds.project.service;
import edu.eci.cvds.project.model.Reservation;
import edu.eci.cvds.project.model.User;
import edu.eci.cvds.project.repository.ReservationRepository;
import edu.eci.cvds.project.repository.UserMongoRepository;
import edu.eci.cvds.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    public User save(User user) {
        return userRepository.saveUser(user);
    }

    public User getUserById(String id) {
        return userRepository.findUserById(id);
    }


    public void deleteUser(String id) {
        userRepository.deleteUserById(id);
    }

    public List<Reservation> getAllReservationByUserId(String id) {
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        List<Reservation> reservations = reservationRepository.findAllReservations();
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getUser().equals(id)) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    public List<User> getAllUser() {
        return userRepository.findAllUsers();
    }

}
