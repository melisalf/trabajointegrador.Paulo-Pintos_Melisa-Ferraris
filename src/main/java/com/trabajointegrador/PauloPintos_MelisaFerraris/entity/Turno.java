package com.trabajointegrador.PauloPintos_MelisaFerraris.entity;

import java.time.LocalDateTime;

public class Turno {

    private int id;
    private LocalDateTime fechaHoraTurno;
    private Odontologo odontologo;

    public Turno(LocalDateTime fechaHoraTurno, Odontologo odontologo) {
        this.fechaHoraTurno = fechaHoraTurno;
        this.odontologo = odontologo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Id: " + id + " - Fecha turno: " + fechaHoraTurno + " - Odontologo: " + odontologo;
    }
}
