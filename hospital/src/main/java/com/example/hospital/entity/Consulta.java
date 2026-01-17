package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Integer idConsulta;

    @Column(nullable = false)
    private LocalDateTime fecha; // Usa LocalDateTime para fecha y hora

    @Column(nullable = false)
    private String motivo;

    // FK: id_paciente
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    // FK: id_doctor
    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctor doctor;

    // Relación inversa con Receta (1 a 1)
    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
    private Receta receta;
}