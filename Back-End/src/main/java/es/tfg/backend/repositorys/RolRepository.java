package es.tfg.backend.repositorys;

import es.tfg.backend.entitys.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Rol findByNombre(String nombre);
}
