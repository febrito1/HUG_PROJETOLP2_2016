package hotel;

/**
 * Intercafe para garantir a presenca dos metodos aqui assinados.
 * 
 */
public interface ControleDeGastos {

	/**
	 * Retorna a data do checkout ou transacao.
	 * 
	 * @return String - data
	 */
	public String getData();
	
	/**
	 * Retorna o nome do cliente.
	 * 
	 * @return String - nome
	 */
	public String getNomeCliente();
	
	/**
	 * Retorna um item do menu ou uma transacao.
	 * 
	 * @return String - itemMenu/transacao
	 */
	public String getTransacao();
	
	/**
	 * Retorna o total gasto no checkout ou em transacoes do restaurante.
	 * 
	 * @return Double - despesa
	 */
	public double getTotalGasto();
}
