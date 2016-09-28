
package restaurante;

import excecoes.Excecoes;

public class PratosFactory {

	public PratosFactory() {
	}

	public Alimentacao criaPrato(String nomePrato, double precoPrato, String descricaoPrato) throws Exception {
		Alimentacao novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato);
		return novoPrato;
	}

}
