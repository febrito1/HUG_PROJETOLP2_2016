package hotel;

import java.util.ArrayList;

import quarto.Quarto;
import restaurante.Restaurante;

public class Estadia {
	
	private int quantidadeDias;
	private double total = 0;
	private Quarto quarto;
	private Hospede hospede; 
	
	private ArrayList<Restaurante> gastosRestaurante;
	
	public Estadia(Hospede hospede, Quarto quarto, int qtdDias){	
			this.hospede = hospede;
			this.quarto = quarto;
			this.quantidadeDias = qtdDias;
			gastosRestaurante = new ArrayList<Restaurante>();	
	}
	

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	
	public double getTotal() {
		return total;
	}


	public Quarto getQuarto() {
		return quarto;
	}
	

	public Hospede getHospede() {
		return hospede;
	}

	
}
