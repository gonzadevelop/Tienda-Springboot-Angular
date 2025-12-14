package es.tfg.backend.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Size(max = 25)
    @NotNull
    @Column(name = "marca", nullable = false, length = 25)
    private String marca;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private Set<ImagenesProducto> imagenesProductos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producto")
    private Set<ListaFavorito> listaFavoritos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producto")
    private Set<ModelosProducto> modelosProductos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producto")
    private Set<Opinion> opiniones = new LinkedHashSet<>();

}
