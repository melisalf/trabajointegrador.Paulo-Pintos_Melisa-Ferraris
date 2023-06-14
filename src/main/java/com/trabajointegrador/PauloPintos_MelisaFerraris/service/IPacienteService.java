package com.trabajointegrador.PauloPintos_MelisaFerraris.service;

import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<PacienteDto> listarPacientes();


    PacienteDto buscarPacientePorId(int id);

    PacienteDto guardarPaciente(Paciente paciente);

    PacienteDto actualizarPaciente(Paciente paciente);

    void eliminarPaciente(int id);}
