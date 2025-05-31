package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.EstadoPrestamo;
import com.example.demo.entities.Prestamo;

@Repository
public class PrestamoRepository {
	
	private final Map<String, Prestamo> prestamos = new ConcurrentHashMap<>();

    public void save(Prestamo prestamo) {
        prestamos.put(prestamo.id(), prestamo);
    }

    public Optional<Prestamo> findById(String id) {
        return Optional.ofNullable(prestamos.get(id));
    }

    public List<Prestamo> findAll() {
        return new ArrayList<>(prestamos.values());
    }

    public void delete(String id) {
        prestamos.remove(id);
    }

    public void update(String id, Prestamo prestamoActualizado) {
        prestamos.put(id, prestamoActualizado);
    }

    public List<Prestamo> findActivos() {
        return prestamos.values().stream()
                .filter(p -> p.estado() == EstadoPrestamo.PENDIENTE)
                .collect(Collectors.toList());
    }
}
