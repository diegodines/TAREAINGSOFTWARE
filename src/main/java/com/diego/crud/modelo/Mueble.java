package com.diego.crud.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "mueble")
@Data @NoArgsConstructor @AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Mueble {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private TipoMueble tipo;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal precioBase;

    @Column(nullable=false)
    private Integer stock;

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private EstadoMueble estado = EstadoMueble.ACTIVO;

    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private Tamano tamano;

    @Column(nullable=false)
    private String material;

    @OneToMany(mappedBy = "mueble", cascade = CascadeType.ALL, orphanRemoval = true)
    
    private List<Variante> variantes = new ArrayList<>();
}
