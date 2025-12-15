package br.guilherme.solidproject.repository;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.interfaces.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("legacyRepo")
public interface LegadoUsuarioRepository extends UsuarioRepository {

//    @Override
//    Optional<Usuario> findById(int id) {
//        return new Usuario(89L, "Usuario Legado", 91);
//    }
}
