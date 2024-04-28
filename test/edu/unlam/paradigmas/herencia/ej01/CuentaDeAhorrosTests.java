package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

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

		cuenta.reservarSaldo(150);
		assertEquals(500, cuenta.consultarSaldo());
	}

	@Test
	void DebeAgregarSaldoAlConsolidar() {
		cuenta.reservarSaldo(800);
		assertEquals(200, cuenta.consultarSaldo());

		cuenta.consolidarSaldos();
		assertEquals(1000, cuenta.consultarSaldo());

		cuenta.reservarSaldo(300);
		assertEquals(700, cuenta.consultarSaldo());

		cuenta.consolidarSaldos();
		assertEquals(1000, cuenta.consultarSaldo());
	}
}
