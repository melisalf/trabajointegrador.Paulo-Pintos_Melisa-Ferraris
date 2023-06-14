package com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.IDao;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {


    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoDaoH2.class);
    private final PacienteDaoH2 pacienteDaoH2;
    private final OdontologoDaoH2 odontologoDaoH2;

    private List<Turno> turnoRepository = new ArrayList<>();
@Autowired
    public TurnoDaoH2(PacienteDaoH2 pacienteDaoH2, OdontologoDaoH2 odontologoDaoH2) {
        this.pacienteDaoH2 = pacienteDaoH2;
        this.odontologoDaoH2 = odontologoDaoH2;
    }

    // debemos sobreescribir los metodos.
    @Override
    public Turno guardar(Turno turno) {
    Turno turnoGuardado = null;

        Paciente paciente = pacienteDaoH2.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoDaoH2.buscarPorId(turno.getOdontologo().getId());
        if (paciente != null && odontologo != null){
            turnoGuardado = turno;
            turnoRepository.add(turnoGuardado);
            LOGGER.info("Turno Guardado Con Exito :"+ turnoGuardado);

        }else LOGGER.error("No Se Guardo Un Turno");

        return turnoGuardado;
    }

    @Override
    public List<Turno> listarTodos() {
        LOGGER.info("Turnos Guardados Son: "+ turnoRepository);
        return turnoRepository;
    }

    @Override
    public void eliminar(int id) {

    Turno turno = buscarPorId(id);
    turnoRepository.remove(turno);

        LOGGER.info("Se ha Encontrado y Eliminado El Turno" + id);

    }

    @Override
    public Turno buscarPorId(int id) {
    return (Turno) turnoRepository.stream().filter(t-> t.getId() == id);
    }

    @Override
    public Turno buscarPorCriterio(String criterio) {
        return null;
    }

    @Override
    public Turno actualizar(Turno turno) {
        turnoRepository.set(turnoRepository.indexOf(buscarPorId(turno.getId())), turno);
        return turno;
    }
}
