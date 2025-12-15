package br.guilherme.solidproject.domain.factory;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.domain.records.UsuarioRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioFactory {

    public Usuario criarUsuario(UsuarioRecord usuarioRecord) {
        return new Usuario(
                usuarioRecord.id() != null ? usuarioRecord.id() : null,
                usuarioRecord.nome(),
                usuarioRecord.idade()
        );
    }

    public UsuarioRecord criarUsuarioRecord(Usuario usuario) {
        return new UsuarioRecord(
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade()
        );
    }

    public List<UsuarioRecord> criarListaUsuariosRecord(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::criarUsuarioRecord)
                .toList();
    }
    public Usuario atualizarUsuario(Usuario usuario, UsuarioRecord usuarioRecord) {
        usuario.setNome(usuarioRecord.nome());
        usuario.setIdade(usuarioRecord.idade());
        return usuario;
    }
}
