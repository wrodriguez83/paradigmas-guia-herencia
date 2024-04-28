package edu.unlam.paradigmas.herencia.ej01;

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
		this.validarImportePositivo(importe);
		this.saldo += importe;
	}

	public void extraer(double importe) {
		this.validarImporteAExtraer(importe);

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

	protected void validarImportePositivo(double importe) {
		if (importe < 0) {
			throw new IllegalArgumentException("El importe no puede ser menor a cero.");
		}
	}

	protected void validarImporteAExtraer(double importe) {
		this.validarImportePositivo(importe);

		if (this.consultarSaldo() < importe) {
			throw new IllegalArgumentException("El importe excede el saldo actual.");
		}
	}
}
