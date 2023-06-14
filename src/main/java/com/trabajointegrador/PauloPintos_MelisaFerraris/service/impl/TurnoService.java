package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.IDao;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl.TurnoDaoH2;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private IDao<Turno> turnoIDao;
    private ObjectMapper objectMapper;
@Autowired
    public TurnoService(IDao<Turno> turnoIDao, ObjectMapper objectMapper) {
        this.turnoIDao = turnoIDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.guardar(turno));
    }

    @Override
    public List<TurnoDto> listarTodos() {
        return turnoIDao.listarTodos().stream().map(TurnoDto::fromTurno).toList();

    }

    @Override
    public TurnoDto buscarTurnoPorId(int id) {
        return TurnoDto.fromTurno(turnoIDao.buscarPorId(id));
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoIDao.actualizar(turno));
    }

    @Override
    public void eliminarTurno(int id) {
     turnoIDao.eliminar(id);
    }
}
