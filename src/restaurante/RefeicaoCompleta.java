package restaurante;
<<<<<<< HEAD

import java.util.HashSet;
import excecoes.*;
=======
import java.util.ArrayList;
import java.util.List;

import excecoes.Excecoes;
>>>>>>> af06d4a59bb3a76755f82ab332ce7478aabbfd2d

public class RefeicaoCompleta {
	
	private String nomeRefeicao, descricaoRefeicao;
	private List<Prato> listaPrato;
	
	public RefeicaoCompleta(String nomeRefeicao, String descricaoRefeicao, ArrayList<Prato> listaPrato) throws Exception {
		
		Excecoes.StringException(nomeRefeicao);
		Excecoes.StringException(descricaoRefeicao);
		Excecoes.verificaTamanhoArray(listaPrato);
		
		this.nomeRefeicao = nomeRefeicao;
		this.descricaoRefeicao = descricaoRefeicao;
		this.listaPrato = listaPrato;
		
	}
	
	public double calculaConta(){
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
		Excecoes.StringException(nomeRefeicao);
		this.nomeRefeicao = nomeRefeicao;
	}

	public void setDescricaoRefeicao(String descricaoRefeicao) throws Exception {
		Excecoes.StringException(descricaoRefeicao);
		this.descricaoRefeicao = descricaoRefeicao;
	}

	public void setListaPrato(ArrayList<Prato> listaPrato) throws Exception {
		Excecoes.verificaTamanhoArray(listaPrato);
		this.listaPrato = listaPrato;
	}
	public void adicionaPrato(Prato prato){
		if(!(listaPrato.contains(prato))){
			listaPrato.add(prato);
		}
	}
	
	public void defineOrdemPrato(){
		for (int i = 1; i < listaPrato.size(); i++) {
			Prato entrada = listaPrato.get(1);
			Prato pratoPrincipal = listaPrato.get(2);
			Prato sobremesa = listaPrato.get(3);
			Prato petitFour = listaPrato.get(4);
		}
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
