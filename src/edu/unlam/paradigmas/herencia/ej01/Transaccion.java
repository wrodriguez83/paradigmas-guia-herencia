package edu.unlam.paradigmas.herencia.ej01;

public class Transaccion {
	protected final double importe;
	protected final String motivo;

	public Transaccion(double importe, String motivo) {
		this.importe = importe;
		this.motivo = motivo;
	}
}
