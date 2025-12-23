package br.guilherme.solidproject.repository;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.interfaces.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("legacyRepo")
public interface LegadoUsuarioRepository extends UsuarioRepository {

    @Override
    @Query(value = """
            SELECT
             usuario.*
            FROM
             usuario usuario
            WHERE
             usuario.nome = :nome
            """, nativeQuery = true)
    Optional<Usuario> findByNome(String nome);
}
