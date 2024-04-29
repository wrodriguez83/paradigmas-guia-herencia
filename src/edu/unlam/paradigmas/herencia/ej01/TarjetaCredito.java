package edu.unlam.paradigmas.herencia.ej01;

import java.util.ArrayList;
import java.util.List;

public class TarjetaCredito extends Tarjeta {
	private List<Transaccion> transacciones = new ArrayList<>();
	private double saldo;

	public TarjetaCredito(Cuenta cuenta) {
		super(cuenta);
	}

	@Override
	public void comprar(double importe) {
		super.comprar(importe);
		this.transacciones.add(new Transaccion(-importe, "Compra tarjeta de crédito."));
		this.saldo += importe;
	}

	public void debitar() {
		double comision = 0.03;
		this.cuenta.validarImporte(this.saldo * (1 + comision));
		this.cuenta.agregarTransacciones(this.transacciones);
		this.cuenta.extraer(this.saldo * comision, "Comisión tarjeta de crédito.");
		this.saldo = 0;
		this.transacciones.clear();
	}

}
