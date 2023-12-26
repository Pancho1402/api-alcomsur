package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.PrestamoDao;
import com.example.AlcomsurProyect.model.JoinPrestamo;
import com.example.AlcomsurProyect.model.Prestamo;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrestamoController {
    @Autowired
     private final PrestamoDao dao;
    @Autowired
    JWTUtil jwtUtil;

    public PrestamoController(PrestamoDao dao) {
        this.dao = dao;
    }


    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
    @RequestMapping(value = "api/prestamo/{empresa}/{fecha}", method = RequestMethod.GET)
    public ResponseEntity<List<JoinPrestamo>> getPrestamo(@RequestHeader(value = "Authorization")
     String token, @PathVariable(value = "empresa") String empresa,
                                                          @PathVariable(value = "fecha") String fecha){
        try {
            if(!validarToken(token)){
                return null;
            }
            List<JoinPrestamo> lista = dao.getPrestamo(empresa, fecha);
            return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @RequestMapping(value = "api/prestamo", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postPrestamo(@RequestBody List<JoinPrestamo> prestamos) {
        boolean resultado = true;
        for (JoinPrestamo prestamo : prestamos) {
            boolean registroExitoso = dao.registrarPrestamo(prestamo);
            if (!registroExitoso) {
                resultado = false;
                break;
            }
        }
        return ResponseEntity.ok(resultado);
    }

}
