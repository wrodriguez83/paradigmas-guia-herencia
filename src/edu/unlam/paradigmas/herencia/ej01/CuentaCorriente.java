package edu.unlam.paradigmas.herencia.ej01;

public class CuentaCorriente extends Cuenta {
	private double descubierto;

	public CuentaCorriente(double saldoInicial, double descubierto) {
		super(saldoInicial);

		if (descubierto < 0) {
			throw new IllegalArgumentException("El descubierto no puede ser menor a cero.");
		}

		this.descubierto = descubierto;
	}

	public double consultarSaldo() {
		return super.consultarSaldo() + this.descubierto;
	}
}
