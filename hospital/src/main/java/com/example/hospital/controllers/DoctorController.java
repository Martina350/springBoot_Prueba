package com.example.hospital.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

import com.example.hospital.entity.Doctor;

import com.example.hospital.service.DoctorService;

@RestController
@RequestMapping("api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping
    public ResponseEntity<List<?>> obtenerListas() {
        List<Doctor> doctors = doctorService.listarDoctor();
        return ResponseEntity.ok(doctors);
    }
        
    @PostMapping("/crear")
    public ResponseEntity<?> crearDoctor(@RequestBody Doctor doctor) {
        Doctor nuevoDoctor = doctorService.crearDoctor(doctor)
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }
    
}