package edu.unlam.paradigmas.herencia.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.opentest4j.IncompleteExecutionException;

class TransferenciasTests {
	Cuenta cuenta = new Cuenta(1000);
	Cuenta cuenta2 = new Cuenta(0);
	CuentaCorriente cuentaCorriente = new CuentaCorriente(1000, 500);
	CuentaCorriente cuentaCorriente2 = new CuentaCorriente(0, 0);
	CuentaDeAhorros cuentaAhorro = new CuentaDeAhorros(1000);
	CuentaDeAhorros cuentaAhorro2 = new CuentaDeAhorros(0);
	
	@Test
	void DebePoderTransferirEntreCuentasBase() {
		cuenta.transferir(500, cuenta2);
		assertEquals(500, cuenta.consultarSaldo());
		assertEquals(500, cuenta2.consultarSaldo());

		cuenta.depositar(3150);
		cuenta.transferir(150, cuenta2);

		assertEquals(3500, cuenta.consultarSaldo());
		assertEquals(650, cuenta2.consultarSaldo());
	}

	@Test
	void NoDebeTransferirMasDelSaldoPermitidoBase() {
		IncompleteExecutionException exception = assertThrows(IncompleteExecutionException.class, () -> {
			cuenta.transferir(1500, cuenta2);
		});
		assertEquals("No se pudo realizar la transferencia.", exception.getMessage());
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(0, cuenta2.consultarSaldo());
	}

	@Test
	void DebePoderTransferirEntreCuentasCorrientes() {
		cuentaCorriente.transferir(500, cuentaCorriente2);
		assertEquals(1000, cuentaCorriente.consultarSaldo());
		assertEquals(500, cuentaCorriente2.consultarSaldo());

		cuentaCorriente.depositar(3150);
		cuentaCorriente.transferir(150, cuentaCorriente2);

		assertEquals(4000, cuentaCorriente.consultarSaldo());
		assertEquals(650, cuentaCorriente2.consultarSaldo());
	}

	@Test
	void NoDebeTransferirMasDelSaldoPermitidoCuentaCorriente() {
		IncompleteExecutionException exception = assertThrows(IncompleteExecutionException.class, () -> {
			cuentaCorriente.transferir(2000, cuentaCorriente2);
		});
		assertEquals("No se pudo realizar la transferencia.", exception.getMessage());
		assertEquals(1500, cuentaCorriente.consultarSaldo());
		assertEquals(0, cuentaCorriente2.consultarSaldo());
	}
	
	@Test
	void DebePoderTransferirEntreCuentasDeAhorro() {
		cuentaAhorro.transferir(500, cuentaAhorro2);
		assertEquals(500, cuentaAhorro.consultarSaldo());
		assertEquals(500, cuentaAhorro2.consultarSaldo());

		cuentaAhorro.depositar(3150);
		cuentaAhorro.transferir(150, cuentaAhorro2);

		assertEquals(3500, cuentaAhorro.consultarSaldo());
		assertEquals(650, cuentaAhorro2.consultarSaldo());
	}

	@Test
	void NoDebeTransferirMasDelSaldoPermitidoCuentaDeAhorro() {
		IncompleteExecutionException exception = assertThrows(IncompleteExecutionException.class, () -> {
			cuentaAhorro.transferir(2000, cuentaAhorro2);
		});
		assertEquals("No se pudo realizar la transferencia.", exception.getMessage());
		assertEquals(1000, cuentaAhorro.consultarSaldo());
		assertEquals(0, cuentaAhorro2.consultarSaldo());
	}
	
	@Test
	void DebePoderTransferirEntreCuentas() {
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(1000, cuentaAhorro.consultarSaldo());
		assertEquals(1500, cuentaCorriente.consultarSaldo());
		
		cuentaAhorro.transferir(500, cuenta);
		assertEquals(500, cuentaAhorro.consultarSaldo());
		assertEquals(1500, cuenta.consultarSaldo());

		cuenta.transferir(500, cuentaCorriente);
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(2000, cuentaCorriente.consultarSaldo());
		
		cuentaCorriente.transferir(500, cuentaAhorro);
		assertEquals(1500, cuentaCorriente.consultarSaldo());
		assertEquals(1000, cuentaAhorro.consultarSaldo());
		
		cuentaAhorro.transferir(500, cuentaCorriente);
		assertEquals(500, cuentaAhorro.consultarSaldo());
		assertEquals(2000, cuentaCorriente.consultarSaldo());
		
		cuentaCorriente.transferir(500, cuenta);
		assertEquals(1500, cuentaCorriente.consultarSaldo());
		assertEquals(1500, cuenta.consultarSaldo());
		
		cuenta.transferir(500, cuentaAhorro);
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(1000, cuentaAhorro.consultarSaldo());
		
		assertEquals(1000, cuenta.consultarSaldo());
		assertEquals(1000, cuentaAhorro.consultarSaldo());
		assertEquals(1500, cuentaCorriente.consultarSaldo());
	}
}
