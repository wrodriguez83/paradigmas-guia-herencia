package edu.unlam.paradigmas.herencia.ej01;

public class PlazoFijo extends ProductoConCuenta {
	private double importe;

	public PlazoFijo(double importe, Cuenta cuenta) {
		super(cuenta);
		this.validarImportePositivo(importe);
		this.importe = importe;
		this.cuenta.extraer(importe, "Creación plazo fijo.");
	}

	public void acreditar() {
		double tasa = 1 + (0.36 / 12);
		this.cuenta.depositar(this.importe * tasa, "Acreditación plazo fijo.");
		this.importe = 0;
	}
}
