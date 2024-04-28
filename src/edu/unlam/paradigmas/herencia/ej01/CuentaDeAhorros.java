package edu.unlam.paradigmas.herencia.ej01;

public class CuentaDeAhorros extends Cuenta {
	private double saldoSecundario;

	public CuentaDeAhorros(double saldoInicial) {
		super(saldoInicial);
	}

	public void reservarSaldo(double importe) {
		this.validarImporteAExtraer(importe);
		this.extraer(importe);
		this.saldoSecundario += importe;
	}

	public void consolidarSaldos() {
		this.depositar(this.saldoSecundario);
		this.saldoSecundario = 0;
	}
}
