package com.example.AlcomsurProyect.dao.repositorio;

import com.example.AlcomsurProyect.model.JoinPrestamo;
import com.example.AlcomsurProyect.model.Prestamo;

import java.util.List;

public interface PrestamoDao {
    List<JoinPrestamo> getPrestamo(String empresa, String fecha);
    boolean registrarPrestamo(JoinPrestamo prestamos);
}
