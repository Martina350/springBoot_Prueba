package com.example.hospital.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hospital.entity.Consulta;
import com.example.hospital.repository.ConsultaRepository;
import com.example.hospital.service.ConsultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaRepository consultaRepository;

    @GetMapping
    public ResponseEntity<List<Consulta>> listarConsultas() {
        List<Consulta> consultas = consultaService.listarConsulta();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obtenerConsultaPorId(@PathVariable Integer id) {
        Optional<Consulta> consulta = consultaRepository.findById(id);
        return consulta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Consulta>> obtenerConsultasPorPaciente(@PathVariable Integer idPaciente) {
        List<Consulta> consultas = consultaRepository.findByPacienteIdPaciente(idPaciente);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/doctor/{idDoctor}")
    public ResponseEntity<List<Consulta>> obtenerConsultasPorDoctor(@PathVariable Integer idDoctor) {
        List<Consulta> consultas = consultaRepository.findByDoctorIdDoctor(idDoctor);
        return ResponseEntity.ok(consultas);
    }

    @PostMapping
    public ResponseEntity<Consulta> crearConsulta(@RequestBody Consulta consulta) {
        Consulta nuevaConsulta = consultaService.crearConsulta(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizarConsulta(@PathVariable Integer id, @RequestBody Consulta consulta) {
        Optional<Consulta> consultaExistente = consultaRepository.findById(id);
        if (consultaExistente.isPresent()) {
            Consulta c = consultaExistente.get();
            c.setFecha(consulta.getFecha());
            c.setMotivo(consulta.getMotivo());
            c.setDiagnostico(consulta.getDiagnostico());
            c.setPaciente(consulta.getPaciente());
            c.setDoctor(consulta.getDoctor());
            Consulta actualizada = consultaRepository.save(c);
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsulta(@PathVariable Integer id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}