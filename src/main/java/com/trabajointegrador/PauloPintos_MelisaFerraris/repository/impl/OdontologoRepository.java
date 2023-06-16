package com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl;



import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

}
