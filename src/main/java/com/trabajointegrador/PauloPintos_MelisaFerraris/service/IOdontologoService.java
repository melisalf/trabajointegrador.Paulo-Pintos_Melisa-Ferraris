package com.trabajointegrador.PauloPintos_MelisaFerraris.service;


import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException;

    List<OdontologoDto> listarOdontologos();

    OdontologoDto registrarOdontologo(Odontologo odontologo);

    OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}