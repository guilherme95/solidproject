package br.guilherme.solidproject.interfaces;

import br.guilherme.solidproject.domain.records.UsuarioRecord;

public interface UsuarioModificationService {
    void criarUsuario(UsuarioRecord usuarioRecord) throws Exception;
    void atualizarUsuario(UsuarioRecord usuarioRecord) throws Exception;
}
