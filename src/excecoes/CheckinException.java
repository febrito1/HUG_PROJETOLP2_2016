package excecoes;

public class CheckinException extends Exception{

	
public CheckinException(String mensagem) {
	
	super("Erro ao realizar checkin. " + mensagem);
	
	}
}
