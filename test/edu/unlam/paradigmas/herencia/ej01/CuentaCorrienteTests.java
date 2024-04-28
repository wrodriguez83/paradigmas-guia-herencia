package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CuentaCorrienteTests {
	CuentaCorriente cuenta = new CuentaCorriente(1000, 500);

	@Test
	void CuentaCorrienteDebeHeredarDeCuenta() {
		assertInstanceOf(Cuenta.class, cuenta);
	}

	@Test
	void DebeTenerEnCuentaElDescubiertoAlConsultarElSaldo() {
		assertEquals(1500, cuenta.consultarSaldo());
	}

	@Test
	void ElSaldoDebeSerCero() {
		CuentaCorriente cuenta = new CuentaCorriente(0, 0);
		assertEquals(0, cuenta.consultarSaldo());
	}

	@Test
	void NoDebePermitirDescubiertoNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new CuentaCorriente(100, -100);
		});
		assertEquals("El descubierto no puede ser menor a cero.", exception.getMessage());
	}

	@Test
	void DebePermitirExtraerSiNoSuperaElSaldoMasElDescubierto() {
		cuenta.extraer(1400);

		assertEquals(100, cuenta.consultarSaldo());
	}

	@Test
	void NoDebePermitirExtraerSiElSaldoMasElDescubiertoQuedaNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			cuenta.extraer(2000);
		});
		assertEquals("El importe excede el saldo actual.", exception.getMessage());
	}
}
