package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.entities.EstadoPrestamo;
import com.example.demo.entities.Prestamo;
import com.example.demo.repositories.IClienteRepository;
import com.example.demo.repositories.PrestamoRepository;

@Service
public class PrestamoService {

    private static final Logger log = LoggerFactory.getLogger(PrestamoService.class);

    private final PrestamoRepository prestamoRepository;
    private final IClienteRepository clienteRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, IClienteRepository clienteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository;
    }

    public void crearPrestamo(Prestamo prestamo) {
        log.info("Creando préstamo: {}", prestamo);
        prestamoRepository.save(prestamo);
    }
    
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> obtenerActivos() {
        return prestamoRepository.findActivos();
    }

    public Optional<Prestamo> buscarPorId(String id) {
        return prestamoRepository.findById(id);
    }

    public void actualizarEstado(String id, EstadoPrestamo estado) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);
        if (prestamoOpt.isPresent()) {
            Prestamo original = prestamoOpt.get();
            Prestamo actualizado = new Prestamo(
                original.id(),
                original.monto(),
                original.clienteId(),
                original.fecha(),
                estado
            );
            prestamoRepository.update(id, actualizado);
            log.info("Estado actualizado: {}", actualizado);
        } else {
            log.warn("No se encontró préstamo con ID {}", id);
        }
    }

    public void eliminarPrestamo(String id) {
        prestamoRepository.delete(id);
        log.info("Préstamo eliminado con ID {}", id);
    }

    public double calcularMontoConInteres(String prestamoId) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(prestamoId);
        if (prestamoOpt.isEmpty()) {
            throw new IllegalArgumentException("Préstamo no encontrado");
        }

        Prestamo prestamo = prestamoOpt.get();
        String clienteId = prestamo.clienteId();
        var clienteOpt = clienteRepository.buscarPorId(clienteId);

        if (clienteOpt.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado para el préstamo");
        }

        var cliente = clienteOpt.get();
        double tasa = switch (cliente.tipoCliente()) {
            case VIP -> 0.05;
            case REGULAR -> 0.10;
        };

        double total = prestamo.monto() + (prestamo.monto() * tasa);
        log.info("Monto con interés para cliente {} ({}): {}", clienteId, cliente.tipoCliente(), total);
        return total;
    }
}