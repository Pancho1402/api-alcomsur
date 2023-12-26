package com.example.AlcomsurProyect.dao.repositorio;

import com.example.AlcomsurProyect.model.JoinPersonal;
import com.example.AlcomsurProyect.model.Termo;

import java.util.List;

public interface TermoDao {
    String postTermo (JoinPersonal termo);
    void eliminarTermo(int id);

}
