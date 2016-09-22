package restaurante;

import java.util.ArrayList;
import java.util.List;

import excecoes.excecoes;

public class RefeicaoCompleta {

	private String nomeRefeicao, descricaoRefeicao;
	private List<Prato> listaPrato;
	private excecoes excecoes;

	public RefeicaoCompleta(String nomeRefeicao, String descricaoRefeicao) throws Exception {

		
		excecoes = new excecoes();
		excecoes.StringException(nomeRefeicao);
		excecoes.StringException(descricaoRefeicao);

		this.nomeRefeicao = nomeRefeicao;
		this.descricaoRefeicao = descricaoRefeicao;
		
		this.listaPrato = new ArrayList<>();

	}

	public double calculaConta() {
		double desconto = 0.1;
		double contaAPagar = 0;

		for (Prato prato : listaPrato) {
			contaAPagar += prato.getPrecoPrato();
		}
		return contaAPagar = contaAPagar * desconto;
	}

	public String getNomeRefeicao() {
		return nomeRefeicao;
	}

	public String getDescricaoRefeicao() {
		return descricaoRefeicao;
	}

	public List<Prato> getListaPrato() {
		return listaPrato;
	}

	public void setNomeRefeicao(String nomeRefeicao) throws Exception {
		excecoes.StringException(nomeRefeicao);
		this.nomeRefeicao = nomeRefeicao;
	}

	public void setDescricaoRefeicao(String descricaoRefeicao) throws Exception {
		excecoes.StringException(descricaoRefeicao);
		this.descricaoRefeicao = descricaoRefeicao;
	}

	public void setListaPrato(ArrayList<Prato> listaPrato) throws Exception {
		excecoes.verificaTamanhoArray(listaPrato);
		this.listaPrato = listaPrato;
	}

	public void adicionaPrato(Prato prato) {
		listaPrato.add(prato);
	}

	public double calculaPrecoTotal() {
		double total = 0;
		for (Prato prato : listaPrato) {
			total += prato.getPrecoPrato();
		}
		return total * 0.9 ;
	}
	public String getNomePratos(){
		for (Prato prato : listaPrato) {
			return prato.getNomePrato();
		}return null;
	}
	@Override
	public String toString() {

		String listaConcatena = "";
		Prato objprato = null;
		for (Prato prato : listaPrato) {
			objprato = prato;
			listaConcatena += objprato.getNomePrato() + "/n";
		}

		return "Refeicao Completa: " + nomeRefeicao + ",- Descricao: " + descricaoRefeicao + "Lista de Pratos: \n"
				+ listaConcatena;
	}

	@Override
	public boolean equals(Object refeicao) {
		if (refeicao instanceof RefeicaoCompleta) {
			RefeicaoCompleta novaRefeicao = (RefeicaoCompleta) refeicao;
			if (novaRefeicao.getNomeRefeicao().equalsIgnoreCase(nomeRefeicao)) {
				return true;
			}
		}
		return false;
	}
	public String informacaoRefeicao() {
		String info = "";
		info = this.descricaoRefeicao.substring(0, this.descricaoRefeicao.length() - 1);
		info += "." + " Serao servidos: ";
		for (int i = 0; i < listaPrato.size(); i++)
			if (!(i == listaPrato.size() - 1)) {
				info += "(" + (i + 1) + ") " + listaPrato.get(i).getNomePrato() + ", ";
			} else {
				info += "(" + (i + 1) + ") " + listaPrato.get(i).getNomePrato() + ".";
			}

		return info;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoRefeicao == null) ? 0 : descricaoRefeicao.hashCode());
		result = prime * result + ((listaPrato == null) ? 0 : listaPrato.hashCode());
		result = prime * result + ((nomeRefeicao == null) ? 0 : nomeRefeicao.hashCode());
		return result;
	}

}
