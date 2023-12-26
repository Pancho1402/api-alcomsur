package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.UsuarioDao;
import com.example.AlcomsurProyect.model.Usuario;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao dao; // inyeccion de dependecia

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public ResponseEntity<String> login (@RequestBody Usuario usuario){
        try {
            Usuario usuarioLogin = dao.getCredenciales(usuario);
            if(usuarioLogin != null){

                String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogin.getId()), usuarioLogin.getEmail());
                return ResponseEntity.ok(tokenJwt);
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
