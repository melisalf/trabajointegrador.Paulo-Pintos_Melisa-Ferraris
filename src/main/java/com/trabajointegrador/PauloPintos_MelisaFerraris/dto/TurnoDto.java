package com.trabajointegrador.PauloPintos_MelisaFerraris.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private String paciente;
    private String odontologo;
    private LocalDateTime fechaHoraTurno;

    public TurnoDto(){

    }

    public TurnoDto(Long id, String paciente, String odontologo, LocalDateTime fechaHoraTurno) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHoraTurno = fechaHoraTurno;
    }

    public Long getId() {
        return id;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(String odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaHoraTurno() {
        return fechaHoraTurno;
    }

    public void setFechaHoraTurno(LocalDateTime fechaHoraTurno) {
        this.fechaHoraTurno = fechaHoraTurno;
    }

    public static TurnoDto fromTurno (Turno turno){
        String paciente = "Nombre: " + turno.getPaciente().getNombre() +" - Apellido: " + turno.getPaciente().getApellido();
        String odontologo = "Nombre: " + turno.getOdontologo().getNombre() +" - Apellido: " + turno.getOdontologo().getApellido();
        return new TurnoDto(turno.getId(), paciente,odontologo, turno.getFechaHoraTurno());
    }
}
