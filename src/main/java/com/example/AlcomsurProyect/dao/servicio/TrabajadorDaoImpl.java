package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.TermoDao;
import com.example.AlcomsurProyect.dao.repositorio.TrabajadorDao;
import com.example.AlcomsurProyect.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TrabajadorDaoImpl implements TrabajadorDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    TermoDao dao;

    private final JdbcTemplate jdbcTemplate;

    public TrabajadorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JoinPersonal> getTrabajador(String empresa) {
        String sqlQuery = "Select  a.nombre_empresa, b.nombre_trabajador, b.id_empresa, b.id_trabajador from empresa as a, trabajador as b\n" +
                "where a.id_empresa = b.id_empresa and a.nombre_empresa = ?;";


        List<JoinPersonal> result = jdbcTemplate.query(sqlQuery,
                new Object[] { empresa },
                new JoinPersonalRowMapper());
        return result;
    }

    @Override
    public Trabajador getTrabajador(int id) {
        return entityManager.find(Trabajador.class, id);
    }

    @Override
    public void postTrabajador(Trabajador trabajador) {
        entityManager.merge(trabajador);
    }

    @Override
    public void putTrabajador(Trabajador trabajador) {
        entityManager.merge(trabajador);
    }

    @Override
    public void deleteTrabajador(int id) {
        String query = "";

        Trabajador trabajador = entityManager.find(Trabajador.class, id);

        query = "FROM Termo WHERE idTrabajador = :id";
        List<Termo> lista = entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();

        if(!lista.isEmpty()){
            dao.eliminarTermo(lista.get(0).getId());
        }

        entityManager.remove(trabajador);

    }
}
