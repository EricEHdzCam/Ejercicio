package com.example.demo;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.TipoCliente;
import com.example.demo.services.ClienteService;

public class TestClienteService {
    public static void main(String[] args) {
        ClienteService service = new ClienteService();

        Cliente c1 = new Cliente("1", "Ana", "ana@mail.com", 28, TipoCliente.VIP);
        service.crearCliente(c1);

        System.out.println("Clientes: " + service.obtenerTodos());

        service.aplicarBeneficio(c1);

        Cliente actualizado = new Cliente("1", "Ana Torres", "anatorres@mail.com", 29, TipoCliente.VIP);
        service.actualizarCliente("1", actualizado);

        service.eliminarCliente("1");

        System.out.println("Clientes después de eliminar: " + service.obtenerTodos());
    }
}
