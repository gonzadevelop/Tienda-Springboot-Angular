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
@Table(name = "direcciones")
@Getter
@Setter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "es_principal")
    private Boolean esPrincipal;

    @Size(max = 255)
    @NotNull
    @Column(name = "calle", nullable = false)
    private String calle;

    @Size(max = 10)
    @Column(name = "numero", length = 10)
    private String numero;

    @Size(max = 50)
    @Column(name = "piso", length = 50)
    private String piso;

    @Size(max = 10)
    @NotNull
    @Column(name = "codigo_postal", nullable = false, length = 10)
    private String codigoPostal;

    @Size(max = 100)
    @NotNull
    @Column(name = "municipio", nullable = false, length = 100)
    private String municipio;

    @Size(max = 100)
    @NotNull
    @Column(name = "provincia", nullable = false, length = 100)
    private String provincia;

    @OneToMany(mappedBy = "direccionEnvio")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

}
