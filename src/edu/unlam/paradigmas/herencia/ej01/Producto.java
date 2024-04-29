package edu.unlam.paradigmas.herencia.ej01;

public abstract class Producto {
	protected void validarImportePositivo(double importe) {
		if (importe < 0) {
			throw new IllegalArgumentException("El importe no puede ser menor a cero.");
		}
	}
}
