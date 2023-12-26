package com.example.AlcomsurProyect.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class JoinReporte {

    @Getter @Setter
    private int id;

    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private String fecha;

    @Getter @Setter
    private int idEmpresa;
    @Getter @Setter
    private String empresa;

}
