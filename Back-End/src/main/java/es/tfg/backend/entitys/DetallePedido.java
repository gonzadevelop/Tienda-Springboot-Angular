package es.tfg.backend.entitys;


import es.tfg.backend.entitys.embeddedid.DetallePedidoId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedido")
@Getter
@Setter
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoId id;

    @Column(name = "cantidad", columnDefinition = "int UNSIGNED not null")
    private Long cantidad;

    @NotNull
    @Column(name = "precio_unitario_historico", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitarioHistorico;

    @MapsId("idPedido")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @MapsId("idModeloProducto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_modelo_producto", nullable = false)
    private ModelosProducto modeloProducto;

}
