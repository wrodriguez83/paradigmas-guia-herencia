package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PlazoFijoTests {
	Cuenta cuenta = new Cuenta(2000);
	PlazoFijo plazoFijo = new PlazoFijo(1000, cuenta);

	@Test
	void ElSaldoDeLaCuentaDebeSerCero() {
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(plazoFijo.cuenta, cuenta);
	}

	@Test
	void DebeRegistrarLaCreacionDelPlazoFijo() {
		assertEquals(2, cuenta.getTransacciones().size());
		assertEquals(-1000, cuenta.getTransacciones().get(1).importe);
		assertEquals("Creación plazo fijo.", cuenta.getTransacciones().get(1).motivo);
	}

	@Test
	void NoDebeCrearPlazoFijoSiSobrepasaElSaldoDeLaCuenta() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new PlazoFijo(2000, cuenta);
		});

		assertEquals("El importe excede el saldo actual.", exception.getMessage());
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
	}

	@Test
	void NoDebeCrearPlazoFijoConImporteNegativo() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			new PlazoFijo(-200, cuenta);
		});

		assertEquals("El importe no puede ser menor a cero.", exception.getMessage());
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(2, cuenta.getTransacciones().size());
	}

	@Test
	void DebeAcreditarElPlazoFijoMasInteresEnLaCuenta() {
		plazoFijo.acreditar();
		assertEquals(2030, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(1030, cuenta.getTransacciones().get(2).importe);
		assertEquals("Acreditación plazo fijo.", cuenta.getTransacciones().get(2).motivo);
	}

	@Test
	void DebeAcreditarElPlazoFijoMasInteresEnLaCuentaOtroValor() {
		Cuenta cuenta = new Cuenta(1000);
		PlazoFijo plazoFijo = new PlazoFijo(300, cuenta);

		plazoFijo.acreditar();
		assertEquals(1009, cuenta.consultarSaldo());
		assertEquals(3, cuenta.getTransacciones().size());
		assertEquals(309, cuenta.getTransacciones().get(2).importe);
		assertEquals("Acreditación plazo fijo.", cuenta.getTransacciones().get(2).motivo);
	}
}
