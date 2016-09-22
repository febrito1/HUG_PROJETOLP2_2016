package excecoes;

public class ConsultaTransacaoException extends Exception {

	public ConsultaTransacaoException(String mensagem) {

		super("Erro na consulta de transacoes. " + mensagem);

	}

}
