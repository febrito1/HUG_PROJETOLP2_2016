package excecoes;

public class QuantidadeInvalidaPratosException extends Exception {
	public QuantidadeInvalidaPratosException(){
		super("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
	}
}
