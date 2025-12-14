package es.tfg.backend.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "valores_atributos_producto")
@Getter
@Setter
public class ValoresAtributos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_modelo_producto", nullable = false)
    private ModelosProducto modeloProducto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_atributo", nullable = false)
    private Atributo atributo;

    @Size(max = 255)
    @NotNull
    @Column(name = "valor_texto", nullable = false)
    private String valorTexto;
}
