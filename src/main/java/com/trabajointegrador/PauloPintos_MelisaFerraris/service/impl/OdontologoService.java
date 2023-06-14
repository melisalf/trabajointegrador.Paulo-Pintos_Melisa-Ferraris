package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.IDao;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;
    private ObjectMapper mapper = new ObjectMapper();
@Autowired
    public OdontologoService(IDao<Odontologo> odontologoIDao, ObjectMapper mapper) {
        this.odontologoIDao = odontologoIDao;
        this.mapper = mapper;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(int id) {
    Odontologo odontologo = odontologoIDao.buscarPorId(id);
    OdontologoDto odontologoDto = mapper.convertValue(odontologo, OdontologoDto.class);
        return odontologoDto;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
    List<Odontologo> odontologos = odontologoIDao.listarTodos();
    List<OdontologoDto> odontologosDtos = new ArrayList<>();
    for (Odontologo odontologo : odontologos){
        OdontologoDto odontologoDto = mapper.convertValue(odontologo, OdontologoDto.class);
        odontologosDtos.add(odontologoDto);

    }

        return odontologosDtos;
    }

    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
    Odontologo odontologoRegistrado = odontologoIDao.guardar(odontologo);
    OdontologoDto odontologoDto = mapper.convertValue(odontologoRegistrado, OdontologoDto.class);

        return odontologoDto;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
    Odontologo odontologoActualuizado = odontologoIDao.actualizar(odontologo);
    OdontologoDto odontologoDto = mapper.convertValue(odontologoActualuizado, OdontologoDto.class);

        return odontologoDto;
    }

    @Override
    public void eliminarOdontologo(int id) {

    odontologoIDao.eliminar(id);

    }
}
