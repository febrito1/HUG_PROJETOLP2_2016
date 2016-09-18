package restaurante;

public class PratosFactory{
	
	public Prato criaPrato(String nomePrato, double precoPrato, String descricaoPrato, String tipoPrato) throws Exception{
		
		Prato novoPrato = null;
		
		switch(tipoPrato.toLowerCase()){
		case "entrada":
			novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato, TipoPrato.ENTRADA);
			break;
		case "principal":
			novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato, TipoPrato.PRINCIPAL);
			break;
		case "sobremesa":
			novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato, TipoPrato.SOBREMESA);
			break;
		case "petit":
			novoPrato = new Prato(nomePrato, precoPrato, descricaoPrato, TipoPrato.PETIT);
			break;
		}
		return novoPrato;
	}
}
