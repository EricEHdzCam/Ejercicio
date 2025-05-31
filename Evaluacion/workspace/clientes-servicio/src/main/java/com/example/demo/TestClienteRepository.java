package com.example.demo;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.TipoCliente;
import com.example.demo.repositories.ClienteRepository;

public class TestClienteRepository {
    public static void main(String[] args) {
        ClienteRepository repo = new ClienteRepository();

        // Crear cliente
        Cliente c1 = new Cliente("1", "Juan", "juan@mail.com", 30, TipoCliente.REGULAR);
        repo.save(c1);

        // Leer todos
        System.out.println("Todos los clientes: " + repo.findAll());

        // Buscar por id
        System.out.println("Buscar cliente con id 1: " + repo.findById("1"));

        // Actualizar
        Cliente c1Actualizado = new Cliente("1", "Juan Pérez", "juanp@mail.com", 31, TipoCliente.VIP);
        repo.update("1", c1Actualizado);
        System.out.println("Cliente actualizado: " + repo.findById("1"));

        // Eliminar
        repo.delete("1");
        System.out.println("Clientes después de eliminar: " + repo.findAll());
    }
}