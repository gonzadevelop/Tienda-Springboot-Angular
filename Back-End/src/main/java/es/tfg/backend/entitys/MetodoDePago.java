package es.tfg.backend.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "metodos_pago_usuario")
@Getter
@Setter
public class MetodoDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Size(max = 255)
    @NotNull
    @Column(name = "token_referencia", nullable = false)
    private String tokenReferencia;

    @Size(max = 100)
    @Column(name = "alias", length = 100)
    private String alias;

    @Size(max = 50)
    @Column(name = "marca_tarjeta", length = 50)
    private String marcaTarjeta;

    @Size(max = 4)
    @NotNull
    @Column(name = "ultimos_4_digitos", nullable = false, length = 4)
    private String ultimos4Digitos;

    @Size(max = 7)
    @NotNull
    @Column(name = "fecha_expiracion", nullable = false, length = 7)
    private String fechaExpiracion;

    @Column(name = "es_principal")
    private Boolean esPrincipal;

    @OneToMany(mappedBy = "metodoPagoUsuario")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

}
