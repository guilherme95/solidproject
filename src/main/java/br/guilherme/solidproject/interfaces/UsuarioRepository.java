package br.guilherme.solidproject.interfaces;

import br.guilherme.solidproject.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
