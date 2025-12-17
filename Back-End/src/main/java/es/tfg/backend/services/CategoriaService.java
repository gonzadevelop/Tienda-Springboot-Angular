package es.tfg.backend.services;

import es.tfg.backend.entitys.Categoria;
import es.tfg.backend.mappers.CategoriaMapper;
import es.tfg.backend.model.CategoriaDTO;
import es.tfg.backend.repositorys.CategoriaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    // -------------- MÉTODOS LLAMADOS POR ENDPOINTS --------------
    public List<CategoriaDTO> getCategoriasPadre(String categoriaPadreNombre) {
        List<Categoria> categorias;
        if (categoriaPadreNombre != null) {
            categorias = categoriaRepository.findByCategoriaPadre_Nombre(categoriaPadreNombre);
        } else {
            categorias = categoriaRepository.findByCategoriaPadre_IdIsNull();
        }

        return categoriaMapper.toDtos(categorias);
    }

    // -------------------- MÉTODOS AUXILIARES --------------------

}
