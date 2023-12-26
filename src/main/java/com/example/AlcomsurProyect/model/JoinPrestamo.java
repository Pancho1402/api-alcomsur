package com.example.AlcomsurProyect.model;

import lombok.Getter;
import lombok.Setter;

public class JoinPrestamo {

    @Getter @Setter
    private int id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private int idTermo;
    @Getter @Setter
    private String empresa;
    @Getter @Setter
    private String fecha;
    @Getter @Setter
    private String hora;
    @Getter @Setter
    private String codigo;
}
