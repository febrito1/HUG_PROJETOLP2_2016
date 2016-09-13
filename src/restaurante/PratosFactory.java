
package restaurante;


import excecoes.Excecoes;

public class PratosFactory {

	public Prato criaPrato(String nomePrato, double precoPrato, String descricaoPrato, String tipoPrato) throws Exception{
		
		Excecoes.StringException(nomePrato);
		Excecoes.doubleException(precoPrato);
		Excecoes.StringException(descricaoPrato);
		
		if(tipoPrato.equalsIgnoreCase("entrada")){
			return  criaPratoEntrada(nomePrato, precoPrato, descricaoPrato);
		}
		else if(tipoPrato.equalsIgnoreCase("principal")){
			return  criaPratoPrincipal(nomePrato, precoPrato, descricaoPrato);
		}
		else if(tipoPrato.equalsIgnoreCase("sobremesa")){
			return  criaPratoSobremesa(nomePrato, precoPrato, descricaoPrato);
		}
		else if(tipoPrato.equalsIgnoreCase("petit")){
			return criaPratoPetit(nomePrato, precoPrato, descricaoPrato);
		}
		return null;
	}
	
	

}
