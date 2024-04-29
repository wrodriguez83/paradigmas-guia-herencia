package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

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

	@Test
	void DebeAgregarTransacciones() throws InterruptedException {
		List<Transaccion> transaccionesNuevas = new ArrayList<>();

		transaccionesNuevas.add(new Transaccion(10, "Test1"));
		Thread.sleep(500);

		transaccionesNuevas.add(new Transaccion(-9, "Test2"));
		Thread.sleep(500);

		cuenta.depositar(200, "Test3");
		Thread.sleep(500);

		transaccionesNuevas.add(new Transaccion(8, "Test4"));
		Thread.sleep(500);

		cuenta.extraer(200, "Test5");
		Thread.sleep(500);

		cuenta.agregarTransacciones(transaccionesNuevas);

		List<Transaccion> transacciones = cuenta.getTransacciones();
		assertEquals(1009, cuenta.consultarSaldo());
		assertEquals(6, transacciones.size());
		assertEquals(1000, transacciones.get(0).importe);
		assertEquals("Saldo inicial", transacciones.get(0).motivo);
		assertEquals(10, transacciones.get(1).importe);
		assertEquals("Test1", transacciones.get(1).motivo);
		assertEquals(-9, transacciones.get(2).importe);
		assertEquals("Test2", transacciones.get(2).motivo);
		assertEquals(200, transacciones.get(3).importe);
		assertEquals("Test3", transacciones.get(3).motivo);
		assertEquals(8, transacciones.get(4).importe);
		assertEquals("Test4", transacciones.get(4).motivo);
		assertEquals(-200, transacciones.get(5).importe);
		assertEquals("Test5", transacciones.get(5).motivo);
	}
}
