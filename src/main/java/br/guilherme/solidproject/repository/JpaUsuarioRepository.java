package br.guilherme.solidproject.repository;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.interfaces.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("jpaRepo")
public interface JpaUsuarioRepository extends UsuarioRepository {

    @Override
    @Query("""
            SELECT
             usuario
            FROM
             Usuario usuario
            WHERE
             usuario.nome = :nome
            """)
    Optional<Usuario> findByNome(String nome);
}
