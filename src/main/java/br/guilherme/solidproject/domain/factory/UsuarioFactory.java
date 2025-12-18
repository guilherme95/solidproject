package br.guilherme.solidproject.domain.factory;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.domain.records.UsuarioRecord;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioFactory {

    private final PasswordEncoder passwordEncoder;

    public UsuarioFactory(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(UsuarioRecord usuarioRecord) {
        return new Usuario(
                usuarioRecord.id(),
                usuarioRecord.nome(),
                usuarioRecord.idade(),
                usuarioRecord.email(),
                passwordEncoder.encode(usuarioRecord.password())
        );
    }

    public UsuarioRecord criarUsuarioRecord(Usuario usuario) {
        return new UsuarioRecord(
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }

    public List<UsuarioRecord> criarListaUsuariosRecord(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::criarUsuarioRecord)
                .toList();
    }

    public Usuario atualizarUsuario(Long id, UsuarioRecord usuarioRecord) {
        return new Usuario(
                id,
                usuarioRecord.nome(),
                usuarioRecord.idade(),
                usuarioRecord.email(),
                passwordEncoder.encode(usuarioRecord.password())
        );
    }
}
