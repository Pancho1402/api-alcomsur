package com.example.AlcomsurProyect.dao.repositorio;


import com.example.AlcomsurProyect.model.JoinReporte;
import com.example.AlcomsurProyect.model.Reportar;
import com.example.AlcomsurProyect.model.Trabajador;

import java.util.List;

public interface ReportarDao {
    List<JoinReporte> getReportar(String fecha, Integer empresa);

    boolean postReportar(Reportar reportar);

    void deleteReportar(int id);
}
