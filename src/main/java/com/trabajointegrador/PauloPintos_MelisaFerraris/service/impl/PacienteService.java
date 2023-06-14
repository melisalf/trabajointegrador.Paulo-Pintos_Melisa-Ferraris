package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.IDao;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private IDao<Paciente> pacienteIDao;
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao, ObjectMapper mapper) {
        this.pacienteIDao = pacienteIDao;
        this.mapper = mapper;
    }



    @Override
    public List<PacienteDto> listarPacientes() {
        List<Paciente> pacientes= pacienteIDao.listarTodos();
        List<PacienteDto> pacientesDtos= new ArrayList<>();


        for (Paciente paciente : pacientes){
            PacienteDto pacienteDto= mapper.convertValue(pacientesDtos, PacienteDto.class);
            pacientesDtos.add(pacienteDto);

        }

        return pacientesDtos;
    }


    @Override
    public PacienteDto buscarPacientePorId(int id) {
      Paciente paciente = pacienteIDao.buscarPorId(id);
      PacienteDto pacienteDto = mapper.convertValue(paciente, PacienteDto.class);
      return pacienteDto;
    }

    @Override
    public PacienteDto guardarPaciente(Paciente paciente) {
        Paciente pacienteGuardado = pacienteIDao.guardar(paciente);
        PacienteDto pacienteDto = mapper.convertValue(pacienteGuardado, PacienteDto.class);




        return pacienteDto;
    }

    @Override
    public PacienteDto actualizarPaciente(Paciente paciente) {
        Paciente pacienteActualizado = pacienteIDao.actualizar(paciente);
        PacienteDto pacienteDto = mapper.convertValue(pacienteActualizado, PacienteDto.class);


        return pacienteDto;
    }

    @Override
    public void eliminarPaciente(int id) {
        pacienteIDao.eliminar(id);

    }



}
