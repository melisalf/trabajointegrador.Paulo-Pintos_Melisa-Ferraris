package com.trabajointegrador.PauloPintos_MelisaFerraris.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trabajointegrador.PauloPintos_MelisaFerraris.entity.Turno;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private String paciente;
    private String odontologo;
    private LocalDateTime fechaHoraTurno;

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

    public LocalDateTime getFecha() {
        return fechaHoraTurno;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fechaHoraTurno = fecha;
    }

    public TurnoDto(){
    }

    public TurnoDto(String paciente, String odontologo, LocalDateTime fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHoraTurno = fecha;
    }

    public static TurnoDto fromTurno (Turno turno){
        String paciente = "Nombre: " + turno.getPaciente().getNombre() +"Apellido: " + turno.getPaciente().getApellido();
        String odotologo = "Nombre: " + turno.getOdontologo().getNombre() +"Apellido: " + turno.getOdontologo().getApellido();
        return new TurnoDto(paciente,odotologo, turno.getFechaHoraTurno());
    }
}
