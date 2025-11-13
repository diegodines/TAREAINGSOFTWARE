package com.diego.crud.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cotizacion_item")
@Data @NoArgsConstructor @AllArgsConstructor
public class CotizacionItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cotizacion cotizacion;

    @ManyToOne(optional = false)
    private Mueble mueble;

    @ManyToOne(optional = true) // <- ahora puede ser nula
    @JoinColumn(name = "variante_id", nullable = true)
    private Variante variante;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal subtotal; // precio calculado * cantidad
}
