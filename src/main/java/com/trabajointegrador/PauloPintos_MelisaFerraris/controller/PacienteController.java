package com.trabajointegrador.PauloPintos_MelisaFerraris.controller;

import com.trabajointegrador.PauloPintos_MelisaFerraris.Dto.PacienteDto;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Paciente;
import com.trabajointegrador.PauloPintos_MelisaFerraris.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /*@GetMapping("/index")
    public String buscarPacientePorDni(Model model, @RequestParam("dni") String dni){
        Paciente paciente = pacienteService.buscarPacientePorDni(dni);

        //agregar los atributos del objeto al modelo que mostraremos en la vista
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());

        return "index";
    }*/

    //POST
    @PostMapping("/registrar")
    public PacienteDto registrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);

    }

    //PUT
    @PutMapping("/actualizar")
    public PacienteDto actualizarPaciente(@RequestBody Paciente paciente){
        return pacienteService.actualizarPaciente(paciente);
    }

    //GET
    @GetMapping
    public List<PacienteDto> listarTodos(){
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public PacienteDto buscarPacientePorId(@PathVariable Long id){
        return pacienteService.buscarPacientePorId(id);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPaciente(@PathVariable Long id){
        pacienteService.eliminarPaciente(id);
    }

}
