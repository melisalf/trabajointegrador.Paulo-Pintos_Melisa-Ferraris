package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;
import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    @Override
    public OdontologoDto buscarOdontologoPorId(int id) {
        return null;
    }

    @Override
    public List<OdontologoDto> listarOdontologos() {
        return null;
    }

    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        return null;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        return null;
    }

    @Override
    public void eliminarOdontologo(int id) {

    }
}
