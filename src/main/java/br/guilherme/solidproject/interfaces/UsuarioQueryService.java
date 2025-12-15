package br.guilherme.solidproject.interfaces;

import br.guilherme.solidproject.domain.records.UsuarioRecord;

import java.util.List;

public interface UsuarioQueryService {
    List<UsuarioRecord> listarUsuarios();
}
