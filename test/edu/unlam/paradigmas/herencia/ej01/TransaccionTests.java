package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class TransaccionTests {
	Transaccion transaccion = new Transaccion(300, "Inicial");

	@Test
	void ElImporteYMotivoDebenSerLosDados() {
		assertEquals(300, transaccion.importe);
		assertEquals("Inicial", transaccion.motivo);
	}
}
