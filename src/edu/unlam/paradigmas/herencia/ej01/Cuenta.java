package edu.unlam.paradigmas.herencia.ej01;

import java.util.concurrent.ExecutionException;

import org.opentest4j.IncompleteExecutionException;

public class Cuenta {
	private double saldo = 0;

	public Cuenta(double saldoInicial) {
		if (saldoInicial < 0) {
			throw new IllegalArgumentException("El saldo inicial no puede ser menor a cero.");
		}
		this.saldo = saldoInicial;
	}

	public double consultarSaldo() {
		return this.saldo;
	}

	public void depositar(double importe) {
		if (importe < 0) {
			throw new IllegalArgumentException("El importe a depositar no puede ser menor a cero.");
		}
		this.saldo += importe;
	}

	public void extraer(double importe) {
		if (importe < 0) {
			throw new IllegalArgumentException("El importe a extraer no puede ser menor a cero.");
		}

		if (this.saldo < importe) {
			throw new IllegalArgumentException("El importe a extraer excede el saldo actual.");
		}

		this.saldo -= importe;
	}

	public void transferir(double importe, Cuenta destino) {
		double saldoActual = this.saldo;
		try {
			this.extraer(importe);
			destino.depositar(importe);
		} catch (Exception e) {
			this.saldo = saldoActual;
			throw new IncompleteExecutionException("No se pudo realizar la transferencia.");
		}
	}
}
