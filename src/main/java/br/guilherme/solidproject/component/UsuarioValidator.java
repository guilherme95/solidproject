package br.guilherme.solidproject.component;

import br.guilherme.solidproject.domain.records.UsuarioRecord;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    public void validarUsuario(UsuarioRecord usuarioRecord) throws Exception {
        if (usuarioRecord.nome() == null || usuarioRecord.nome().isEmpty()) {
            throw new Exception("Nome do usuario nao pode ser nulo");
        }
    }
}
