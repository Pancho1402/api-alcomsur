package com.example.AlcomsurProyect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "prestamo")
public class Prestamo {
    @Id
    @Getter @Setter @Column(name = "id_prestamo")
    private int id;

    @Getter @Setter @Column(name = "id_termo")
    private int idTermo;

    @Getter @Setter @Column(name = "fecha_prestamo")
    private String fecha;

    @Getter @Setter @Column(name = "hora_prestamo")
    private String hora;

}
