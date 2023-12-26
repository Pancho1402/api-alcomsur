package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.TrabajadorDao;
import com.example.AlcomsurProyect.model.Empresa;
import com.example.AlcomsurProyect.model.JoinPersonal;
import com.example.AlcomsurProyect.model.Trabajador;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrabajadorController {
    @Autowired
    TrabajadorDao dao;
    @Autowired
    JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioID = jwtUtil.getKey(token);
        return usuarioID != null;
    }

    @RequestMapping(value = "api/trabajador/{empresa}", method = RequestMethod.GET)
    public ResponseEntity<List<JoinPersonal>> getTrabajador(@RequestHeader(value = "Authorization")
        String token,@PathVariable(value = "empresa") String empresa) {
        try {
            if (!validarToken(token)) {
                return null;
            }
            List<JoinPersonal> lista = dao.getTrabajador(empresa);
            return lista.isEmpty()? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "api/trabajador", method = RequestMethod.POST)
    public void agregarTrabajador(@RequestBody Trabajador trabajador) {
        dao.postTrabajador(trabajador);
    }
    @RequestMapping(value = "api/trabajador/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void editarTrabajador(@RequestHeader(value = "Authorization") String token
            ,@PathVariable(value = "id") int id,
            @RequestBody Trabajador trabajador) {

        if(!validarToken(token)){
            return ;
        }

        Trabajador trabajadorExiste = dao.getTrabajador(id);

        if (trabajadorExiste != null) {
            trabajadorExiste.setNombre(trabajador.getNombre());
            trabajadorExiste.setIdEmpresa(trabajador.getIdEmpresa());

            dao.putTrabajador(trabajadorExiste);
        }
    }

    @RequestMapping(value = "api/trabajador/{id}", method = RequestMethod.DELETE)
    public void deleteTrabajador(@RequestHeader(value = "Authorization") String token
            ,@PathVariable(value = "id") int id){
        if(!validarToken(token)){
            return ;
        }
         dao.deleteTrabajador(id);

    }
}
