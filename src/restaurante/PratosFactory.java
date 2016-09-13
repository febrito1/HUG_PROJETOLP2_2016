
package restaurante;


import excecoes.Excecoes;

public class PratosFactory {

	public PratosFactory(){}
	
	public Prato criaPrato(String nomePrato, double precoPrato, String descricaoPrato, String tipoPrato) throws Exception{
		
		Excecoes.StringException(nomePrato);
		Excecoes.doubleException(precoPrato);
		Excecoes.StringException(descricaoPrato);
		
		Prato novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato);
		return novoPrato;
	}
	
	

}
