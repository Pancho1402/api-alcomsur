package com.example.AlcomsurProyect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "reportar_termo")
public class Reportar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_reporte")
    private int id;

    @Getter @Setter @Column(name = "des_reporte")
    private String descripcion;

    @Getter @Setter @Column(name = "id_empresa")
    private int idEmpresa;

    @Getter @Setter @Column(name = "fecha_reporte")
    private String fecha;
}
