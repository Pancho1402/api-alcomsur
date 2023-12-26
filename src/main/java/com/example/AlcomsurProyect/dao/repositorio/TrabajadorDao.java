package com.example.AlcomsurProyect.dao.repositorio;

import com.example.AlcomsurProyect.model.Empresa;
import com.example.AlcomsurProyect.model.JoinPersonal;
import com.example.AlcomsurProyect.model.Trabajador;

import java.util.List;

public interface TrabajadorDao {
    List<JoinPersonal> getTrabajador(String empresa);
    Trabajador getTrabajador(int id);
    void postTrabajador(Trabajador trabajador);
    void putTrabajador(Trabajador trabajador);

    void deleteTrabajador(int id);
}
