package com.trabajointegrador.PauloPintos_MelisaFerraris.controller;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class PacienteController {
    private final IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    //POST
    @PostMapping("/registrar")
    public ResponseEntity<PacienteDto> registrarPaciente(@Valid @RequestBody Paciente paciente) throws BadRequestException{
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        if (pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.CREATED);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@Valid @RequestBody Paciente paciente) throws BadRequestException,ResourceNotFoundException {
        ResponseEntity<PacienteDto> respuesta;
        PacienteDto pacienteDto = pacienteService.actualizarPaciente(paciente);
        if (pacienteDto != null) respuesta = new ResponseEntity<>(pacienteDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return respuesta;
    }


    //GET LISTAR TODOS
    @GetMapping("")
    public List<PacienteDto> listarTodos() {
        return pacienteService.listarPacientes();
    }

    //GET BUSCAR POR ID
    @GetMapping("/{id}")
    public PacienteDto buscarPacientePorId(@PathVariable Long id)throws ResourceNotFoundException {
        return pacienteService.buscarPacientePorId(id);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Paciente eliminado con exito.");
    }

}


