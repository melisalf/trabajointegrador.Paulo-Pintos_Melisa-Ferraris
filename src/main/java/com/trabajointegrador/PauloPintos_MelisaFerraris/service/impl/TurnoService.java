package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl.TurnoRepository;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) throws ResourceNotFoundException {
        Turno turnoGuardado = null;
        TurnoDto turnoGuardadoDto = null;
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if (odontologoDto != null && pacienteDto != null) {
            turnoGuardado = turno;
            turnoGuardadoDto = TurnoDto.fromTurno(turnoRepository.save(turnoGuardado));
            LOGGER.info("Turno Guardado Con Exito {}", turnoGuardadoDto);
        } else LOGGER.error("No se puede registrar el turno");

        return turnoGuardadoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<TurnoDto> listaTurnosDto = turnoRepository.findAll().stream().map(TurnoDto::fromTurno).toList();
        return listaTurnosDto;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoBuscadoDto = TurnoDto.fromTurno(turnoBuscado);
        return turnoBuscadoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        Turno turnoActualizado = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;
        if (turnoActualizado != null) {
            turnoActualizadoDto = TurnoDto.fromTurno(turnoActualizado);
            LOGGER.info("Turno registrado con exito : {}", turnoActualizadoDto);
        } else LOGGER.error("El turno no se pudo actualizar");
        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(buscarTurnoPorId(id) != null){
        turnoRepository.deleteById(id);
        LOGGER.warn("Turno Eliminado Con Exito");
        }else {LOGGER.error("El turno  no se pudo eliminar");
        throw new ResourceNotFoundException(" No Se Encontro El Turno Con ID: "+ id);
        }
    }
}
