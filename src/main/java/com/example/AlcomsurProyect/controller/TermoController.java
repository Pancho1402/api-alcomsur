package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.TermoDao;
import com.example.AlcomsurProyect.model.JoinPersonal;
import com.example.AlcomsurProyect.model.Termo;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TermoController {
    @Autowired
    TermoDao termoDao;
    @Autowired
    JWTUtil jwtUtil;
    private boolean validarToken(String token){
        String usuarioID = jwtUtil.getKey(token);
        return usuarioID != null;
    }
    @RequestMapping(value = "api/termo", method = RequestMethod.POST)
    public String postTermo(@RequestBody JoinPersonal termo){
        return termoDao.postTermo(termo);
    }
    @RequestMapping(value = "api/termo/{id}", method = RequestMethod.DELETE)
    public void deleteTermo(@RequestHeader(value = "Authorization") String token
            ,@PathVariable(value = "id") int id){
        if(!validarToken(token)){
            return;
        }
        termoDao.eliminarTermo(id);
    }
}
