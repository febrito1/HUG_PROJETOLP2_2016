package restaurante;

/**
 * Super classe para o tipo alimento que fornece metodos e valores para o mesmo.
 * 
 */
public abstract class Alimentacao implements Comparable<Alimentacao> {
	private String nome, descricao;

	/**
	 * Construtor de Alimentacao
	 * 
	 * @param String - nome
	 * @param String - descricao
	 */
	public Alimentacao(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	/**
	 * Retorna iformacoes a respeito de uma alimentacao que pode ser do tipo Prato ou RefeicaoCompleta.
	 * 
	 * @return String - descricao
	 */
	public abstract String informacaoDescricao();
	
	/**
	 * Retorna o valor do Prato ou RefeicaoCompleta.
	 * 
	 * @return Double - preco
	 */
	public abstract double getPreco();
	
	/**
	 * Retorna o nome da alimentacao.
	 * @return String - nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Acessa e modifica o nome de um alimento.
	 * 
	 * @param String -nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna a descricao de um alimento.
	 * 
	 * @return String - descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Acessa e modifica a descricao de um alimento.
	 * 
	 * @param String - descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int compareTo(Alimentacao obj){
		return this.nome.compareTo(obj.getNome()); 
	}
	
}