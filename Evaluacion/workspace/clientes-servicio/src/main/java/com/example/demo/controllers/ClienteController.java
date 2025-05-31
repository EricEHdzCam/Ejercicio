package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Cliente;
import com.example.demo.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteService service;

    public ClienteController() {
        this.service = new ClienteService();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente")
    public ResponseEntity<Void> crearCliente(@RequestBody Cliente cliente) {
        service.crearCliente(cliente);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un cliente por ID")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente por ID")
    public ResponseEntity<Void> actualizarCliente(@PathVariable String id, @RequestBody Cliente actualizado) {
        boolean ok = service.actualizarCliente(id, actualizado);
        return ok ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por ID")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) {
        boolean eliminado = service.eliminarCliente(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/beneficio")
    @Operation(summary = "Aplicar beneficio según tipo de cliente")
    public ResponseEntity<Void> aplicarBeneficio(@PathVariable String id) {
    	return service.buscarPorId(id)
    	        .<ResponseEntity<Void>>map(cliente -> {
    	            service.aplicarBeneficio(cliente);
    	            return ResponseEntity.ok().build();
    	        })
    	        .orElse(ResponseEntity.notFound().build());
    }
}
    
    