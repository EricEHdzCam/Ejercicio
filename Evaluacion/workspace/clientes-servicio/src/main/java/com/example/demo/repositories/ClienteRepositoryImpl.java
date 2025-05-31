package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entities.Cliente;

@Repository
public class ClienteRepositoryImpl implements IClienteRepository{
	
	private final Map<String, Cliente> baseDeDatos = new HashMap<>();

    @Override
    public void guardar(Cliente cliente) {
        baseDeDatos.put(cliente.id(), cliente);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    @Override
    public void actualizar(String id, Cliente cliente) {
        baseDeDatos.put(id, cliente);
    }

    @Override
    public void eliminar(String id) {
        baseDeDatos.remove(id);
    }
}