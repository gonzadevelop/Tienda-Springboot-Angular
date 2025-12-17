package es.tfg.backend.services;

import es.tfg.backend.model.TokenRequestDTO;
import es.tfg.backend.model.UsernameResponseDTO;
import es.tfg.backend.security.JwtService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------
    private final JwtService jwtService;

    // -------------- MÉTODOS LLAMADOS POR ENDPOINTS --------------
    public UsernameResponseDTO getUsername(TokenRequestDTO tokenRequestDTO) {
        String username = jwtService.extractUsername(tokenRequestDTO.getToken());
        return UsernameResponseDTO.builder()
                .username(username)
                .build();
    }

    // -------------------- MÉTODOS AUXILIARES --------------------

}
