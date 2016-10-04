package quarto;

/**
 * Iterface cujo unica assinatura eh o getpreco.
 * 
 */
public interface PrecoQuarto {
	
	/**
	 * Retorna o preco do quarto que varia de acordo com seu tipo
	 * que pode ser simples, luxo ou presidencial.
	 * 
	 * @return Double - preco
	 */
	public double getPreco();
	
}
