package edu.eci.cvds.project;


import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.model.Reservation;
import edu.eci.cvds.project.repository.LaboratoryRepository;
import edu.eci.cvds.project.service.LaboratoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LaboratoryServiceTest {

    @Mock
    private LaboratoryRepository laboratoryRepository;  // Mock de la dependencia

    @InjectMocks
    private LaboratoryService laboratoryService;  // El servicio bajo prueba

    private Laboratory laboratory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks

        // Crear un laboratorio de prueba
        laboratory = new Laboratory();
        laboratory.setId("1");
        laboratory.setName("Lab 101");
    }

    @Test
    public void testGetAllLaboratories() {
        // Configurar el mock para devolver una lista cuando se llame a findAll()
        when(laboratoryRepository.findAll()).thenReturn(List.of(laboratory));

        // Llamar al método
        var laboratories = laboratoryService.getAllLaboratories();

        // Verificar que el resultado contiene el laboratorio esperado
        assertNotNull(laboratories);
        assertFalse(laboratories.isEmpty());
        assertEquals(1, laboratories.size());
        assertEquals("Lab 101", laboratories.get(0).getName());
    }

    @Test
    public void testGetLaboratoryById() {
        // Configurar el mock para devolver un laboratorio cuando se llame a findById()
        when(laboratoryRepository.findById("1")).thenReturn(Optional.of(laboratory));

        // Llamar al método
        Optional<Laboratory> result = laboratoryService.getLaboratoryById("1");

        // Verificar que se devuelve el laboratorio correcto
        assertTrue(result.isPresent());
        assertEquals("Lab 101", result.get().getName());
    }

    @Test
    public void testSaveLaboratory() {
        // Configurar el mock para que devuelva el laboratorio cuando se llame a save()
        when(laboratoryRepository.save(any(Laboratory.class))).thenReturn(laboratory);

        // Llamar al método
        Laboratory savedLaboratory = laboratoryService.saveLaboratory(laboratory);

        // Verificar que el laboratorio guardado es el correcto
        assertNotNull(savedLaboratory);
        assertEquals("Lab 101", savedLaboratory.getName());
    }

    @Test
    public void testIsLaboratoryAvailable_whenAvailable() {
        // Crear una reserva que no interfiera con la fecha solicitada
        Reservation reservation = new Reservation();
        reservation.setStartDateTime(LocalDateTime.of(2025, 3, 6, 10, 0));
        reservation.setEndDateTime(LocalDateTime.of(2025, 3, 6, 12, 0));
        laboratory.setReservations(List.of(reservation));

        // Verificar que el laboratorio está disponible a una hora fuera del rango de la reserva
        LocalDateTime checkTime = LocalDateTime.of(2025, 3, 6, 14, 0);
        boolean available = laboratoryService.isLaboratoryAvailable(laboratory, checkTime);

        assertTrue(available);  // Debería estar disponible
    }

    @Test
    public void testIsLaboratoryAvailable_whenNotAvailable() {
        // Crear una reserva que interfiera con la fecha solicitada
        Reservation reservation = new Reservation();
        reservation.setStartDateTime(LocalDateTime.of(2025, 3, 6, 10, 0));
        reservation.setEndDateTime(LocalDateTime.of(2025, 3, 6, 12, 0));
        laboratory.setReservations(List.of(reservation));

        // Verificar que el laboratorio NO está disponible en el rango de la reserva
        LocalDateTime checkTime = LocalDateTime.of(2025, 3, 6, 11, 0);
        boolean available = laboratoryService.isLaboratoryAvailable(laboratory, checkTime);

        assertFalse(available);  // No debería estar disponible
    }
}

