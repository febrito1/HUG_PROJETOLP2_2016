package excecoes;

public class ConsultaHospedagemException extends Exception {

	public ConsultaHospedagemException(String mensagem) {
		super("Erro na consulta de hospedagem. " + mensagem);
	}

}