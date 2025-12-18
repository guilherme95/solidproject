package br.guilherme.solidproject;

import br.guilherme.solidproject.domain.entity.Usuario;
import br.guilherme.solidproject.interfaces.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class SolidprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolidprojectApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UsuarioRepository usuarioRepository) {
		return args -> {
			System.out.println("==== Iniciando persistencia para usuario ====");

			Usuario usuarioUm = new Usuario();
			usuarioUm.setNome("Maria Elis");
			usuarioUm.setIdade(18);
			usuarioUm.setEmail("maria@email.com");
			usuarioUm.setSenha(
					new BCryptPasswordEncoder()
							.encode("1234")
			);

			Usuario usuarioDois = new Usuario();
			usuarioDois.setNome("Igor Augusto");
			usuarioDois.setIdade(19);
			usuarioDois.setEmail("igor@email.com");
			usuarioDois.setSenha(
					new BCryptPasswordEncoder()
							.encode("5678")
			);

			usuarioRepository.saveAll(
					List.of(usuarioUm, usuarioDois)
			);

			System.out.println("==== Persistencia de usuario concluida com sucesso ====");
		};
	}

}
