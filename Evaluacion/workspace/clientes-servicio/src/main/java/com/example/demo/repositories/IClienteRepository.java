package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Cliente;

public interface IClienteRepository {
	
	void guardar(Cliente cliente);
    List<Cliente> obtenerTodos();
    Optional<Cliente> buscarPorId(String id);
    void actualizar(String id, Cliente cliente);
    void eliminar(String id);
}