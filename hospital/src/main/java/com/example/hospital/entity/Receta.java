package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer idReceta;

    @Column(columnDefinition = "TEXT")
    private String medicamentos;

    @Column(columnDefinition = "TEXT")
    private String indicaciones;

    // FK: id_consulta (Unique para asegurar 1 a 1)
    @OneToOne
    @JoinColumn(name = "id_consulta", unique = true, nullable = false)
    private Consulta consulta;
}