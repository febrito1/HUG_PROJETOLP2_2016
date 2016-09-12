
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
	
	private Prato criaPratoEntrada(String nomePrato, double precoPrato, String descricaoPrato) throws Exception{
		Prato entrada = new Entrada(nomePrato, precoPrato, descricaoPrato);
		return entrada;
	}
	
	private Prato criaPratoPrincipal(String nomePrato, double precoPrato, String descricaoPrato) throws Exception{
		Prato PratoPrincipal = new PratoPrincipal(nomePrato, precoPrato, descricaoPrato);
		return PratoPrincipal;
	}
	
	private Prato criaPratoSobremesa(String nomePrato, double precoPrato, String descricaoPrato) throws Exception{
		Prato sobremesa = new Sobremesa(nomePrato, precoPrato, descricaoPrato);
		return sobremesa;
	}
	
	private Prato criaPratoPetit(String nomePrato, double precoPrato, String descricaoPrato) throws Exception{
		Prato petit = new PetitFour(nomePrato, precoPrato, descricaoPrato);
		return petit;
	}

}
