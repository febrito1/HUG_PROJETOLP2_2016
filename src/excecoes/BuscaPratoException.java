package excecoes;

public class BuscaPratoException extends Exception  {

	public BuscaPratoException(){
		
		super("N�o existe esse prato no cardapio.");
	}
}
