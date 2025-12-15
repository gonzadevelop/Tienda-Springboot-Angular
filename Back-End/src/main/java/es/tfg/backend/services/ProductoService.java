package es.tfg.backend.services;

import es.tfg.backend.entitys.Producto;
import es.tfg.backend.repositorys.ProductoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------

    private final ProductoRepository productoRepository;

    // -------------- MÉTODOS LLAMADOS POR ENDPOINTS --------------

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // -------------------- MÉTODOS AUXILIARES --------------------

}