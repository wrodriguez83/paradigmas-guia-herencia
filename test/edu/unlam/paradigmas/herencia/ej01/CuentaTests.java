package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.opentest4j.IncompleteExecutionException;

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

	@Test
	void DebePoderTransferirEntreCuentas() {
		Cuenta cuentaDestino = new Cuenta(0);
		cuenta.transferir(500, cuentaDestino);
		assertEquals(500, cuenta.consultarSaldo());
		assertEquals(500, cuentaDestino.consultarSaldo());

		cuenta.depositar(3150);
		cuenta.transferir(150, cuentaDestino);

		assertEquals(3500, cuenta.consultarSaldo());
		assertEquals(650, cuentaDestino.consultarSaldo());
	}

	@Test
	void NoDebeTransferirMasDelSaldoPermitido() {
		cuenta = new Cuenta(100);
		Cuenta cuentaDestino = new Cuenta(0);

		IncompleteExecutionException exception = assertThrows(IncompleteExecutionException.class, () -> {
			cuenta.transferir(500, cuentaDestino);
		});
		assertEquals("No se pudo realizar la transferencia.", exception.getMessage());
		assertEquals(100, cuenta.consultarSaldo());
		assertEquals(0, cuentaDestino.consultarSaldo());
	}
}
