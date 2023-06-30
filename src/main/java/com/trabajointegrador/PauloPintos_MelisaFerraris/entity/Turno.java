package com.trabajointegrador.PauloPintos_MelisaFerraris.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "El paciente no puede ser nulo")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @NotNull(message = "El odontologo no puede ser nulo")
    private Odontologo odontologo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDateTime fechaHoraTurno;

    public Turno(LocalDateTime of) {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Turno(LocalDateTime fechaHoraTurno, Odontologo odontologo, Paciente paciente) {
        this.fechaHoraTurno = fechaHoraTurno;
        this.odontologo = odontologo;
        this.paciente = paciente;
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
