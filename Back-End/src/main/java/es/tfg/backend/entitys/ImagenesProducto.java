package es.tfg.backend.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "imagenes_producto")
@Getter
@Setter
public class ImagenesProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Size(max = 512)
    @NotNull
    @Column(name = "ruta_archivo", nullable = false, length = 512)
    private String rutaArchivo;

    @Column(name = "orden", columnDefinition = "int UNSIGNED")
    private Long orden;

    @Column(name = "es_principal")
    private Boolean esPrincipal;
}
