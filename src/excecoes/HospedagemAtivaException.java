package excecoes;

public class HospedagemAtivaException extends Exception {

	public HospedagemAtivaException(String mensagem) {
		super("Erro ao checar hospedagem ativa. " + mensagem);
	}

}
