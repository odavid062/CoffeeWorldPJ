package com.coffeworld.backend.resource;

import com.coffeworld.backend.dto.auth.AuthResponseDTO;
import com.coffeworld.backend.dto.auth.LoginRequestDTO;
import com.coffeworld.backend.dto.auth.RegisterRequestDTO;
import com.coffeworld.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Login e registro de usuários")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthService authService;

    @Operation(summary = "Cadastra um novo cliente")
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(authService.registrar(dto));
    }

    @Operation(summary = "Autentica usuário e retorna JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        try {
            return ResponseEntity.ok(authService.login(dto));
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("E-mail ou senha incorretos");
        }
    }
}
