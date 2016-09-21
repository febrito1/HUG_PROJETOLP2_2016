package excecoes;

public class CadastraPratoInvalidoException extends Exception{
	
	 public CadastraPratoInvalidoException() {
		 super("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
	 }
	 
}
