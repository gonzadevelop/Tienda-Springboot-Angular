package es.tfg.backend.controllers;

import es.tfg.backend.model.AuthRequestDTO;
import es.tfg.backend.model.AuthResponseDTO;
import es.tfg.backend.model.RegisterRequestDTO;
import es.tfg.backend.servers.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request){
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(usuarioService.register(request));
    }
}
