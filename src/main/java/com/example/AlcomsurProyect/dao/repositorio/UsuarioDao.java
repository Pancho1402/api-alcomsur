package com.example.AlcomsurProyect.dao.repositorio;

import com.example.AlcomsurProyect.model.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuario();
    void postUsuario(Usuario usuario);
    void deleteUsuario(int id);
    Usuario getCredenciales(Usuario usuario);
}
