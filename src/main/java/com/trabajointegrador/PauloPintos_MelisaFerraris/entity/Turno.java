package com.trabajointegrador.PauloPintos_MelisaFerraris.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="TURNOS")
public class Turno {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Paciente paciente;
    private LocalDateTime fechaHoraTurno;
    private Odontologo odontologo;


public Turno(){

}
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Turno(LocalDateTime fechaHoraTurno, Odontologo odontologo) {
        this.fechaHoraTurno = fechaHoraTurno;
        this.odontologo = odontologo;
    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getFechaHoraTurno() {
        return fechaHoraTurno;
    }

    public void setFechaHoraTurno(LocalDateTime fechaHoraTurno) {
        this.fechaHoraTurno = fechaHoraTurno;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Fecha turno: " + fechaHoraTurno + " - Odontologo: " + odontologo + " - Paciente: " + paciente;
    }
}
