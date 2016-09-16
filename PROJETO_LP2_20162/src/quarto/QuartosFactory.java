package quarto;

public class QuartosFactory {

	public QuartosFactory(){}
		
	public Quarto criaQuarto(String id, String tipoQuarto) throws Exception {
		if(tipoQuarto.equalsIgnoreCase("luxo")){
			return criaQuartoLuxo(id);
		}
		else if(tipoQuarto.equalsIgnoreCase("presidencial")){
			return criaQuartoPresidencial(id);
		}
		else if(tipoQuarto.equalsIgnoreCase("simples")){
			return criaQuartoSimples(id);
		}
		
		return null;
	}
	
	
	
	private Quarto criaQuartoLuxo(String id) throws Exception{
		Quarto luxo = new QuartoLuxo(id);
		return luxo;
	}
	
	
	private Quarto criaQuartoPresidencial(String id) throws Exception{
		Quarto presidencial = new QuartoPresidencial(id);
		return presidencial;
	}
	
	private Quarto criaQuartoSimples(String id) throws Exception{
		Quarto simples = new QuartoSimples(id);
		return simples;
	}
		
}
