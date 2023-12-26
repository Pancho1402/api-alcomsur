package com.example.AlcomsurProyect.dao.repositorio;

import com.example.AlcomsurProyect.model.Devolucion;
import com.example.AlcomsurProyect.model.JoinDevolucion;

import java.util.List;

public interface DevolucionDao {
    List<JoinDevolucion> getDevolucion(String empresa, String fecha);
    boolean postDevolucion(JoinDevolucion devolucion);
}
