package es.tfg.backend.mappers;

import es.tfg.backend.entitys.Usuario;
import es.tfg.backend.model.authDtos.RegisterRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(RegisterRequestDTO registerRequestDTO);
}
