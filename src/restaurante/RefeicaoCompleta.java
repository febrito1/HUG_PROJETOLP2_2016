package restaurante;

import java.util.ArrayList;
import java.util.List;

import excecoes.Excecoes;

public class RefeicaoCompleta extends Alimentacao {

	private List<String> listaPrato;
	private Excecoes excecoes;
	private double totalPrecoPratos;

	public RefeicaoCompleta(String nome, String descricao) throws Exception {
		super(nome, descricao);

		excecoes = new Excecoes();
		excecoes.StringException(nome);
		excecoes.StringException(descricao);
		this.totalPrecoPratos = 0;
		this.listaPrato = new ArrayList<>();

	}


	public List<String> getListaPrato() {
		return listaPrato;
	}

	public void setListaPrato(ArrayList<String> listaPrato) throws Exception {
		excecoes.verificaTamanhoArray(listaPrato);
		this.listaPrato = listaPrato;
	}

	public void adicionaPrato(String prato) {
		listaPrato.add(prato);
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
	public double getPreco() {
		return totalPrecoPratos * 0.9;
	}
	
	public void setPreco(double preco) {
		this.totalPrecoPratos = preco;
	}

}
