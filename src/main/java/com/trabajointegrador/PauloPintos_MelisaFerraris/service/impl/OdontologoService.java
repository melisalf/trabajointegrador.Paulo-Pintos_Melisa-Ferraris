package com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trabajointegrador.PauloPintos_MelisaFerraris.dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.BadRequestException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.exceptions.ResourceNotFoundException;
import com.trabajointegrador.PauloPintos_MelisaFerraris.repository.impl.OdontologoRepository;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ObjectMapper mapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    @Override
    public OdontologoDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;

        if (odontologoEncontrado != null) {
            odontologoDto = mapper.convertValue(odontologoEncontrado, OdontologoDto.class);
            LOGGER.info("Odontologo Encontrado :{}", odontologoDto);
        } else  {LOGGER.info("Odontologo No Encontrado:{}", odontologoDto);
        throw new ResourceNotFoundException("No Se Pudo Encontrar El Odontologo Con El ID: "+ id);
        }

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
        LOGGER.info("Odontologo Registrado Con Exito {}", nuevoOdontologoDto);

        return nuevoOdontologoDto;
    }

    @Override
    public OdontologoDto actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException, BadRequestException {
        Odontologo odontologoActualizado = odontologoRepository.findById(odontologo.getId()).orElse(null);

        OdontologoDto odontologoActualizadoDto= null;

        if (odontologoActualizado != null  )
        { odontologoActualizado = odontologo;
            odontologoRepository.save(odontologoActualizado);odontologoActualizadoDto = mapper.convertValue  (odontologoActualizado, OdontologoDto.class);
            LOGGER.info("Odontologo Actualizado Con Exito  :{}", odontologoActualizadoDto);}

        if (odontologo.getId() != odontologoActualizado.getId()){
            throw new ResourceNotFoundException("No Se Encontro El Odontologo A Actualziar");

        } else if (odontologoActualizado == null){
            throw new BadRequestException("No Se Puede Actualizar El Odontologo Porque No Se Especifico ID");
           }


        return odontologoActualizadoDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se elimino el Odontologo con id: {}", id);
        }else {LOGGER.error("No se encontro el odontologo con id: {}", id);
        throw new ResourceNotFoundException("No Se Encontro El Odontologo Con el ID: "+ id);
        }
    }
}
