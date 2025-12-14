package es.tfg.backend.repositorys;

import es.tfg.backend.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);
    public Optional<Usuario> findByUsername(String username);
}
