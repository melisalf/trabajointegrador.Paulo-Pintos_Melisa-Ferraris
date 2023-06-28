package com.trabajointegrador.PauloPintos_MelisaFerraris.service;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno) throws ResourceNotFoundException, BadRequestException;
    List<TurnoDto> listarTodos();
    TurnoDto buscarTurnoPorId(Long id) throws ResourceNotFoundException;
    TurnoDto actualizarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException;
    void eliminarTurno(Long id) throws ResourceNotFoundException;


}