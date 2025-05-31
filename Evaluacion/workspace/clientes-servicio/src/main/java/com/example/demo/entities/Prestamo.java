package com.example.demo.entities;

import java.time.LocalDate;

public record Prestamo(
		
		String id,
	    double monto,
	    String clienteId,
	    LocalDate fecha,
	    EstadoPrestamo estado
		) {

}
