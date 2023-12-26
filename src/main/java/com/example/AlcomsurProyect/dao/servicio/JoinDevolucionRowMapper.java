package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.model.JoinDevolucion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinDevolucionRowMapper implements RowMapper<JoinDevolucion> {
    @Override
    public JoinDevolucion mapRow(ResultSet rs, int rowNum) throws SQLException {
        JoinDevolucion join = new JoinDevolucion();
        join.setId(rs.getInt("id_devolucion"));
        join.setFecha(rs.getString("fecha_devolucion"));
        join.setHora(rs.getString("hora_devolucion"));
        join.setEmpresa(rs.getString("nombre_empresa"));
        join.setNombre(rs.getString("nombre_trabajador"));
        join.setIdTermo(rs.getInt("id_termo"));

        return join;
    }
}

