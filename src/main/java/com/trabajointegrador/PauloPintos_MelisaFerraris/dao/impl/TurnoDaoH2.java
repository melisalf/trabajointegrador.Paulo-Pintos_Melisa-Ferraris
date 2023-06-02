package com.trabajointegrador.PauloPintos_MelisaFerraris.dao.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.dao.IDao;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;

import java.util.List;

public class TurnoDaoH2 implements IDao<Turno> {

    // debemos sobreescribir los metodos.
    @Override
    public Turno guardar(Turno turno) {
        return null;
    }

    @Override
    public List<Turno> listarTodos() {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Turno buscarPorId(int id) {
        return null;
    }

    @Override
    public Turno buscarPorCriterio(String criterio) {
        return null;
    }

    @Override
    public Turno modificar(int id) {
        return null;
    }
}
