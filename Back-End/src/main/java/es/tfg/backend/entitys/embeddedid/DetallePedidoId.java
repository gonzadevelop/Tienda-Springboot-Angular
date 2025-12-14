package es.tfg.backend.entitys.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DetallePedidoId {

    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_modelo_producto")
    private Long idModeloProducto;
}
