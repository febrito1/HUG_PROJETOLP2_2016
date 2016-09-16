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
	
	public Estadia(Hospede hospede, Quarto quarto, int qtdDias, String email){
		if(hospede.getEmailHospede().equalsIgnoreCase(email)){
			this.hospede = hospede;
			this.quarto = quarto;
			this.quantidadeDias = qtdDias;
			gastosRestaurante = new ArrayList<Restaurante>();
		}
		
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


	public Quarto getQuarto() {
		return quarto;
	}


	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	

	public Hospede getHospede() {
		return hospede;
	}


	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	
}
