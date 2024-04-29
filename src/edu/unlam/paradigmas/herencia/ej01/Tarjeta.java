package edu.unlam.paradigmas.herencia.ej01;

public abstract class Tarjeta extends ProductoConCuenta {
	public Tarjeta(Cuenta cuenta) {
		super(cuenta);
	}

	public void comprar(double importe) {
		this.validarImportePositivo(importe);
	}
}
