package es.tfg.backend.mappers;

import es.tfg.backend.entitys.Categoria;
import es.tfg.backend.model.CategoriaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toDto(Categoria categoria);

    List<CategoriaDTO> toDtos(List<Categoria> categorias);
}
