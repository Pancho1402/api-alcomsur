package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.ReportarDao;
import com.example.AlcomsurProyect.model.JoinReporte;
import com.example.AlcomsurProyect.model.Reportar;
import com.example.AlcomsurProyect.model.Trabajador;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportarController {
    @Autowired
    ReportarDao dao;
    @Autowired
    JWTUtil jwtUtil;
    private boolean validarToken(String token){
        String usuarioID = jwtUtil.getKey(token);
        return usuarioID != null;
    }
    @RequestMapping(value = "api/reportar/{empresa}/{fecha}", method = RequestMethod.GET)
    public ResponseEntity<List<JoinReporte>> get(@RequestHeader(value = "Authorization") String token
                                          , @PathVariable(value = "fecha") String fecha
                                          ,@PathVariable(value = "empresa") Integer empresa){

        try {
            if(!validarToken(token)){
                return null;
            }
            List<JoinReporte> lista =  dao.getReportar(fecha,empresa);
            return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "api/reportar", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postReportar(@RequestBody Reportar reportar){
            return ResponseEntity.ok(dao.postReportar(reportar));
    }

    @RequestMapping(value = "api/reportar/{id}", method = RequestMethod.DELETE)
    public void deleteReportar(@RequestHeader(value = "Authorization") String token,
                               @PathVariable(value = "id") int id){
        if(!validarToken(token)){
            return;
        }
        dao.deleteReportar(id);
    }

}
