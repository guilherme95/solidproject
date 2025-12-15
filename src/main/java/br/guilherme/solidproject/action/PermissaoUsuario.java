package br.guilherme.solidproject.action;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.interfaces.PostRegistrationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PermissaoUsuario implements PostRegistrationAction {

    private static final Logger log = LoggerFactory.getLogger(EmailUsuarioSender.class);

    @Override
    public void execute(Usuario usuario) {
        log.info("Permissao do usuario: {} criada com sucesso!", usuario.getNome());
    }
}
