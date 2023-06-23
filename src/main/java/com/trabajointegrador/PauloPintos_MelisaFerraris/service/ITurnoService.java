package com.trabajointegrador.PauloPintos_MelisaFerraris.service;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno) throws ResourceNotFoundException;
    List<TurnoDto> listarTodos();
    TurnoDto buscarTurnoPorId(Long id);
    TurnoDto actualizarTurno(Turno turno);
    void eliminarTurno(Long id) throws ResourceNotFoundException;


}