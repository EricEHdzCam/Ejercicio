package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entities.Cliente;
import com.example.demo.entities.EstadoPrestamo;
import com.example.demo.entities.Prestamo;
import com.example.demo.entities.TipoCliente;
import com.example.demo.repositories.IClienteRepository;
import com.example.demo.repositories.PrestamoRepository;
import com.example.demo.services.PrestamoService;



class PrestamoServiceTest {
	
	private PrestamoRepository prestamoRepository;
    private IClienteRepository clienteRepository;
    private PrestamoService service;

    @BeforeEach
    void setUp() {
        prestamoRepository = mock(PrestamoRepository.class);
        clienteRepository = mock(IClienteRepository.class);
        service = new PrestamoService(prestamoRepository, clienteRepository);
    }

    @Test
    void testCalcularMontoConInteres_clienteVIP() {
        // Arrange
        String prestamoId = "123";
        Prestamo prestamo = new Prestamo(prestamoId, 1000.0, "cliente1", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        Cliente cliente = new Cliente("cliente1", "Juan", "juan@email.com", 30, TipoCliente.VIP);

        when(prestamoRepository.findById(prestamoId)).thenReturn(Optional.of(prestamo));
        when(clienteRepository.buscarPorId("cliente1")).thenReturn(Optional.of(cliente));

        // Act
        double monto = service.calcularMontoConInteres(prestamoId);

        // Assert
        assertEquals(1050.0, monto, 0.001);
    }

    @Test
    void testCalcularMontoConInteres_clienteRegular() {
        String prestamoId = "456";
        Prestamo prestamo = new Prestamo(prestamoId, 2000.0, "cliente2", LocalDate.now(), EstadoPrestamo.PENDIENTE);
        Cliente cliente = new Cliente("cliente2", "Ana", "ana@email.com", 28, TipoCliente.REGULAR);

        when(prestamoRepository.findById(prestamoId)).thenReturn(Optional.of(prestamo));
        when(clienteRepository.buscarPorId("cliente2")).thenReturn(Optional.of(cliente));

        double monto = service.calcularMontoConInteres(prestamoId);

        assertEquals(2200.0, monto, 0.001);
    }

    @Test
    void testCalcularMontoConInteres_prestamoNoExiste() {
        when(prestamoRepository.findById("noexiste")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            service.calcularMontoConInteres("noexiste");
        });
    }
}