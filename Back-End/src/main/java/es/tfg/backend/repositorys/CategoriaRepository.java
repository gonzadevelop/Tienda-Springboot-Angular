package es.tfg.backend.repositorys;

import es.tfg.backend.entitys.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    List<Categoria> findByCategoriaPadre_IdIsNull();
    List<Categoria> findByCategoriaPadre_Nombre(String categoriaPadreNombre);
}
