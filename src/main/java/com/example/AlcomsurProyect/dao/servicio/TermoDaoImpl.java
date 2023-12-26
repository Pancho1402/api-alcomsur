package com.example.AlcomsurProyect.dao.servicio;

import com.example.AlcomsurProyect.dao.repositorio.TermoDao;
import com.example.AlcomsurProyect.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Repository
@Transactional
public class TermoDaoImpl implements TermoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String postTermo(JoinPersonal joinPersonal) {
        List<Termo> listaTermo = validarTermoExsitente(joinPersonal);
        if(listaTermo.isEmpty()){

            String codQr = "";

            for (int i = 0; i < 30; i++) {
                codQr += charRandom(65, 122);
            }

            Termo termo = new Termo();

            termo.setIdEmpresa(joinPersonal.getIdEmpresa());
            termo.setIdTrabajador(joinPersonal.getIdTrabajador());
            termo.setCodRegistro(codQr);

            entityManager.merge(termo);
            return codQr;
        }
        else {
            return listaTermo.get(0).getCodRegistro();
        }
    }

    @Override
    public void eliminarTermo(int id) {
        String query ="";

        query ="DELETE FROM Devolucion WHERE idTermo = :idTermo";
        entityManager.createQuery(query)
                .setParameter("idTermo", id)
                .executeUpdate();


        query ="DELETE FROM Prestamo WHERE idTermo = :idTermo";
        entityManager.createQuery(query)
                .setParameter("idTermo", id)
                .executeUpdate();

        Termo termo = entityManager.find(Termo.class, id);
        entityManager.remove(termo);
    }

    private static char charRandom(int min, int max) {
        Random rand = new Random();
        char randomChar;
        do {
            randomChar = (char) (rand.nextInt(max - min + 1) + min);
        } while (!validarChar(randomChar));
        return randomChar;
    }

    private static boolean validarChar(char c) {
        return Character.isLetterOrDigit(c) || validarCharEspecial(c);
    }

    private static boolean validarCharEspecial(char c) {
        String commonSpecialChars = "!@#$%^&*()_-+=<>?/{}[]|`";
        return commonSpecialChars.indexOf(c) != -1;
    }
    private List<Termo> validarTermoExsitente(JoinPersonal joinPersonal) {
        String query = "FROM Termo WHERE idTrabajador = :idTrabajador";

        return entityManager.createQuery(query)
                .setParameter("idTrabajador", joinPersonal.getIdTrabajador())
                .getResultList();

    }
}
