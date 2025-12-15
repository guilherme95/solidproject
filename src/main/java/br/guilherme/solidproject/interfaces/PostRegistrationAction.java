package br.guilherme.solidproject.interfaces;

import br.guilherme.solidproject.domain.entity.Usuario;

public interface PostRegistrationAction {

    void execute(Usuario usuario);
}
