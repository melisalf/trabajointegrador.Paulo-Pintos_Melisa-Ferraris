package com.trabajointegrador.PauloPintos_MelisaFerraris.service;


import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    OdontologoDto buscarOdontologoPorId(int id);
    List<OdontologoDto> listarOdontologos();
    OdontologoDto registrarOdontologo(Odontologo odontologo);
    OdontologoDto actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(int id);
}
