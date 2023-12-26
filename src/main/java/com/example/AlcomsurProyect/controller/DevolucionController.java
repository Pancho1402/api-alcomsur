package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.DevolucionDao;
import com.example.AlcomsurProyect.model.Devolucion;
import com.example.AlcomsurProyect.model.JoinDevolucion;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DevolucionController {
    @Autowired
    private final DevolucionDao dao;
    @Autowired
    JWTUtil jwtUtil;

    public DevolucionController(DevolucionDao dao) {
        this.dao = dao;
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
    @RequestMapping(value = "api/devolucion/{empresa}/{fecha}", method = RequestMethod.GET)
    public ResponseEntity<List<JoinDevolucion>> mostrarDevolucion(@RequestHeader(value = "Authorization")
              String token, @PathVariable(value = "empresa") String empresa,
                                                      @PathVariable(value = "fecha") String fecha){
        try {
            if(!validarToken(token)){
                return null;
            }
            List<JoinDevolucion> lista = dao.getDevolucion(empresa, fecha);
            return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    @RequestMapping(value = "api/devolucion", method = RequestMethod.POST)
    public ResponseEntity<Boolean> agregarDevolucion(@RequestBody List<JoinDevolucion> devoluciones) {
        boolean resultado = true;
        for (JoinDevolucion devolucion : devoluciones) {
            boolean registroExitoso = dao.postDevolucion(devolucion);
            if (!registroExitoso) {
                resultado = false;
                break;
            }
        }
        return ResponseEntity.ok(resultado);
    }

}
