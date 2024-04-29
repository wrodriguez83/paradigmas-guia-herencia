package edu.unlam.paradigmas.herencia.ej01;

import java.util.ArrayList;
import java.util.List;

import org.opentest4j.IncompleteExecutionException;

public class Cuenta {
	private List<Transaccion> transacciones = new ArrayList<>();
	private double saldo = 0;

	public Cuenta(double saldoInicial) {
		if (saldoInicial < 0) {
			throw new IllegalArgumentException("El saldo inicial no puede ser menor a cero.");
		}
		this.agregarTransaccion(saldoInicial, "Saldo inicial");
	}

	public double consultarSaldo() {
		return this.saldo;
	}

	public void depositar(double importe, String motivo) {
		this.validarImportePositivo(importe);
		this.agregarTransaccion(importe, motivo);
	}

	public void extraer(double importe, String motivo) {
		this.validarImporteAExtraer(importe);
		this.agregarTransaccion(-importe, motivo);
	}

	public void transferir(double importe, Cuenta destino) {
		double saldoActual = this.saldo;
		try {
			this.extraer(importe, "Transferencia entre cuentas");
			destino.depositar(importe, "Transferencia entre cuentas");
		} catch (Exception e) {
			this.saldo = saldoActual;
			throw new IncompleteExecutionException("No se pudo realizar la transferencia.");
		}
	}

	public List<Transaccion> getTransacciones() {
		return this.transacciones;
	}

	private void agregarTransaccion(double importe, String motivo) {
		this.transacciones.add(new Transaccion(importe, motivo));
		this.saldo += importe;
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
