package es.tfg.backend.servers;

import es.tfg.backend.entitys.Usuario;
import es.tfg.backend.exception.EmailAlrreadyExistsException;
import es.tfg.backend.exception.UsernameAlreadyExistsException;
import es.tfg.backend.mappers.UsuarioMapper;
import es.tfg.backend.model.AuthRequestDTO;
import es.tfg.backend.model.AuthResponseDTO;
import es.tfg.backend.model.RegisterRequestDTO;
import es.tfg.backend.repositorys.RolRepository;
import es.tfg.backend.repositorys.UsuarioRepository;
import es.tfg.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;


    // -------------- MÉTODOS LLAMADOS POR ENDPOINTS --------------

    public AuthResponseDTO login(AuthRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token);
    }

    public Void register(RegisterRequestDTO request) {

        // Comprbaciones contra la bd
        usuarioRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new EmailAlrreadyExistsException(request.getEmail());
                });
        usuarioRepository.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new UsernameAlreadyExistsException(request.getUsername());
                });

        // Encriptación de la contraseña
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // Mapeo y guardado del usuario
        Usuario user = usuarioMapper.toEntity(request);
        user.setFechaCreacion(LocalDateTime.now());
        user.setRol(rolRepository.findByNombre("ROLE_CLIENTE")); // Se le asigna rol de cliente por defecto
        usuarioRepository.save(user);

        return null;
    }

    // -------------------- MÉTODOS AUXILIARES --------------------

}