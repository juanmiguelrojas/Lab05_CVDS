package edu.eci.cvds.project.service;

import edu.eci.cvds.project.model.Laboratory;

import java.util.List;
import java.util.Optional;

public interface servicesLab {
    List<Laboratory> getAllLaboratories();
    Optional<Laboratory> getLaboratoryById(String id);
    Laboratory saveLaboratory(Laboratory laboratory);
}
