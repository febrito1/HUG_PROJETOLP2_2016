package restaurante;

import cliente.Hospede;

public class RefeicaoFactory {
	
	public RefeicaoCompleta criaRefeicao(String nome, String descricao) throws Exception{
		return new RefeicaoCompleta(nome, descricao);
	}
}
