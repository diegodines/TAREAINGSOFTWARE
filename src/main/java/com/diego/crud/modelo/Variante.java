package com.diego.crud.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "variante")
@Data @NoArgsConstructor @AllArgsConstructor
// Usa IDs para evitar recursión
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Variante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mueble_id")
    
    private Mueble mueble;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal incrementoPrecio;
}
