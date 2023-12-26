package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.model.JoinPrestamo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinPrestamoRowMapper  implements RowMapper<JoinPrestamo> {
    @Override
    public JoinPrestamo mapRow(ResultSet rs, int rowNum) throws SQLException {
        JoinPrestamo join = new JoinPrestamo();
        join.setId(rs.getInt("id_prestamo"));
        join.setFecha(rs.getString("fecha_prestamo"));
        join.setHora(rs.getString("hora_prestamo"));
        join.setEmpresa(rs.getString("nombre_empresa"));
        join.setNombre(rs.getString("nombre_trabajador"));
        join.setIdTermo(rs.getInt("id_termo"));

        return join;
    }
}
