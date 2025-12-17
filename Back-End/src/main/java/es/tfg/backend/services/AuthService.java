package es.tfg.backend.services;

import es.tfg.backend.entitys.Usuario;
import es.tfg.backend.exception.auth.EmailAlrreadyExistsException;
import es.tfg.backend.exception.auth.EmailNotFoundException;
import es.tfg.backend.exception.auth.UsernameAlreadyExistsException;
import es.tfg.backend.mappers.UsuarioMapper;
import es.tfg.backend.model.authDtos.LoginRequestDTO;
import es.tfg.backend.model.authDtos.LoginResponseDTO;
import es.tfg.backend.model.authDtos.RegisterRequestDTO;
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
public class AuthService {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------
    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;


    // -------------- MÉTODOS LLAMADOS POR ENDPOINTS --------------

    public LoginResponseDTO login(LoginRequestDTO request) {


        String username = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotFoundException(request.getEmail()))
                .getUsername();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, request.getPassword())
        );

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return new LoginResponseDTO(token);
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

        // pendiente mailjet o similar para verificacion de email

        // Encriptado de la contraseña
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        // Mapeo y guardado del usuario
        Usuario user = usuarioMapper.toEntity(request);
        user.setFechaCreacion(LocalDateTime.now());
        user.setRol(rolRepository.findByNombre("ROLE_CLIENTE")); // Se le asigna rol de cliente por defecto
        usuarioRepository.save(user);

        return null;
    }

    public Boolean checkEmailExists(String email) {
        return usuarioRepository.findByEmail(email).isPresent();
    }

    // -------------------- MÉTODOS AUXILIARES --------------------

}
