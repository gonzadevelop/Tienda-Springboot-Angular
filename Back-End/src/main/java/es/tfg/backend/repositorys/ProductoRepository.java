package es.tfg.backend.repositorys;

import es.tfg.backend.entitys.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long>{
    public List<Producto> findAll();
}
