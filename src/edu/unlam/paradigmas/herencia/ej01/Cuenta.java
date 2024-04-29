package edu.unlam.paradigmas.herencia.ej01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.opentest4j.IncompleteExecutionException;

public class Cuenta extends Producto {
	private List<Transaccion> transacciones = new ArrayList<>();
	private double saldo = 0;

	public Cuenta(double saldoInicial) {
		super();
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
		this.validarImporte(importe);
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
		this.transacciones.sort(Comparator.comparing(Transaccion::getFecha));
		return this.transacciones;
	}

	public void agregarTransacciones(List<Transaccion> transaccionesNuevas) {
		double importe = transaccionesNuevas.stream().mapToDouble(t -> t.importe).sum();
		this.saldo += importe;
		this.transacciones.addAll(transaccionesNuevas);
	}

	private void agregarTransaccion(double importe, String motivo) {
		this.transacciones.add(new Transaccion(importe, motivo));
		this.saldo += importe;
	}

	protected void validarImporte(double importe) {
		this.validarImportePositivo(importe);

		if (this.consultarSaldo() < importe) {
			throw new IllegalArgumentException("El importe excede el saldo actual.");
		}
	}
}
