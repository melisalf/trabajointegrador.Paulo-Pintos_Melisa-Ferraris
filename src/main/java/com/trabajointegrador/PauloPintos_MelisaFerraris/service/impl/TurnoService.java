package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.TurnoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
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
    public TurnoDto guardarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException {
        TurnoDto turnoDto = null;
        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if(paciente == null || odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            }
            else if (paciente == null){
                LOGGER.error("El paciente no se encuentra en nuestra base de datos");
                throw new BadRequestException("El paciente no se encuentra en nuestra base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en nuestra base de datos");
                throw new BadRequestException("El odontologo no se encuentra en nuestra base de datos");
            }

        } else {
            turnoDto = TurnoDto.fromTurno(turnoRepository.save(turno));
            LOGGER.info("Nuevo turno registrado con exito");
        }

        return turnoDto;
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<TurnoDto> listaTurnosDto = turnoRepository.findAll().stream().map(TurnoDto::fromTurno).toList();
        return listaTurnosDto;
    }

    @Override
    public TurnoDto buscarTurnoPorId(Long id) throws BadRequestException {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoBuscadoDto = TurnoDto.fromTurno(turnoBuscado);
        if (turnoBuscado.getId() == null){
            throw new BadRequestException("Debe Ingresa Un ID Valido");
        }
        return turnoBuscadoDto;
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) throws BadRequestException, ResourceNotFoundException {
        Turno turnoActualizado = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;
        if (turnoActualizado != null) {
            turnoActualizadoDto = TurnoDto.fromTurno(turnoActualizado);
            LOGGER.info("Turno registrado con exito : {}", turnoActualizadoDto);
        } if(turnoActualizado.getId() != turno.getId()){
            throw new ResourceNotFoundException("No Se Encontro El Turno Especificado");


        }else if(turnoActualizado.getId() == null) {

            throw new BadRequestException("Tiene Que Indicar Un ID Valido");
        }
        return turnoActualizadoDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException, BadRequestException {
        if(buscarTurnoPorId(id) != null){
        turnoRepository.deleteById(id);
        LOGGER.warn("Turno Eliminado Con Exito");
        }else {LOGGER.error("El turno  no se pudo eliminar");
        throw new ResourceNotFoundException(" No Se Encontro El Turno Con ID: "+ id);
        }
    }
}
