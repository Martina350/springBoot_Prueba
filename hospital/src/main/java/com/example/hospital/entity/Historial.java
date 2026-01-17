package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "id_paciente", unique = true, nullable = false)
    private Paciente paciente;
}