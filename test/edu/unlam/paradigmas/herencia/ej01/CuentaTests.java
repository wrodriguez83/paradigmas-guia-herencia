package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CuentaTests {
	Cuenta cuenta = new Cuenta(1000);

	@Test
	void ElSaldoInicialDebeSerElDado() {
		assertEquals(1000, cuenta.consultarSaldo());
	}

	@Test
	void ElSaldoDebeSerCero() {
		Cuenta cuenta = new Cuenta(0);
		assertEquals(0, cuenta.consultarSaldo());
	}

	@Test
	void NoDebePermitirSaldoInicialNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new Cuenta(-100);
		});
		assertEquals("El saldo inicial no puede ser menor a cero.", exception.getMessage());
	}

	@Test
	void DebeAgregarSaldoAlDepositar() {
		cuenta.depositar(1000);
		assertEquals(2000, cuenta.consultarSaldo());

		cuenta.depositar(550);
		assertEquals(2550, cuenta.consultarSaldo());
	}

	@Test
	void DebeRestarSaldoAlExtraer() {
		cuenta.extraer(550);
		assertEquals(450, cuenta.consultarSaldo());

		cuenta.extraer(300);
		assertEquals(150, cuenta.consultarSaldo());
	}

	@Test
	void NoDebePermitirDepositarImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.depositar(-550);
		});
		assertEquals("El importe no puede ser menor a cero.", exception.getMessage());
	}

	@Test
	void NoDebePermitirExtraerImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(-500);
		});
		assertEquals("El importe no puede ser menor a cero.", exception.getMessage());
	}

	@Test
	void NoDebePermitirExtraerSiElSaldoQuedaNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(2000);
		});
		assertEquals("El importe excede el saldo actual.", exception.getMessage());
	}
}
