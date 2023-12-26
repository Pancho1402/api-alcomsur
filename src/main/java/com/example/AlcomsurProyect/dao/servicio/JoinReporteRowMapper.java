package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.model.JoinReporte;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinReporteRowMapper implements RowMapper<JoinReporte> {
    @Override
    public JoinReporte mapRow(ResultSet rs, int rowNum) throws SQLException {
        JoinReporte join = new JoinReporte();
        join.setId(rs.getInt("id_reporte"));
        join.setDescripcion(rs.getString("des_reporte"));
        join.setFecha(rs.getString("fecha_reporte"));
        join.setEmpresa(rs.getString("nombre_empresa"));

        return join;
    }
}
