package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.UsuarioDao;
import com.example.AlcomsurProyect.model.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Usuario> getUsuario() {
        String query = "FROM Usuario WHERE admin = false";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void postUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void deleteUsuario(int id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario getCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";// se asocia el nombre de la clase
        List<Usuario> lista= entityManager.createQuery(query)
                .setParameter( "email", usuario.getEmail())
                .getResultList();
        if(lista.isEmpty()){
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, usuario.getPassword())){
            return  lista.get(0);
        }
        return null;
    }
}
