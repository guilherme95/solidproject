package br.guilherme.solidproject.controller;

import br.guilherme.solidproject.domain.records.UsuarioRecord;
import br.guilherme.solidproject.interfaces.UsuarioAdministrationService;
import br.guilherme.solidproject.interfaces.UsuarioModificationService;
import br.guilherme.solidproject.interfaces.UsuarioQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final UsuarioAdministrationService usuarioAdministrationService;
    private final UsuarioModificationService usuarioModificationService;
    private final UsuarioQueryService usuarioQueryService;

    public UsuarioController(
            UsuarioAdministrationService usuarioAdministrationService,
            UsuarioModificationService usuarioModificationService, UsuarioQueryService usuarioQueryService
    ) {
        this.usuarioAdministrationService = usuarioAdministrationService;
        this.usuarioModificationService = usuarioModificationService;
        this.usuarioQueryService = usuarioQueryService;
    }

    @PostMapping(path = "/criar-usuario")
    public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioRecord usuarioRecord) throws Exception {
        usuarioModificationService.criarUsuario(usuarioRecord);
        return ResponseEntity.status(201)
                .build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRecord>> listarUsuarios() {
        return ResponseEntity.ok(
                usuarioQueryService.listarUsuarios()
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> editarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRecord usuarioRecord
    ) throws Exception {
        usuarioModificationService.atualizarUsuario(id, usuarioRecord);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id) {
        usuarioAdministrationService.removerUsuario(id);
        return ResponseEntity.status(200).build();
    }
}
