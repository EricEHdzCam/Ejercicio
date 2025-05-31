package com.example.demo.entities;

public record Cliente(
	    String id,
	    String nombre,
	    String email,
	    int edad,
	    TipoCliente tipoCliente
		) {

}
