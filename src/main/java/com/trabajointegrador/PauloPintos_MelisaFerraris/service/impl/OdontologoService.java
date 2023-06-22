package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl.OdontologoRepository;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if (odontologoEncontrado != null) {
            odontologoDto = mapper.convertValue(odontologoEncontrado, OdontologoDto.class);
            LOGGER.info("Odontologo Encontrado Con Id :{}", odontologoDto);
        } else  LOGGER.info("Odontologo No Encontrado Con Id :{}", odontologoDto);

        return odontologoDto;
    }
    @Override
    public List<OdontologoDto> listarOdontologos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        List<OdontologoDto> odontologosDto = new ArrayList<>();

        for (Odontologo odontologo : odontologos) {
            OdontologoDto odontologoDto = mapper.convertValue(odontologo, OdontologoDto.class);
            odontologosDto.add(odontologoDto);
        }

        return odontologosDto;
    }

    @Override
    public OdontologoDto registrarOdontologo(Odontologo odontologo) {
        Odontologo nuevoOdontologo= odontologoRepository.save(odontologo);
        OdontologoDto nuevoOdontologoDto = mapper.convertValue(nuevoOdontologo, OdontologoDto.class);
        LOGGER.info("Paciente Guardado Con Exito {}", nuevoOdontologoDto);

        return nuevoOdontologoDto;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {
        Odontologo odontologoActualizado = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoActualizadoDto= null;
        if (odontologoActualizado != null  )
        {
            odontologoActualizadoDto = mapper.convertValue(odontologoActualizado, OdontologoDto.class);
            LOGGER.info("Paciente Actualizado Con Exito  :{}", odontologoActualizadoDto);
        } else LOGGER.error("No Se Pudo Actualizar Los Datos Del Paciente");


        return odontologoActualizadoDto;
    }

    @Override
    public void eliminarOdontologo(Long id){
        OdontologoDto odontologoEliminado = buscarOdontologoPorId(id);
        if(odontologoEliminado != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Odontologo eliminado con exito");
        }else LOGGER.error("No fue posible eliminar el odontologo");
    }
}
