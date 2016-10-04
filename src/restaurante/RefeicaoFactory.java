package restaurante;

/**
 * Classe que cria uma nova refeicao
 * 
 */
public class RefeicaoFactory {
	
	/**
	 * Cria uma nova refeicao.
	 * 
	 * @param String nome
	 * @param String descricao
	 * @return RefeicaoCompleta - nova refeicao completa
	 * @throws Exception
	 */
	public RefeicaoCompleta criaRefeicao(String nome, String descricao) throws Exception{
		return new RefeicaoCompleta(nome, descricao);
	}
}
