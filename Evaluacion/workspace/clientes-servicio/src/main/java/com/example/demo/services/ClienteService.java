package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteRepository repository;

    public ClienteService() {
        this.repository = new ClienteRepository();
    }

    public void crearCliente(Cliente cliente) {
        log.info("Creando cliente: {}", cliente);
        repository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(String id) {
        return repository.findById(id);
    }

    public boolean actualizarCliente(String id, Cliente actualizado) {
        boolean actualizadoOk = repository.update(id, actualizado);
        if (actualizadoOk) {
            log.info("Cliente actualizado: {}", actualizado);
        } else {
            log.warn("No se encontró cliente con id: {}", id);
        }
        return actualizadoOk;
    }

    public boolean eliminarCliente(String id) {
        boolean eliminado = repository.delete(id);
        if (eliminado) {
            log.info("Cliente eliminado con id: {}", id);
        } else {
            log.warn("No se encontró cliente para eliminar con id: {}", id);
        }
        return eliminado;
    }

    public void aplicarBeneficio(Cliente cliente) {
        switch (cliente.tipoCliente()) {
            case VIP -> log.info("Cliente VIP: aplica descuento del 5%");
            case REGULAR -> log.info("Cliente REGULAR: sin descuento");
        }
    }
}