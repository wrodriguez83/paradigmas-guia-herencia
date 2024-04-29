package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CuentaTests {
	Cuenta cuenta = new Cuenta(1000);

	@Test
	void ElSaldoInicialDebeSerElDado() {
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());
		assertEquals(1000, cuenta.getTransacciones().get(0).importe);
		assertEquals("Saldo inicial", cuenta.getTransacciones().get(0).motivo);
	}

	@Test
	void ElSaldoDebeSerCero() {
		Cuenta cuenta = new Cuenta(0);
		assertEquals(0, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());
		assertEquals(0, cuenta.getTransacciones().get(0).importe);
		assertEquals("Saldo inicial", cuenta.getTransacciones().get(0).motivo);
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
		cuenta.depositar(1000, "Test");
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
		assertEquals(1000, cuenta.getTransacciones().get(1).importe);
		assertEquals("Test", cuenta.getTransacciones().get(1).motivo);

		cuenta.depositar(550, "Test2");
		assertEquals(2550, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(550, cuenta.getTransacciones().get(2).importe);
		assertEquals("Test2", cuenta.getTransacciones().get(2).motivo);
	}

	@Test
	void DebeRestarSaldoAlExtraer() {
		cuenta.extraer(550, "Test");
		assertEquals(450, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
		assertEquals(-550, cuenta.getTransacciones().get(1).importe);
		assertEquals("Test", cuenta.getTransacciones().get(1).motivo);

		cuenta.extraer(300, "Test2");
		assertEquals(150, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(-300, cuenta.getTransacciones().get(2).importe);
		assertEquals("Test2", cuenta.getTransacciones().get(2).motivo);
	}

	@Test
	void NoDebePermitirDepositarImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.depositar(-550, "Test");
		});
		assertEquals("El importe no puede ser menor a cero.", exception.getMessage());
		assertEquals(1, cuenta.getTransacciones().size());
		assertEquals(1000, cuenta.getTransacciones().get(0).importe);
		assertEquals("Saldo inicial", cuenta.getTransacciones().get(0).motivo);
	}

	@Test
	void NoDebePermitirExtraerImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(-500, "Test");
		});
		assertEquals("El importe no puede ser menor a cero.", exception.getMessage());
		assertEquals(1, cuenta.getTransacciones().size());
		assertEquals(1000, cuenta.getTransacciones().get(0).importe);
		assertEquals("Saldo inicial", cuenta.getTransacciones().get(0).motivo);
	}

	@Test
	void NoDebePermitirExtraerSiElSaldoQuedaNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(2000, "Test");
		});
		assertEquals("El importe excede el saldo actual.", exception.getMessage());
		assertEquals(1, cuenta.getTransacciones().size());
		assertEquals(1000, cuenta.getTransacciones().get(0).importe);
		assertEquals("Saldo inicial", cuenta.getTransacciones().get(0).motivo);
	}
}
