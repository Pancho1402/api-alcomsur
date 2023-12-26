package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.ReportarDao;
import com.example.AlcomsurProyect.model.JoinDevolucion;
import com.example.AlcomsurProyect.model.JoinReporte;
import com.example.AlcomsurProyect.model.Reportar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ReportarDaoImpl implements ReportarDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    public ReportarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JoinReporte> getReportar(String fecha, Integer empresa) {
        String sqlQuery = "SELECT a.id_reporte, a.des_reporte, a.fecha_reporte, b.nombre_empresa\n" +
                "FROM reportar_termo AS a, empresa AS b\n" +
                "WHERE a.id_empresa = b.id_empresa and a.fecha_reporte = ? AND b.id_empresa = ?;";

        List<JoinReporte> result = jdbcTemplate.query(sqlQuery,
                new Object[] { fecha , empresa},
                new JoinReporteRowMapper());

        return result;
    }
    @Override
    public boolean postReportar(Reportar reportar) {
        try {
            entityManager.merge(reportar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void deleteReportar(int id) {
        Reportar reportar = entityManager.find(Reportar.class, id);
        entityManager.remove(reportar);
    }

}
