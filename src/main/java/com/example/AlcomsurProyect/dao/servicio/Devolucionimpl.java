package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.DevolucionDao;
import com.example.AlcomsurProyect.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Service
@Transactional
public class Devolucionimpl implements DevolucionDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    public Devolucionimpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JoinDevolucion> getDevolucion(String empresa, String fecha) {
        String sqlQuery = "SELECT c.id_termo,a.id_devolucion, a.fecha_devolucion, a.hora_devolucion, b.nombre_empresa, d.nombre_trabajador\n" +
                "FROM devolucion AS a\n" +
                "JOIN termo AS c ON a.id_termo = c.id_termo\n" +
                "JOIN empresa AS b ON c.id_empresa = b.id_empresa\n" +
                "JOIN trabajador AS d ON c.id_trabajador = d.id_trabajador\n" +
                "WHERE b.nombre_empresa = ? AND a.fecha_devolucion = ?";

        List<JoinDevolucion> result = jdbcTemplate.query(sqlQuery,
                new Object[] { empresa, fecha},
                new JoinDevolucionRowMapper());
        return result;
    }

    @Override

    public boolean postDevolucion(JoinDevolucion devolucion) {
        try {
            String query = "FROM Termo WHERE codRegistro = :codRegistro";

            List<Termo> lista = entityManager.createQuery(query)
                    .setParameter("codRegistro", devolucion.getCodigo())
                    .getResultList();

            if(!lista.isEmpty()) {
                int idTermo = lista.get(0).getId();

                Devolucion newDevolucion = new Devolucion();
                newDevolucion.setFecha(devolucion.getFecha());
                newDevolucion.setHora(devolucion.getHora());
                newDevolucion.setIdTermo(idTermo);

                entityManager.merge(newDevolucion);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
