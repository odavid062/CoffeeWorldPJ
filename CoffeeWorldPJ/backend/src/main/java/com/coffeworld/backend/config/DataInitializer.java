package com.coffeworld.backend.config;

import com.coffeworld.backend.enums.Role;
import com.coffeworld.backend.model.Usuario;
import com.coffeworld.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email:david.tdb21@hotmail.com}")
    private String adminEmail;

    @Value("${app.admin.password:250797}")
    private String adminPassword;

    @Value("${app.admin.name:David Rodrigues}")
    private String adminName;

    @Value("${app.cozinha.email:cozinha@coffeeworld.com}")
    private String cozinhaEmail;

    @Value("${app.cozinha.password:cozinha123}")
    private String cozinhaPassword;

    @Override
    public void run(String... args) {
        criarUsuarioSeNaoExiste(adminEmail, adminPassword, adminName, Role.ADMIN);
        criarUsuarioSeNaoExiste(cozinhaEmail, cozinhaPassword, "Equipe da Cozinha", Role.COZINHEIRO);
    }

    private void criarUsuarioSeNaoExiste(String email, String senhaPlana, String nome, Role role) {
        if (usuarioRepository.existsByEmail(email)) {
            log.info("Usuário {} ({}) já existe — pulando criação.", email, role);
            return;
        }

        Usuario usuario = Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(passwordEncoder.encode(senhaPlana))
                .role(role)
                .build();

        usuarioRepository.save(usuario);
        log.info("Usuário {} criado com role {}.", email, role);
    }
}
