
package restaurante;

import excecoes.Excecoes;

public class PratosFactory {

	public PratosFactory() {
	}

	public Prato criaPrato(String nomePrato, double precoPrato, String descricaoPrato) throws Exception {
		return new Prato(nomePrato, precoPrato, descricaoPrato);
	}

}
