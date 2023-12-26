package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.UsuarioDao;
import com.example.AlcomsurProyect.model.Usuario;
import com.example.AlcomsurProyect.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;
    private  boolean validarToken(String token){
        String usuarioID = jwtUtil.getKey(token);
        return usuarioID != null;
    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> getUsuario(@RequestHeader(value = "Authorization")
                                                        String token){
        try {
            if(!validarToken(token)){
                return  null;
            }
            List<Usuario> lista = usuarioDao.getUsuario();
            return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "api/usuario", method = RequestMethod.POST)
    public void registrarUsuario (@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.postUsuario(usuario);
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader(value = "Authorization") String token,
                              @PathVariable(value = "id") int id){
        if(!validarToken(token)){
            return;
        }
        usuarioDao.deleteUsuario(id);
    }

}
