package com.trabajointegrador.PauloPintos_MelisaFerraris.service;


import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    Odontologo buscarOdontologoPorId(int id);
    List<Odontologo> listarOdontologos();
    Odontologo registrarOdontologo(Odontologo odontologo);
    Odontologo actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(int id);
}
