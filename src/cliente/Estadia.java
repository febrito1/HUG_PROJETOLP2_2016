package cliente;

import quarto.Quarto;

public class Estadia {

	private int quantidadeDias;
	private Quarto quarto;
	private Hospede hospede;

	public Estadia(Quarto quarto, int qtdDias) {
		this.quarto = quarto;
		this.quantidadeDias = qtdDias;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public String getID() {
		return quarto.getID();
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public Hospede getHospede() {
		return hospede;
	}

}
