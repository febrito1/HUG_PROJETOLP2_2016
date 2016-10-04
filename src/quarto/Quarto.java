package quarto;

/**
 * 
 * Classe de objetos do tipo Quarto
 */
public class Quarto {

	private String ID;
	private TipoQuarto tipo;
	
	/**
	 * Construtor de Quarto
	 * 
	 * @param String - ID
	 * @param TipoQuarto - tipo
	 */
	public Quarto(String ID, TipoQuarto tipo) {
		this.ID = ID;
		this.tipo = tipo;
	}
	
	/**
	 * Retorna o ID do Quarto.
	 * 
	 * @return String - ID
	 */
	public String getID() {
		return this.ID;
	}
	
	/**
	 * Retorna o preco do Quarto.
	 * 
	 * @return Double - preco
	 */
	public double getPreco() {
		return tipo.getPreco();
	}

	/**
	 * Retorna o tipo do Quarto.
	 * 
	 * @return String - tipo do quarto
	 */
	public String getTipo() {
		return tipo.name().toLowerCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	
	/**
	 * Equals que compara quartos por seu ID
	 */
	@Override
	public boolean equals(Object outroQuarto) {
		if (outroQuarto instanceof Quarto) {
			Quarto novo = (Quarto) outroQuarto;
			if (novo.getID().equalsIgnoreCase(ID)) {
				return true;
			}
		}
		return false;
	}

}
