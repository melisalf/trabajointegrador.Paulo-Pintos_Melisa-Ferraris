package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Domicilio;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

        @Autowired
        private PacienteService pacienteService;

        @Test
        @Order(1)
        void deberiaPoderRegristrarUnPaciente() throws BadRequestException {
                Domicilio domicilio = new Domicilio("Sabattini", 1111, "Rio Cuarto", "Cordoba");
                Paciente pacienteAGuardar = new Paciente("Federico", "Gomez", "3361420873", LocalDate.of(2024, 10, 15), domicilio);
                PacienteDto pacienteDto = pacienteService.guardarPaciente(pacienteAGuardar);

                Assertions.assertNotNull(pacienteDto);
                Assertions.assertNotNull(pacienteDto.getId());
        }

        @Test
        @Order(2)
        void noDeberiaRegistrarPaciente_porQueDomicilioEsNull() throws BadRequestException {
                Paciente pacienteAGuardar = new Paciente("Federico", "Gomez", "3361420873", LocalDate.of(2024, 10, 15), null);
                Assertions.assertThrows(BadRequestException.class, () -> pacienteService.guardarPaciente(pacienteAGuardar));
        }

        @Test
        @Order(3)
        void noDeberiaRegistrarPaciente_porQueElFormatoDniEsIncorrecto() {
                Paciente pacienteAGuardar = new Paciente("Federico", "Gomez", "33614208732", LocalDate.of(2023, 02, 12), new Domicilio("Rioja", 28469, "Rosario", "Entre Rios"));

                Assertions.assertThrows(ConstraintViolationException.class, () -> pacienteService.guardarPaciente(pacienteAGuardar));

        }

        @Test
        @Order(4)
        void deberiaListarUnSoloPaciente() {
                List<PacienteDto> pacienteDtos = pacienteService.listarPacientes();
                Assertions.assertEquals(1, pacienteDtos.size());
        }

        @Test
        @Order(5)
        void deberiaEliminarElPacienteoConId1() throws ResourceNotFoundException {
                pacienteService.eliminarPaciente(1L);
                Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
        }

        @Test
        @Order(6)
        void noDeberiaPermitirEliminarUnOdontologoQueNoSeEncuentreEnLaBaseDeDatos () {
                Assertions.assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
        }

        @Test
        @Order(7)
        void volverAComprobarQueLaListaPacienteEstaVacia(){
                Assertions.assertTrue(pacienteService.listarPacientes().isEmpty());
        }
}

