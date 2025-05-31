package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.EstadoPrestamo;
import com.example.demo.entities.Prestamo;
import com.example.demo.services.PrestamoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
	
	private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo préstamo")
    public ResponseEntity<Void> crear(@RequestBody Prestamo prestamo) {
        service.crearPrestamo(prestamo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtener préstamos activos")
    public ResponseEntity<List<Prestamo>> activos() {
        return ResponseEntity.ok(service.obtenerActivos());
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de préstamo")
    public ResponseEntity<Void> actualizarEstado(
            @PathVariable String id,
            @RequestParam EstadoPrestamo estado
    ) {
        service.actualizarEstado(id, estado);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar préstamo")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los préstamos")
    public ResponseEntity<List<Prestamo>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}/interes")
    @Operation(summary = "Calcular monto con interés según tipo de cliente")
    public ResponseEntity<Double> calcularMontoConInteres(@PathVariable String id) {
        try {
            double monto = service.calcularMontoConInteres(id);
            return ResponseEntity.ok(monto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}