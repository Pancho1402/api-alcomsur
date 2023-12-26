package com.example.AlcomsurProyect.controller;

import com.example.AlcomsurProyect.dao.repositorio.EmpresaDao;
import com.example.AlcomsurProyect.model.Empresa;
import com.example.AlcomsurProyect.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpresaController {
    @Autowired
    EmpresaDao dao;
    @Autowired
    JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioID = jwtUtil.getKey(token);
        return usuarioID != null;
    }

    @RequestMapping(value = "api/empresas", method = RequestMethod.GET)
    public ResponseEntity<List<Empresa>> mostrarEmpresa(@RequestHeader(value = "Authorization")
        String token){
        try {
            if(!validarToken(token)){
                return null;
            }
            List<Empresa> lista = dao.mostrarEmpresas();
            return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(value = "api/empresas", method = RequestMethod.POST)
    public void agregarEmpresas(@RequestBody Empresa empresa){
        dao.agregarEmpresas(empresa);
    }
    @RequestMapping(value = "api/empresas/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void editarEmpresa(@RequestHeader(value = "Authorization") String token
            ,@PathVariable(value = "id") int id, @RequestBody Empresa empresa){

        if(!validarToken(token)){
            return ;
        }

        Empresa empresaExiste = dao.mostrarEmpresa(id);
        if(empresaExiste != null){
            empresaExiste.setEmpresa(empresa.getEmpresa());
            empresaExiste.setRepresentante(empresa.getRepresentante());

            dao.editarEmpresa(empresaExiste);
        }

    }

    @RequestMapping(value = "api/empresas/{id}", method = RequestMethod.DELETE)
    public void deleteEmpresa(@RequestHeader(value = "Authorization") String token
            ,@PathVariable(value = "id") int id){
        if(!validarToken(token)){
            return ;
        }
        dao.eliminarEmpresa(id);
    }
}
