package com.example.AlcomsurProyect.dao.repositorio;

import com.example.AlcomsurProyect.model.Empresa;

import java.util.List;

public interface EmpresaDao {
    List<Empresa> mostrarEmpresas();
    Empresa mostrarEmpresa(int id);

    void agregarEmpresas(Empresa empresa);
    void editarEmpresa(Empresa empresa);

    void eliminarEmpresa(int id);

}
