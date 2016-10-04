package restaurante;

import java.util.HashSet;
import excecoes.Excecoes;

/**
 * Classe para objetos do tipo Prato que estende Alimentacao
 * 
 */
public class Prato extends Alimentacao{

	private double precoPrato;
	private HashSet<Prato> pratos;
	private Excecoes excecoes = new Excecoes();
	
	/**
	 * Construtor de Prato.
	 * 
	 * @param String - nomePrato
	 * @param Double - precoPrato
	 * @param String - descricaoPrato
	 * @throws StringException, doubleException
	 */
	public Prato(String nomePrato, double precoPrato, String descricaoPrato) throws Exception {
		super(nomePrato, descricaoPrato);
		
		excecoes.StringException(nomePrato);
		excecoes.doubleException(precoPrato);
		excecoes.StringException(descricaoPrato);
			
		this.precoPrato = precoPrato;
		this.pratos = new HashSet<>();
		
	}
	
	/**
	 * Acessa e modifica o preco do Prato.
	 * 
	 * @param Double - precoPrato
	 */
	public void setPreco(double precoPrato) {
		this.precoPrato = precoPrato;
	}
	
	/**
	 * Retorna a lista de Pratos.
	 * 
	 * @return HashSet<Prato> - pratos
	 */
	public HashSet<Prato> getPratos() {
		return pratos;
	}

	/**
	 * Adiciona um prato na lista de pratos.
	 * 
	 * @param HashSet<Prato> - pratos
	 */
	public void setPratos(HashSet<Prato> pratos) {
		this.pratos = pratos;
	}
	
	@Override
	public double getPreco() {
		return precoPrato;
	}

	@Override
	public String informacaoDescricao() {
		return getDescricao();
	}

	@Override
	public String toString() {
		return "Prato: " + super.getNome() + ", custa: " + precoPrato +
				"Descricao: " + super.getDescricao();
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((super.getDescricao() == null) ? 0 : super.getDescricao().hashCode());
		result = prime * result + ((super.getNome() == null) ? 0 : super.getNome().hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoPrato);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Prato)){
			return false;
		}
		Prato outro = (Prato) obj;
		
		if(outro.getNome().equalsIgnoreCase(getNome()) 
				&& outro.getPreco() == getPreco() 
				&& outro.getDescricao().equalsIgnoreCase(getDescricao())){
			return true;
		}return false;
	}
	
}
