package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class CuentaDeAhorrosTests {
	CuentaDeAhorros cuenta = new CuentaDeAhorros(1000);

	@Test
	void CuentaDeAhorrosDebeHeredarDeCuenta() {
		assertInstanceOf(Cuenta.class, cuenta);
	}

	@Test
	void DebeSacarSaldoAlReservar() {
		cuenta.reservarSaldo(350);
		assertEquals(650, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
		assertEquals(-350, cuenta.getTransacciones().get(1).importe);
		assertEquals("Reservar saldo", cuenta.getTransacciones().get(1).motivo);

		cuenta.reservarSaldo(150);
		assertEquals(500, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(-150, cuenta.getTransacciones().get(2).importe);
		assertEquals("Reservar saldo", cuenta.getTransacciones().get(2).motivo);
	}

	@Test
	void DebeAgregarSaldoAlConsolidar() {
		cuenta.reservarSaldo(800);
		assertEquals(200, cuenta.consultarSaldo());

		cuenta.consolidarSaldos();
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(800, cuenta.getTransacciones().get(2).importe);
		assertEquals("Consolidar saldo", cuenta.getTransacciones().get(2).motivo);

		cuenta.reservarSaldo(300);
		assertEquals(700, cuenta.consultarSaldo());

		cuenta.consolidarSaldos();
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(5, cuenta.getTransacciones().size());
		assertEquals(300, cuenta.getTransacciones().get(4).importe);
		assertEquals("Consolidar saldo", cuenta.getTransacciones().get(4).motivo);
	}
}
