package excecoes;

public class ComponenteVazioException extends Exception {

	public ComponenteVazioException() {

		super("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");

	}
}
