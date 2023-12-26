package com.example.AlcomsurProyect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_trabajador")
    private int id;
    @Getter @Setter @Column(name = "nombre_trabajador")
    private String nombre;
    @Getter @Setter @Column(name = "id_empresa")
    private int idEmpresa;

}
