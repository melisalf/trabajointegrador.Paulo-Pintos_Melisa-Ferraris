package com.trabajointegrador.PauloPintos_MelisaFerraris.service;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();

    PacienteDto buscarPacientePorId(Long id) throws ResourceNotFoundException;

    PacienteDto guardarPaciente(Paciente paciente) throws BadRequestException;

    PacienteDto actualizarPaciente(Paciente paciente) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
