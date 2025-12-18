package br.guilherme.solidproject.repository;

import br.guilherme.solidproject.interfaces.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("legacyRepo")
public interface LegadoUsuarioRepository extends UsuarioRepository {
}
