package edu.eci.cvds.project.service;

import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.model.Reservation;
import edu.eci.cvds.project.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LaboratoryService implements servicesLab {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Override
    public List<Laboratory> getAllLaboratories() {
        return laboratoryRepository.findAll();
    }
    @Override
    public Optional<Laboratory> getLaboratoryById(String id) {
        return laboratoryRepository.findById(id);
    }
    @Override
    public Laboratory saveLaboratory(Laboratory laboratory) {
        return laboratoryRepository.save(laboratory);
    }

    public boolean isLaboratoryAvailable(Laboratory laboratory, LocalDateTime localDateTime) {
        for(Reservation reservation : laboratory.getReservations()){
            LocalDateTime start = reservation.getStartDateTime();
            LocalDateTime end = reservation.getEndDateTime();
            if (!localDateTime.isBefore(start) && !localDateTime.isAfter(end)) {
                return false;
            }
        }
        return true;
    }

}
