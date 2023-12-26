package com.example.AlcomsurProyect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "devolucion")
public class Devolucion {
    @Id
    @Getter @Setter @Column(name = "id_devolucion")
    private int id;

    @Getter @Setter @Column(name = "id_termo")
    private int idTermo;

    @Getter @Setter @Column(name = "fecha_devolucion")
    private String fecha;

    @Getter @Setter @Column(name = "hora_devolucion")
    private String hora;

}
