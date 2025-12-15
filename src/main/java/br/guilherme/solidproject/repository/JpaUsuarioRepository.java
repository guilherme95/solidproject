package br.guilherme.solidproject.repository;

import br.guilherme.solidproject.interfaces.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpaRepo")
public interface JpaUsuarioRepository extends UsuarioRepository {

//    @Override
//    Optional<Usuario> findById(Long id);
}
