package restaurante;


import java.util.HashSet;
import excecoes.*;

public class RefeicaoCompleta {
	
	private String nomeRefeicao, descricaoRefeicao;
	private HashSet<Prato> listaPrato;
	
	public RefeicaoCompleta(String nomeRefeicao, String descricaoRefeicao, HashSet<Prato> listaPrato) throws Exception {
		
		Excecoes.StringException(nomeRefeicao);
		Excecoes.StringException(descricaoRefeicao);
		Excecoes.verificaTamanhoSet(listaPrato);
		
		this.nomeRefeicao = nomeRefeicao;
		this.descricaoRefeicao = descricaoRefeicao;
		this.listaPrato = listaPrato;
		
	}

	public String getNomeRefeicao() {
		return nomeRefeicao;
	}

	public String getDescricaoRefeicao() {
		return descricaoRefeicao;
	}

	public HashSet<Prato> getListaPrato() {
		return listaPrato;
	}

	public void setNomeRefeicao(String nomeRefeicao) throws Exception {
		Excecoes.StringException(nomeRefeicao);
		this.nomeRefeicao = nomeRefeicao;
	}

	public void setDescricaoRefeicao(String descricaoRefeicao) throws Exception {
		Excecoes.StringException(descricaoRefeicao);
		this.descricaoRefeicao = descricaoRefeicao;
	}

	public void setListaPrato(HashSet<Prato> listaPrato) throws Exception {
		Excecoes.verificaTamanhoSet(listaPrato);
		this.listaPrato = listaPrato;
	}

	@Override
	public String toString() {
		
		String listaConcatena = "";
		Prato objprato = null;
		for (Prato prato : listaPrato) {
			objprato = prato;
			listaConcatena += objprato.getNomePrato() + "/n";
		}
		
		return "Refeicao Completa: " + nomeRefeicao + ",- Descricao: " + descricaoRefeicao
				+ "Lista de Pratos: \n"
				+ listaConcatena;
	}

	
}
