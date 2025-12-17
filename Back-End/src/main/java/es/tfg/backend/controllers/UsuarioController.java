package es.tfg.backend.controllers;

import es.tfg.backend.model.TokenRequestDTO;
import es.tfg.backend.model.UsernameResponseDTO;
import es.tfg.backend.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------
    private final UsuarioService usuarioService;

    // ------------------------ ENDPOINTS ------------------------
    @PostMapping("/username-from-jwt")
    public ResponseEntity<UsernameResponseDTO> getUsername(@RequestBody TokenRequestDTO tokenRequestDTO) {
        return ResponseEntity.ok(usuarioService.getUsername(tokenRequestDTO));
    }
}
