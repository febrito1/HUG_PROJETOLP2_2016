
package restaurante;

/**
 * Classe que cria um novo Prato.
 * 
 */
public class PratosFactory {
	
	/**
	 * Cria um novo Prato do super tipo Alimentacao
	 * 
	 * @param String - nomePrato
	 * @param Double - precoPrato
	 * @param String - descricaoPrato
	 * @return Alimetacao - novo prato
	 * @throws Exception
	 */
	public Alimentacao criaPrato(String nomePrato, double precoPrato, String descricaoPrato) throws Exception {
		Alimentacao novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato);
		return novoPrato;
	}

}
