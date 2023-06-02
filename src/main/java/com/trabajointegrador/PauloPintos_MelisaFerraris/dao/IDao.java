package com.trabajointegrador.PauloPintos_MelisaFerraris.dao;

import java.util.List;

public interface IDao<T> {
    //guardar (registrar, almacenar), buscarlos, moficarlos (actualizarlos), eliminarlos y listarlos.

    T guardar(T t);

    List<T> listarTodos();

    void eliminar(int id);

    T buscarPorId(int id);

    //es un metodo generico, para reutilizar
    T buscarPorCriterio(String criterio);

    T modificar(int id);
}
