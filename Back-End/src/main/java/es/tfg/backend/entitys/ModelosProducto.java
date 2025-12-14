package es.tfg.backend.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "modelos_producto")
@Getter
@Setter
public class ModelosProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre_modelo", nullable = false, length = 100)
    private String nombreModelo;

    @NotNull
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "porcentaje_oferta", precision = 4, scale = 2)
    private BigDecimal porcentajeOferta;

    @Column(name = "stock", columnDefinition = "int UNSIGNED not null")
    private Integer stock;

    @OneToMany(mappedBy = "modeloProducto")
    private Set<ValoresAtributos> valoresAtributosProductos = new LinkedHashSet<>();

}
