package br.guilherme.solidproject.service;

import br.guilherme.solidproject.audit.LoggingService;
import br.guilherme.solidproject.component.UsuarioValidator;
import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.domain.factory.UsuarioFactory;
import br.guilherme.solidproject.domain.records.LoggingRecord;
import br.guilherme.solidproject.domain.records.UsuarioRecord;
import br.guilherme.solidproject.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UsuarioQueryService, UsuarioModificationService, UsuarioAdministrationService {

    private final LoggingService loggingService;
    private final UsuarioValidator usuarioValidator;
    private final UsuarioFactory usuarioFactory;
    private final UsuarioRepository usuarioRepository;
    private final List<PostRegistrationAction> postRegistrationAction;

    public UsuarioService(
            LoggingService loggingService,
            UsuarioValidator usuarioValidator,
            UsuarioFactory usuarioFactory,
            UsuarioRepository usuarioRepository,
            List<PostRegistrationAction> postRegistrationAction
    ) {
        this.loggingService = loggingService;
        this.usuarioValidator = usuarioValidator;
        this.usuarioFactory = usuarioFactory;
        this.usuarioRepository = usuarioRepository;
        this.postRegistrationAction = postRegistrationAction;
    }

    @Override
    public void criarUsuario(UsuarioRecord usuarioRecord) throws Exception {
        usuarioValidator.validarUsuario(usuarioRecord);
        Usuario usuario = usuarioFactory.criarUsuario(usuarioRecord);
        usuarioRepository.save(usuario);
        for (PostRegistrationAction action : postRegistrationAction) {
            action.execute(usuario);
        }
        loggingService.log(
                new LoggingRecord(
                        "USUARIO_CRIADO",
                        usuario.getId(),
                        "Usuario",
                        usuario.toString()
                )
        );
    }

    @Override
    public List<UsuarioRecord> listarUsuarios() {
        List<UsuarioRecord> usuarioRecords = usuarioFactory.criarListaUsuariosRecord(
                usuarioRepository.findAll()
        );
        usuarioRecords.forEach(u ->
                loggingService.log(
                        new LoggingRecord(
                                "USUARIO_LISTADO",
                                u.id(),
                                "Usuario",
                                u.toString()
                        )
                )
        );
        return usuarioRecords;
    }

    @Override
    public void atualizarUsuario(Long id, UsuarioRecord usuarioRecord) throws Exception {
        usuarioValidator.validarUsuario(usuarioRecord);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuario nao encontrado"));
        usuarioRepository.save(
                usuarioFactory.atualizarUsuario(usuario.getId(), usuarioRecord)
        );
        loggingService.log(
                new LoggingRecord(
                        "USUARIO_ATUALIZADO",
                        usuario.getId(),
                        "Usuario",
                        usuario.toString()
                )
        );
    }

    @Override
    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
        loggingService.log(
                new LoggingRecord(
                        "USUARIO_REMOVIDO",
                        id,
                        "Usuario",
                        id.toString()
                )
        );
    }
}
