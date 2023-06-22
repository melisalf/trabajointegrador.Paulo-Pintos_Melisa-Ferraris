package com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl;

import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TurnoRepository extends JpaRepository<Turno, Long> {



}
