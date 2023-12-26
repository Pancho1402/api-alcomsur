package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.EmpresaDao;
import com.example.AlcomsurProyect.dao.repositorio.ReportarDao;
import com.example.AlcomsurProyect.dao.repositorio.TrabajadorDao;
import com.example.AlcomsurProyect.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Service
@Transactional
public class EmpresaDaoImpl implements EmpresaDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    TrabajadorDao dao;
    @Autowired
    ReportarDao reportarDao;
    @Override
    public List<Empresa> mostrarEmpresas() {
        String query = "FROM Empresa";
        return entityManager.createQuery(query).getResultList();
    }
    @Override
    public Empresa mostrarEmpresa(int id) {
        return entityManager.find(Empresa.class, id);
    }

    @Override
    public void agregarEmpresas(Empresa empresa) {
        entityManager.merge(empresa);
    }
    @Override
    public void editarEmpresa(Empresa empresa) {
        entityManager.merge(empresa);
    }

    @Override
    public void eliminarEmpresa(int id) {

        String query = "FROM Trabajador WHERE idEmpresa = :idEmpresa";
        List<Trabajador> listaTrabajador = entityManager.createQuery(query)
                .setParameter("idEmpresa", id)
                .getResultList();

        if(!listaTrabajador.isEmpty()){
            for(Trabajador t : listaTrabajador){
                dao.deleteTrabajador(t.getId());
            }
        }
        query = " FROM Reportar WHERE idEmpresa = :idEmpresa";
        List<Reportar> listaReporte = entityManager.createQuery(query)
                .setParameter("idEmpresa", id)
                .getResultList();

        if(!listaReporte.isEmpty()){
            for(Reportar r : listaReporte){
                reportarDao.deleteReportar(r.getId());

            }
        }

        Empresa empresa = entityManager.find(Empresa.class, id);
        entityManager.remove(empresa);
    }

}
