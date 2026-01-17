package com.example.hospital.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import com.example.hospital.entity.Consulta;
import com.example.hospital.service.ConsultaService;

@RestController
@RequestMapping("api/consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> obtenerListas() {
        List<Consulta> consultas = consultaService.listarConsulta();
        return ResponseEntity.ok(consultas);
    }

    @PostMapping("/crear")
    public ResponseEntity<Consulta> crearConsulta(@RequestBody Consulta consulta) {
        Consulta nuevaConsulta = consultaService.crearConsulta(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaConsulta);
    }
}