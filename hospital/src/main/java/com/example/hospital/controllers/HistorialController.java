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

import lombok.RequiredArgsConstructor;

import com.example.hospital.entity.Historial;
import com.example.hospital.service.HistorialService;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor
public class HistorialController {
    
    private final HistorialService historialService;

    @PostMapping
    public ResponseEntity<Historial> crearHistorial(@RequestBody Historial historial) {
        Historial nuevoHistorial = historialService.crearHistorial(historial);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoHistorial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial> actualizarHistorial(@PathVariable Integer id, @RequestBody Historial historial) {
        try {
            Historial historialActualizado = historialService.actualizarHistorial(id, historial);
            return ResponseEntity.ok(historialActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Historial>> listarHistoriales() {
        List<Historial> historiales = historialService.listarHistorial();
        return ResponseEntity.ok(historiales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> obtenerHistorialPorId(@PathVariable Integer id) {
        Optional<Historial> historial = historialService.obtenerHistorialPorId(id);
        return historial.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Integer id) {
        try {
            historialService.eliminarHistorial(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
