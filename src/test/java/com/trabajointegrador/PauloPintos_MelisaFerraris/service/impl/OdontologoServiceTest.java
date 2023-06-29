package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnOdontologo(){
        Odontologo odontologoARegistrar = new Odontologo("FK-4344426", "Javier", "Cornejo");
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologoARegistrar);

        Assertions.assertNotNull(odontologoDto);
        Assertions.assertNotNull(odontologoDto.getId());
    }

    @Test
    @Order(2)
    void cuandoNoSeCumpleElPatronDeLaMatricula_noDeberiaInsertarUnOdontologo(){
        Odontologo odontologoARegistrar = new Odontologo("565524", "Camila", "Perez");
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologoARegistrar));
    }

    @Test
    @Order(3)
    void cuandoNoSeCumpleElFormatoNombre_noDeberiaInsertarUnOdontologo(){
        Odontologo odontologoARegistrar = new Odontologo("565524", "Camilaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Perez");
        Assertions.assertThrows(ConstraintViolationException.class, () -> odontologoService.registrarOdontologo(odontologoARegistrar));
    }


    @Test
    @Order(4)
    void deberiaListarUnSoloOdontologo() {
        List<OdontologoDto> odontologoDtos = odontologoService.listarOdontologos();
        Assertions.assertEquals(1, odontologoDtos.size());
    }

    @Test
    @Order(5)
    void deberiaEliminarElOdontologoConId1() throws ResourceNotFoundException {
      odontologoService.eliminarOdontologo(1L);
      Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(6)
    void noDeberiaPermitirEliminarUnOdontologoQueNoSeEncuentreEnLaBaseDeDatos () {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(7)
    void comprobarQueLaListaOdontologosEstaVacia(){
        Assertions.assertTrue(odontologoService.listarOdontologos().isEmpty());
    }


}