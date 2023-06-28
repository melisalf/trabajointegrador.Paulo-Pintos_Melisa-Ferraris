package com.trabajointegrador.PauloPintos_MelisaFerraris.controller;


import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //GET BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), null, HttpStatus.OK);
    }


    //GET LISTAR TODOS
    @GetMapping()
    public List<TurnoDto> listarTurnos() {
        return turnoService.listarTodos();
    }

    //POST REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<TurnoDto> guardarTurno(@Valid @RequestBody Turno turno) throws ResourceNotFoundException, BadRequestException {
        return new ResponseEntity<>(turnoService.guardarTurno(turno), null, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarPaciente(@Valid @RequestBody Turno turno) throws BadRequestException, ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.actualizarTurno(turno), null, HttpStatus.OK);
    }


}