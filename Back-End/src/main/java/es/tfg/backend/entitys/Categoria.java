package es.tfg.backend.entitys;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_padre")
    private Categoria categoriaPadre;

    @ManyToMany(mappedBy = "categorias")
    private Set<Atributo> atributos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "categoriaPadre")
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "categoria")
    private Set<Producto> productos = new LinkedHashSet<>();

}
