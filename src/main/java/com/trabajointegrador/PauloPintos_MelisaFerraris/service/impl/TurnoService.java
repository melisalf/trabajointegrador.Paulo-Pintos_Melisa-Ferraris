package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.ITurnoService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {


    @Override
    public TurnoDto guardarTurno(Turno turno) {
        return null;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        return null;
    }

    @Override
    public TurnoDto buscarTurnoPorId(int id) {
        return null;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        return null;
    }

    @Override
    public void eliminarTurno(int id) {

    }
}
