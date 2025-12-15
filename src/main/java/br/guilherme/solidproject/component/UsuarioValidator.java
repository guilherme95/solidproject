package br.guilherme.solidproject.component;

import br.guilherme.solidproject.domain.records.UsuarioRecord;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    public void validarNovoUsuario(UsuarioRecord usuarioRecord) throws Exception {
        if (usuarioRecord.nome() == null || usuarioRecord.nome().isEmpty()) {
            throw new Exception("Nome do usuario nao pode ser nulo");
        }
    }

    public void validarAtualizacaoUsuario(UsuarioRecord usuarioRecord) throws Exception {
        if (usuarioRecord.id() == null && usuarioRecord.nome() == null) {
            throw new Exception("Id e nome nao podem ser nulos");
        }
    }
}
