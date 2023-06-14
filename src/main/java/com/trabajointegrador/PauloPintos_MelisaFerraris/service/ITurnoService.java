package com.trabajointegrador.PauloPintos_MelisaFerraris.service;

import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno);

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(int id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(int id);


}