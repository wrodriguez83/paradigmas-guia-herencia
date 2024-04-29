package edu.unlam.paradigmas.herencia.ej01;

import java.util.Date;

public class Transaccion {
	protected final double importe;
	protected final String motivo;
	private Date fecha;

	public Transaccion(double importe, String motivo) {
		this.importe = importe;
		this.motivo = motivo;
		this.fecha = new Date();
	}

	public Date getFecha() {
		return this.fecha;
	}
}
