package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Domicilio;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;



    @Test
    @Order(1)
    void deberiaInsertarUnTurno() throws BadRequestException, ResourceNotFoundException {
        Paciente paciente = new Paciente("Tomas", "Gomez", "33614202", LocalDate.of(2023, 8, 12), new Domicilio("Rioja", 28469, "Rosario", "Entre Rios"));
        Odontologo odontologo = new Odontologo("FK-4344426", "Pablo", "Cornejo");

        PacienteDto pacienteDto= pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(LocalDateTime.of(LocalDate.of(2024,11,20), LocalTime.of(15, 20)), odontologo, paciente));

        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto.getId());
        Assertions.assertEquals(turnoDto.getPaciente(), "Nombre: " + pacienteDto.getNombre() + " - Apellido: " + pacienteDto.getApellido());
        Assertions.assertEquals(turnoDto.getOdontologo(),"Nombre: " + odontologoDto.getNombre() + " - Apellido: " + odontologoDto.getApellido());

    }
    @Test
    @Order(2)
    void deberiaEliminarElTurnoConId1()throws BadRequestException,ResourceNotFoundException{

        Paciente paciente = new Paciente("Tomas", "Gomez", "33614202", LocalDate.of(2023, 8, 12), new Domicilio("Rioja", 28469, "Rosario", "Entre Rios"));
        Odontologo odontologo = new Odontologo("FK-4344426", "Pablo", "Cornejo");

        PacienteDto pacienteDto= pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(LocalDateTime.of(LocalDate.of(2024,11,20), LocalTime.of(15, 20)), odontologo, paciente));
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));

    }
    @Test
    @Order(3)
    void noDeberiaInsertarUnTurno_laFechaHoraTurnoEsAnteriorAHoy() throws BadRequestException {
        Paciente paciente = new Paciente("Tomas", "Gomez", "33614202", LocalDate.of(2023, 8, 12), new Domicilio("Rioja", 28469, "Rosario", "Entre Rios"));
        Odontologo odontologo = new Odontologo("FK-4344426", "Pablo", "Cornejo");

        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        PacienteDto pacienteDto= pacienteService.guardarPaciente(paciente);

        Assertions.assertThrows(ConstraintViolationException.class, () -> turnoService.guardarTurno(new Turno(LocalDateTime.of(LocalDate.of(2022,11,20), LocalTime.of(15, 20)), odontologo, paciente)));

    }


}