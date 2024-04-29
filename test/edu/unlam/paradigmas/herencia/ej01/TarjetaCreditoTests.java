package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TarjetaCreditoTests {
	Cuenta cuenta = new Cuenta(2000);
	TarjetaCredito tarjeta = new TarjetaCredito(cuenta);

	@Test
	void ElSaldoDeLaCuentaDebeSerCero() {
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(tarjeta.cuenta, cuenta);
	}

	@Test
	void ComprarNoDebeCambiarElSaldoDeLaCuenta() {
		tarjeta.comprar(500);
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());

		tarjeta.comprar(1500);
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());

		tarjeta.comprar(2500);
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());
	}

	@Test
	void DebeDebitarLasComprasDeLaTarjetaMasComisionesEnLaCuenta() {
		tarjeta.comprar(500);
		tarjeta.comprar(100);
		tarjeta.comprar(200);
		tarjeta.debitar();

		assertEquals(1176, cuenta.consultarSaldo());
		assertEquals(5, cuenta.getTransacciones().size());
		assertEquals(-500, cuenta.getTransacciones().get(1).importe);
		assertEquals("Compra tarjeta de crédito.", cuenta.getTransacciones().get(1).motivo);
		assertEquals(-100, cuenta.getTransacciones().get(2).importe);
		assertEquals("Compra tarjeta de crédito.", cuenta.getTransacciones().get(2).motivo);
		assertEquals(-200, cuenta.getTransacciones().get(3).importe);
		assertEquals("Compra tarjeta de crédito.", cuenta.getTransacciones().get(3).motivo);
		assertEquals(-24, cuenta.getTransacciones().get(4).importe);
		assertEquals("Comisión tarjeta de crédito.", cuenta.getTransacciones().get(4).motivo);
	}

	@Test
	void DebeResetearElSaldoDeLaTarjetaAlDebitarYModificarSaldoEnLaCuenta() {
		tarjeta.comprar(500);
		tarjeta.comprar(100);
		tarjeta.debitar();
		tarjeta.comprar(200);
		tarjeta.debitar();

		assertEquals(1176, cuenta.consultarSaldo());
		assertEquals(6, cuenta.getTransacciones().size());
		assertEquals(-500, cuenta.getTransacciones().get(1).importe);
		assertEquals(-100, cuenta.getTransacciones().get(2).importe);
		assertEquals(-18, cuenta.getTransacciones().get(3).importe);
		assertEquals("Comisión tarjeta de crédito.", cuenta.getTransacciones().get(3).motivo);
		assertEquals(-200, cuenta.getTransacciones().get(4).importe);
		assertEquals(-6, cuenta.getTransacciones().get(5).importe);
		assertEquals("Comisión tarjeta de crédito.", cuenta.getTransacciones().get(5).motivo);
	}

	@Test
	void NoDebeDebitarSiElSaldoEsMayorAlDeLaCuenta() {
		tarjeta.comprar(500);
		tarjeta.comprar(1500);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			tarjeta.debitar();
		});

		assertEquals("El importe excede el saldo actual.", exception.getMessage());
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());
	}

	@Test
	void NoDebeComprarSiElImporteEsNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			tarjeta.comprar(-500);
		});

		assertEquals("El importe no puede ser menor a cero.", exception.getMessage());
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(1, cuenta.getTransacciones().size());
	}
}
