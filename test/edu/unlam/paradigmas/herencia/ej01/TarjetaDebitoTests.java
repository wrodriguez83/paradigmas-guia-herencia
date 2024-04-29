package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TarjetaDebitoTests {
	Cuenta cuenta = new Cuenta(2000);
	TarjetaDebito tarjeta = new TarjetaDebito(cuenta);

	@Test
	void ElSaldoDeLaCuentaDebeSerCero() {
		assertEquals(2000, cuenta.consultarSaldo());
		assertEquals(tarjeta.cuenta, cuenta);
	}

	@Test
	void ComprarDebeCambiarElSaldoDeLaCuenta() {
		tarjeta.comprar(500);
		assertEquals(1500, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
		assertEquals(-500, cuenta.getTransacciones().get(1).importe);
		assertEquals("Compra tarjeta débito.", cuenta.getTransacciones().get(1).motivo);

		tarjeta.comprar(100);
		assertEquals(1400, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(-100, cuenta.getTransacciones().get(2).importe);
		assertEquals("Compra tarjeta débito.", cuenta.getTransacciones().get(2).motivo);

		tarjeta.comprar(200);
		assertEquals(1200, cuenta.consultarSaldo());
		assertEquals(4, cuenta.getTransacciones().size());
		assertEquals(-200, cuenta.getTransacciones().get(3).importe);
		assertEquals("Compra tarjeta débito.", cuenta.getTransacciones().get(3).motivo);
	}

	@Test
	void NoDebeComprarSiElImporteEsMayorAlSaldoDeLaCuenta() {
		tarjeta.comprar(500);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			tarjeta.comprar(1600);
		});

		assertEquals("El importe excede el saldo actual.", exception.getMessage());
		assertEquals(1500, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
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
