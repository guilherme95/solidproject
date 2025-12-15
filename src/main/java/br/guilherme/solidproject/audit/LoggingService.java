package br.guilherme.solidproject.audit;

import br.guilherme.solidproject.domain.records.LoggingRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {

    private static final Logger log = LoggerFactory.getLogger(LoggingService.class);

    public void log(LoggingRecord loggingRecord) {
        String mensagemLog = String.format(
                "Tipo: %s, Usuario: %d, Dominio: %s, Detalhes: %s",
                loggingRecord.tipoLog(),
                loggingRecord.idUsuario(),
                loggingRecord.dominio(),
                loggingRecord.detalhes()
        );
        log.info("[AUDI]: {}", mensagemLog);
    }
}
