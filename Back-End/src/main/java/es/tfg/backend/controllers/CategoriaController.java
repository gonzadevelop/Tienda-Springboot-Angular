package es.tfg.backend.controllers;

import es.tfg.backend.model.CategoriaDTO;
import es.tfg.backend.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    // --------------- INYECCIONES POR CONSTRUCTOR ---------------
    private final CategoriaService categoriaService;

    // ------------------------ ENDPOINTS ------------------------
    @GetMapping("/padres-hijas")
    public ResponseEntity<List<CategoriaDTO>> getCategorias(@RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(categoriaService.getCategoriasPadre(nombre));
    }
}
