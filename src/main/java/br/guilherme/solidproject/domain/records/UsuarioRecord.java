package br.guilherme.solidproject.domain.records;

public record UsuarioRecord(
        Long id,
        String nome,
        Integer idade,
        String email,
        String password
) {
}
