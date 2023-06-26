package com.trabajointegrador.PauloPintos_MelisaFerraris.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioDto domicilioDto;

    public PacienteDto(){
    }

    public PacienteDto(Long id,String nombre, String apellido, String dni, LocalDate fechaDeIngreso, DomicilioDto domicilioDto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaDeIngreso;
        this.domicilioDto = domicilioDto;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaIngreso;
    }

    public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaIngreso = fechaDeIngreso;
    }

    public DomicilioDto getDomicilioDto() {
        return domicilioDto;
    }

    public void setDomicilioDto(DomicilioDto domicilioDto) {
        this.domicilioDto = domicilioDto;
    }


}
