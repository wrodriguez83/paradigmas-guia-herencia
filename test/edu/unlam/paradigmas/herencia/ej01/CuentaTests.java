package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaTests {
	Cuenta cuenta = new Cuenta();

	@Test
	void ElSaldoDebeSerCero() {
		assertEquals(0, cuenta.saldo);
	}
	
	@Test
	void ElSaldoDebeSerModificable() {
		cuenta.saldo+= 1000;
		assertEquals(1000, cuenta.saldo);
		
		cuenta.saldo-= 550;
		assertEquals(450, cuenta.saldo);
	}
	
	@Test
	void DebePermitirSaldoNegativo() {
		cuenta.saldo+= 1000;
		cuenta.saldo-= 1550;
		assertEquals(-550, cuenta.saldo);
	}
}
