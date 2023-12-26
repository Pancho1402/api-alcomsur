package com.example.AlcomsurProyect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id_empresa")
    private int id;

    @Getter @Setter @Column(name = "nombre_empresa")
    private String empresa;

    @Getter @Setter @Column(name = "representante")
    private String representante;
}
