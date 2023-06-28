package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.DomicilioDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Domicilio;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl.PacienteRepository;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ObjectMapper mapper;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }


    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDto> pacienteDtos = pacientes.stream()
                .map(paciente -> {
                    DomicilioDto domicilioDto = mapper.convertValue(paciente.getDomicilio(), DomicilioDto.class);
                    return new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioDto);
                })
                .toList();

        LOGGER.info("Lista de todos los pacientes :{}", pacienteDtos);
        return pacienteDtos;

    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException{
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;

        if (pacienteBuscado != null  ) {
            DomicilioDto domicilioDto = mapper.convertValue(pacienteBuscado.getDomicilio(),DomicilioDto.class);
            pacienteDto = mapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente Encontrado : {}", pacienteDto);
        } else {LOGGER.info("No se encuentra en la base de datos el Paciente con el id indicado.");
            throw new ResourceNotFoundException("No se pudo encontrar el Paciente con el ID: " + id);
        }
        return pacienteDto;
    }


    @Override
    public PacienteDto guardarPaciente(Paciente paciente) throws BadRequestException{
        Domicilio domicilio = paciente.getDomicilio();
        PacienteDto nuevoPacienteDto = null;
        if(domicilio != null){
            Paciente nuevoPaciente= pacienteRepository.save(paciente);
            DomicilioDto domicilioDto = mapper.convertValue(domicilio,DomicilioDto.class);
            nuevoPacienteDto = mapper.convertValue(nuevoPaciente, PacienteDto.class);
            nuevoPacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente Guardado Con Exito {}", nuevoPacienteDto);
        } else {
            LOGGER.error("Se debe indicar un domicilio");
            throw new BadRequestException("Debe Especificar Un Domicilio Para El Paciente A Registar");
        }
        return nuevoPacienteDto;

    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException{
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;

        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteRepository.save(pacienteAActualizar);

            DomicilioDto domicilioDto = mapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
            pacienteActualizadoDto = mapper.convertValue(pacienteAActualizar, PacienteDto.class);
            pacienteActualizadoDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente actualizado con exito: {}",pacienteActualizadoDto);
        } else{ LOGGER.error("No se encontro el paciente con el id indicado");
            throw new ResourceNotFoundException("No Existe El Paciente Con El ID Especificado");
        }
        return pacienteActualizadoDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Paciente pacienteEliminado = pacienteRepository.findById(id).orElse(null);
        if (pacienteEliminado != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se elimino el Paciente con id: {}", id);
        }else {LOGGER.error("No se encontro el paciente con id: {}", id);
            throw new ResourceNotFoundException("No Se Encontro El Paciente Con ID: "+ id);
        }
    }
}