package br.guilherme.solidproject.domain.records;

public record LoggingRecord(
        String tipoLog,
        Long idUsuario,
        String dominio,
        String detalhes
) {}
