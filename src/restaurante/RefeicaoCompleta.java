package restaurante;

import java.util.ArrayList;
import java.util.List;

import excecoes.Excecoes;

/**
 * Classe para objetos do tipo RefeicaoCompleta que estende Alimentacao.
 *
 */
public class RefeicaoCompleta extends Alimentacao {

	private List<String> listaPrato;
	private Excecoes excecoes;
	private double totalPrecoPratos;

	/**
	 * Construtor de RefeicaoCompleta.
	 * 
	 * @param String - nome
	 * @param String - descricao
	 * @throws StringException
	 */
	public RefeicaoCompleta(String nome, String descricao) throws Exception {
		super(nome, descricao);

		excecoes = new Excecoes();
		excecoes.StringException(nome);
		excecoes.StringException(descricao);
		this.totalPrecoPratos = 0;
		this.listaPrato = new ArrayList<>();

	}

	/**
	 * Retorna a lista de pratos da refeicao.
	 * 
	 * @return List<String> listaPrato
	 */
	public List<String> getListaPrato() {
		return listaPrato;
	}

	/**
	 * Troca a lista de pratos da refeicao.
	 * 
	 * @param ArrayList<String> listaPrato
	 * @throws verificaTamanhoArray
	 */
	public void setListaPrato(ArrayList<String> listaPrato) throws Exception {
		excecoes.verificaTamanhoArray(listaPrato);
		this.listaPrato = listaPrato;
	}

	/**
	 * Adiciona um prato a lista de pratos de uma refeicao.
	 * 
	 * @param String prato
	 */
	public void adicionaPrato(String prato) {
		listaPrato.add(prato);
	}

	@Override
	public String informacaoDescricao() {
		String info = "";
		info = this.getDescricao() + " Serao servidos: ";
		for (int i = 0; i < listaPrato.size(); i++)
			if (!(i == listaPrato.size() - 1)) {
				info += "(" + (i + 1) + ") " + listaPrato.get(i) + ", ";
			} else {
				info += "(" + (i + 1) + ") " + listaPrato.get(i) + ".";
			}

		return info;
	}
	
	/**
	 * Modifca o preco da refeicao.
	 * 
	 * @param Double - preco
	 */
	public void setPreco(double preco) {
		this.totalPrecoPratos = preco;
	}
	
	@Override
	public double getPreco() {
		return totalPrecoPratos * 0.9;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDescricao() == null) ? 0 : getDescricao().hashCode());
		result = prime * result + ((listaPrato == null) ? 0 : listaPrato.hashCode());
		result = prime * result + ((getNome() == null) ? 0 : getNome().hashCode());
		return result;
	}

	@Override
	public String toString() {
		
		String listaConcatena = "";
		String objprato = null;
		for (String prato : listaPrato) {
			objprato = prato;
			listaConcatena += getNome() + "/n";
		}
		
		return "Refeicao Completa: " + this.getNome() + ",- Descricao: " + this.getDescricao() + "Lista de Pratos: \n"
		+ listaConcatena;
	}
	
	@Override
	public boolean equals(Object refeicao) {
		if (refeicao instanceof RefeicaoCompleta) {
			RefeicaoCompleta novaRefeicao = (RefeicaoCompleta) refeicao;
			if (novaRefeicao.getNome().equalsIgnoreCase(getNome())) {
				return true;
			}
		}
		return false;
	}
	
}
