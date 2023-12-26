package com.example.AlcomsurProyect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "termo")
public class Termo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_termo")
    private int id;

    @Getter @Setter @Column(name = "cod_registro")
    private String codRegistro;

    @Getter @Setter @Column(name = "id_trabajador")
    private int idTrabajador;

    @Getter @Setter @Column(name = "id_empresa")
    private int idEmpresa;
}
