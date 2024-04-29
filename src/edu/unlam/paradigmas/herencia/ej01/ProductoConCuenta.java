package edu.unlam.paradigmas.herencia.ej01;

public abstract class ProductoConCuenta extends Producto {
	protected Cuenta cuenta;

	public ProductoConCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
}
