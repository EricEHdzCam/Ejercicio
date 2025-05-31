package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Cliente;

public class ClienteRepository {
	
	private final List<Cliente> clientes = new ArrayList<>();

    // Crear cliente
    public void save(Cliente cliente) {
        clientes.add(cliente);
    }

    // Obtener todos
    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }

    // Buscar por ID
    public Optional<Cliente> findById(String id) {
        return clientes.stream()
                .filter(c -> c.id().equals(id))
                .findFirst();
    }

    // Actualizar cliente por ID
    public boolean update(String id, Cliente clienteActualizado) {
        Optional<Cliente> clienteOpt = findById(id);
        if (clienteOpt.isPresent()) {
            int index = clientes.indexOf(clienteOpt.get());
            clientes.set(index, clienteActualizado);
            return true;
        }
        return false;
    }

    // Eliminar cliente por ID
    public boolean delete(String id) {
        return clientes.removeIf(c -> c.id().equals(id));
    }
}
