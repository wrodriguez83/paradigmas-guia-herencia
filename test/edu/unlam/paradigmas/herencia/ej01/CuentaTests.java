package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CuentaTests {
	Cuenta cuenta = new Cuenta(1000);

	@Test
	void ElSaldoInicialDebeSerElDado() {
		assertEquals(1000, cuenta.getSaldo());
	}

	@Test
	void ElSaldoDebeSerCero() {
		Cuenta cuenta = new Cuenta(0);
		assertEquals(0, cuenta.getSaldo());
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
		assertEquals(2000, cuenta.getSaldo());

		cuenta.depositar(550);
		assertEquals(2550, cuenta.getSaldo());
	}

	@Test
	void DebeRestarSaldoAlExtraer() {
		cuenta.extraer(550);
		assertEquals(450, cuenta.getSaldo());

		cuenta.extraer(300);
		assertEquals(150, cuenta.getSaldo());
	}

	@Test
	void NoDebePermitirDepositarImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.depositar(-550);
		});
		assertEquals("El importe a depositar no puede ser menor a cero.", exception.getMessage());
	}

	@Test
	void NoDebePermitirExtraerImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(-500);
		});
		assertEquals("El importe a extraer no puede ser menor a cero.", exception.getMessage());
	}
	
	@Test
	void NoDebePermitirExtraerSiElSaldoQuedaNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(2000);
		});
		assertEquals("El importe a extraer excede el saldo actual.", exception.getMessage());
	}
}
