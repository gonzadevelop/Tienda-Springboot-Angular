package es.tfg.backend.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "atributos")
@Getter
@Setter
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 30)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotNull
    @Lob
    @Column(name = "tipo_dato", nullable = false)
    private String tipoDato;

    @ManyToMany
    @JoinTable(name = "atributo_categoria",
            joinColumns = @JoinColumn(name = "id_atributo"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "atributo")
    private Set<ValoresAtributos> valoresAtributosProductos = new LinkedHashSet<>();

}
