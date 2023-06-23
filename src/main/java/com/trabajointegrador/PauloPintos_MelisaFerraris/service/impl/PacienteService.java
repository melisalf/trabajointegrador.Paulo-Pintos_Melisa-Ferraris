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
    public PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException, BadRequestException{
        Paciente pacienteEncontrado = pacienteRepository.findById(id).orElse(null);
        PacienteDto pacienteDto = null;

        if (pacienteEncontrado != null  ) {
            DomicilioDto domicilioDto = mapper.convertValue(pacienteEncontrado.getDomicilio(),DomicilioDto.class);
            pacienteDto = mapper.convertValue(pacienteEncontrado, PacienteDto.class);
            pacienteDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente Encontrado Con Id :{}", pacienteDto);

        } if (pacienteEncontrado.getId() != pacienteDto.getId()){
            throw new ResourceNotFoundException("No Se Encontro Un Paciente Con El Id Especificado");
        }else if (pacienteEncontrado.getId() == null ){
            throw new BadRequestException("Ingrese Un ID Valido");}

        return pacienteDto;
    }

    @Override
    public PacienteDto guardarPaciente(Paciente paciente) throws BadRequestException{
        Paciente nuevoPaciente= pacienteRepository.save(paciente);
        DomicilioDto domicilioDto = mapper.convertValue(nuevoPaciente.getDomicilio(),DomicilioDto.class);
        PacienteDto nuevoPacienteDto = mapper.convertValue(nuevoPaciente, PacienteDto.class);
        nuevoPacienteDto.setDomicilioDto(domicilioDto);
        LOGGER.info("Paciente Guardado Con Exito {}", nuevoPacienteDto);
        if(nuevoPaciente.getDomicilio() == null ){
            throw new BadRequestException("Debe Especificar Un Domicilio Para El Paciente A Registar");

        }
        return nuevoPacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) throws BadRequestException, ResourceNotFoundException{
        Paciente pacienteAActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;

        if (pacienteAActualizar != null) {
            pacienteAActualizar = paciente;
            pacienteRepository.save(pacienteAActualizar);

            DomicilioDto domicilioDto = mapper.convertValue(pacienteAActualizar.getDomicilio(), DomicilioDto.class);
            pacienteActualizadoDto = mapper.convertValue(pacienteAActualizar, PacienteDto.class);
            pacienteActualizadoDto.setDomicilioDto(domicilioDto);
            LOGGER.info("Paciente actualizado con exito: {}",pacienteActualizadoDto);
        } if(paciente.getId()!= pacienteAActualizar.getId()){

            throw new BadRequestException("No Se Encontro El Paciente A Actualizar");
        }

        else if (pacienteAActualizar== null){

            throw new ResourceNotFoundException("No Existe El Paciente Con El ID Especificado");
        }
        return pacienteActualizadoDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Paciente pacienteEliminado = pacienteRepository.findById(id).orElse(null);
        if (pacienteEliminado != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Paciente Eliminado Con Exito");
        }else {LOGGER.error("No se pudo eliminar el paciente");
            throw new ResourceNotFoundException("No Se Encontro El Paciente Con ID: "+ id);
        }
    }
}
