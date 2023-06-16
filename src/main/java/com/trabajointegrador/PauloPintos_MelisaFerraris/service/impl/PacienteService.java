package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.DomicilioDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Domicilio;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
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
    private PacienteRepository pacienteRepository;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired

    public PacienteService(PacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }


    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<PacienteDto> pacienteDtos = pacientes.stream().map(paciente -> {
            Domicilio dom = paciente.getDomicilio();
            DomicilioDto domicilioDto = mapper.convertValue(dom, DomicilioDto.class);
            return new PacienteDto(paciente.getId(), paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), domicilioDto);
        }).toList();
        LOGGER.info("Lista de todos los pacientes :{}", pacienteDtos);
        return pacienteDtos;

    }

    @Override
    public PacienteDto buscarPacientePorId(Long id) {
        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;

        if (pacienteEncontrado != null  ) {
            DomicilioDto domicilioDto = mapper.convertValue(pacienteEncontrado.getDomicilio(),DomicilioDto.class);
            pacienteDto = mapper.convertValue(pacienteEncontrado, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente Encontrado Con Id :{}", pacienteDto);

        } else LOGGER.error("No Se Encontro Paciente Con El ID Indicado");



        return pacienteDto;
    }

    @Override
    public PacienteDto guardarPaciente(Paciente paciente) {
        Paciente nuevoPaciente= pacienteRepository.save(paciente);
        DomicilioDto domicilioDto = mapper.convertValue(nuevoPaciente.getDomicilio(),DomicilioDto.class);
        PacienteDto nuevoPacienteDto = mapper.convertValue(nuevoPaciente, PacienteDto.class);
        nuevoPacienteDto.setDomicilioDto(domicilioDto);
        LOGGER.info("Paciente Guardado Con Exito {}", nuevoPacienteDto);

        return nuevoPacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        Paciente pacienteActualizado = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto= null;
        if (pacienteActualizado != null  ) {
            DomicilioDto domicilioDto = mapper.convertValue(pacienteActualizado.getDomicilio(),DomicilioDto.class);
            pacienteActualizadoDto = mapper.convertValue(pacienteActualizado, PacienteDto.class);
            pacienteActualizadoDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente Actualizado Con Exito  :{}", pacienteActualizadoDto);}
        else LOGGER.error("No Se Pudo Actualizar Los Datos Del Paciente");


        return pacienteActualizadoDto;
    }

    @Override
    public void eliminarPaciente(Long id) {

        pacienteRepository.deleteById(id);
        LOGGER.warn("Paciente Eliminado Con Exito");

    }
}
