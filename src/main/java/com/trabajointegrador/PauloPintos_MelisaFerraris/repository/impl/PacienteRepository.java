package com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {


}
