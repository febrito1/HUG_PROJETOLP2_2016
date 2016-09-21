package excecoes;

public class BuscaPratoException extends Exception  {

	public BuscaPratoException(){
		
		super("Não existe esse prato no cardapio.");
	}
}
