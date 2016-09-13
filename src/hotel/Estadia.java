package hotel;

import quarto.Quarto;

public class Estadia {
	private int quantidadeDias;
	private double total = 0;
	private Quarto quarto;

	public Estadia() {
	}

	public double calculaGastoTotal() {
		return total += quantidadeDias * quarto.getValorDiaria();
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
