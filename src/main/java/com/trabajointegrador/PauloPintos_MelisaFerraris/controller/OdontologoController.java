package com.trabajointegrador.PauloPintos_MelisaFerraris.controller;


import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/odontologos")

public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

     //GET
    @GetMapping()
    public List<OdontologoDto> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException{
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if (odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity <OdontologoDto> registrarOdontologo(@Valid @RequestBody Odontologo odontologo){
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.registrarOdontologo(odontologo);
        if (odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity <OdontologoDto> actualizarOdontologo(@Valid @RequestBody Odontologo odontologo) throws BadRequestException, ResourceNotFoundException {
        ResponseEntity<OdontologoDto> respuesta;
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        if (odontologoDto != null) respuesta = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    ///DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado con exito.");
    }

}
