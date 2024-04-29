package edu.unlam.paradigmas.herencia.ej01;

public class TarjetaDebito extends Tarjeta {
	public TarjetaDebito(Cuenta cuenta) {
		super(cuenta);
	}

	@Override
	public void comprar(double importe) {
		super.comprar(importe);
		this.cuenta.extraer(importe, "Compra tarjeta débito.");
	}
}
