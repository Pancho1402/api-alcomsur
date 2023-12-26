package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.model.JoinDevolucion;
import com.example.AlcomsurProyect.model.JoinPersonal;
import com.example.AlcomsurProyect.model.Trabajador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinPersonalRowMapper implements RowMapper<JoinPersonal> {
    @Override
    public JoinPersonal mapRow(ResultSet rs, int rowNum) throws SQLException {
        JoinPersonal join = new JoinPersonal();
        join.setNombreEmpresa(rs.getString("nombre_empresa"));
        join.setNombre(rs.getString("nombre_trabajador"));
        join.setIdTrabajador(rs.getInt("id_trabajador"));
        join.setIdEmpresa(rs.getInt("id_empresa"));

        return join;
    }
}
