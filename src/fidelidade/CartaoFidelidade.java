package fidelidade;

/**
 * Intercafe para tipos de cartoes de fidelidade, que possui assinaturas de metodos para os mesmos.
 * 
 */
public interface CartaoFidelidade {
	
	/**
	 * Retorna os pontos do cartao.
	 * 
	 * @return int - pontos
	 */
	public int getPontos();
	
	/**
	 * Adiciona pontos ao cartao. 
	 * Os pontos sao calculados a partir do tipo do cartao e do preco.
	 * 
	 * @param Double - preco
	 */
	public void addPontos(double preco);
	
	/**
	 * Converte pontos em um desconto.
	 * 
	 * @param int - pontos
	 * @return String - desconto 
	 * @throws Exception
	 */
	public String convertePontos(int pontos) throws Exception;
	
	/**
	 * Calcula o desconto atraves do preco recebido como parametro.
	 * 
	 * @param Double - preco
	 * @return Double - desconto
	 */
	public double desconto(double preco);
}
