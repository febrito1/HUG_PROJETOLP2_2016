package excecoes;

public class Excecoes {
	
	public Excecoes(){
		
	}
	
	public static void StringException(String excecao) throws Exception{
		if(excecao == null || excecao.trim().isEmpty()){
			throw new Exception("String não pode ser nula ou vazia");
		}
	}
	
	public static void inteiroException(int numInteiro) throws Exception{
		if(numInteiro < 0){
			throw new Exception("Numero não pode ser negativo.");
		}
	}

	
	public static void doubleException(double numDouble) throws Exception{
		if(numDouble < 0){
			throw new Exception("Numero não pode ser negativo.");
		}
	}
}
