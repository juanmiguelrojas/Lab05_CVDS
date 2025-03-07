package edu.eci.cvds.project;

import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.repository.LaboratoryRepository;
import edu.eci.cvds.project.service.LaboratoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LaboratoryServiceTest {

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @InjectMocks
    private LaboratoryService laboratoryService;

    private Laboratory laboratory1;
    private Laboratory laboratory2;

    @BeforeEach
    void setUp() {
        laboratory1 = new Laboratory("1", "Lab A");
        laboratory2 = new Laboratory("2", "Lab B");
    }

    @Test
    void testGetAllLaboratories() {
        List<Laboratory> laboratories = Arrays.asList(laboratory1, laboratory2);
        when(laboratoryRepository.findAll()).thenReturn(laboratories);

        List<Laboratory> result = laboratoryService.getAllLaboratories();

        assertEquals(2, result.size());
        assertEquals("Lab A", result.get(0).getName());
        verify(laboratoryRepository, times(1)).findAll();
    }

    @Test
    void testGetLaboratoryById_Found() {
        when(laboratoryRepository.findById("1")).thenReturn(Optional.of(laboratory1));

        Optional<Laboratory> result = laboratoryService.getLaboratoryById("1");

        assertTrue(result.isPresent());
        assertEquals("Lab A", result.get().getName());
        verify(laboratoryRepository, times(1)).findById("1");
    }

    @Test
    void testGetLaboratoryById_NotFound() {
        when(laboratoryRepository.findById("3")).thenReturn(Optional.empty());

        Optional<Laboratory> result = laboratoryService.getLaboratoryById("3");

        assertFalse(result.isPresent());
        verify(laboratoryRepository, times(1)).findById("3");
    }

    @Test
    void testSaveLaboratory() {
        when(laboratoryRepository.save(laboratory1)).thenReturn(laboratory1);

        Laboratory result = laboratoryService.saveLaboratory(laboratory1);

        assertNotNull(result);
        assertEquals("Lab A", result.getName());
        verify(laboratoryRepository, times(1)).save(laboratory1);
    }
}

