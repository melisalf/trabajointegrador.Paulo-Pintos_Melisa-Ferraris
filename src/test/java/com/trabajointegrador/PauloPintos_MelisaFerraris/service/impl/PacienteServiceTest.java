package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {


        @Autowired
        private PacienteService pacienteService;

        @Autowired
        private OdontologoService odontologoService;

        @Test
        @Order(1)
        void deberiaInsertarUnOdontologo(){
                Odontologo odontologoARegistrar = new Odontologo("FA-565524", "Camila", "Perez");
                OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoARegistrar);

                Assertions.assertNotNull(odontologoDto);
                Assertions.assertNotNull(odontologoDto.getId());
        }

        @Test
        @Order(2)
        void cuandoNoSeCumpleElPatronDeLaMatriculaNoDeberiaInsertarUnOdontologo(){
                Odontologo odontologoARegistrar = new Odontologo("565524", "Camila", "Perez");

                Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologoARegistrar));
        }

        @Test
        @Order(3)
        void deberiaListarUnSoloOdontologo() {
                List<OdontologoDto> odontologoDtos = odontologoService.listarOdontologos();
                assertEquals(1, odontologoDtos.size());
        }

        @Test
        @Order(4)
        void deberiaEliminarElOdontologoConId1() {

                List<OdontologoDto> odontologoDtos = odontologoService.listarOdontologos();
                assertEquals(0, odontologoDtos.size());
        }

    /*
    @Test
    @Order(5)
    void noDeberiaPermitirEliminarUnOdontologoQueNoSeEncuentreEnLaBaseDeDatos () {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1);
    }

     */



}