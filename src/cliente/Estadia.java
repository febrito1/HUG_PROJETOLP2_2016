package cliente;

import quarto.Quarto;
/**
 * Classe de estadia que possui informacao de quantidade de dias, quarto e hospede.
 *
 */
public class Estadia {

	private int quantidadeDias;
	private Quarto quarto;
	private Hospede hospede;
	
	/**
	 * Construtor de estadia que recebe um quarto e a quantidade de dias.
	 * 
	 * @param quarto
	 * @param qtdDias
	 */
	public Estadia(Quarto quarto, int qtdDias) {
		this.quarto = quarto;
		this.quantidadeDias = qtdDias;
	}
	
	/**
	 * Metodo que retorna a quantidade de dias da estadia.
	 * 
	 * @return int - quantidade de dias
	 */
	public int getQuantidadeDias() {
		return quantidadeDias;
	}
	
	/**
	 * Metodo que retorna o ID do quarto.
	 * 
	 * @return String - ID
	 */
	public String getID() {
		return quarto.getID();
	}
	
	/**
	 * Metodo que retorna o quarto da estadia.
	 * 
	 * @return Quarto - quarto
	 */
	public Quarto getQuarto() {
		return quarto;
	}
	
	/**
	 * Metodo que retorna o hospede da estadia.
	 * 
	 * @return Hospede - hospede
	 */
	public Hospede getHospede() {
		return hospede;
	}

}
