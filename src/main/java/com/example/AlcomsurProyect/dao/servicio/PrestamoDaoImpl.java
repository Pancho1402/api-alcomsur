package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.PrestamoDao;
import com.example.AlcomsurProyect.model.JoinDevolucion;
import com.example.AlcomsurProyect.model.JoinPrestamo;
import com.example.AlcomsurProyect.model.Prestamo;
import com.example.AlcomsurProyect.model.Termo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PrestamoDaoImpl implements PrestamoDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    public PrestamoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<JoinPrestamo> getPrestamo(String empresa, String fecha) {
        String sqlQuery = "SELECT c.id_termo,a.id_prestamo, a.fecha_prestamo, a.hora_prestamo, b.nombre_empresa, d.nombre_trabajador\n" +
                "FROM prestamo AS a\n" +
                "JOIN termo AS c ON a.id_termo = c.id_termo\n" +
                "JOIN empresa AS b ON c.id_empresa = b.id_empresa\n" +
                "JOIN trabajador AS d ON c.id_trabajador = d.id_trabajador\n" +
                "WHERE b.nombre_empresa = ? AND a.fecha_prestamo = ? ;";

        List<JoinPrestamo> result = jdbcTemplate.query(sqlQuery,
                new Object[] { empresa, fecha},
                new JoinPrestamoRowMapper());
        return result;
    }
    @Override
    public boolean registrarPrestamo(JoinPrestamo prestamos) {
        try {
            String query = "FROM Termo WHERE codRegistro = :codRegistro";
            List<Termo> lista = entityManager.createQuery(query)
                    .setParameter("codRegistro",prestamos.getCodigo())
                    .getResultList();

            if(!lista.isEmpty()) {
                int idTermo = lista.get(0).getId();

                Prestamo newPrestamo = new Prestamo();

                newPrestamo.setFecha(prestamos.getFecha());
                newPrestamo.setHora(prestamos.getHora());
                newPrestamo.setIdTermo(idTermo);

                entityManager.merge(newPrestamo);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
