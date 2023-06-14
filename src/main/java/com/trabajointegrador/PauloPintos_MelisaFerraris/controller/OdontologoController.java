package com.trabajointegrador.PauloPintos_MelisaFerraris.controller;

import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.OdontologoDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Odontologo;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    /*@GetMapping("/index")
    public String buscarOdontologo(Model model, @RequestParam("id") int id) {
        Odontologo odontologo = odontologoService.buscarOdontologoPorId(id);

        //agregar los atributos del objeto al modelo que mostraremos en la vista
        model.addAttribute("matricula", odontologo.getMatricula());
        return "index";
    }*/


    //GET
    @GetMapping()
    public List<OdontologoDto> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public OdontologoDto buscarOdontologoPorId(@PathVariable int id){
        return odontologoService.buscarOdontologoPorId(id);
    }

    //POST
    @PostMapping("/registrar")
    public OdontologoDto registrarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.registrarOdontologo(odontologo);
    }

    //PUT
    @PutMapping("/actualizar")
    public OdontologoDto actualizarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.actualizarOdontologo(odontologo);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable int id){
        odontologoService.eliminarOdontologo(id);
    }

}
