package restaurante;

public abstract class Alimentacao implements Comparable<Alimentacao> {
	private String nome, descricao;

	public Alimentacao(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}
	public abstract String informacaoDescricao();

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public abstract double getPreco();
	
	public int compareTo(Alimentacao obj){
		return this.nome.compareTo(obj.getNome()); 
	}
	
}