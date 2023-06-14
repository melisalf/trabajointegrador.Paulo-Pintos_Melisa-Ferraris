package com.trabajointegrador.PauloPintos_MelisaFerraris.controller;

import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;

@Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/{id}")
    public TurnoDto buscarTurnoPorId(@PathVariable int id){
        return turnoService.buscarTurnoPorId(id);
    }

}
