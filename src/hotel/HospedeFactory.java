package hotel;

import excecoes.Excecoes;

public class HospedeFactory {
	private String nomeHospede;
	private String emailHospede;
	private int anoNascimento;
	
public HospedeFactory(String nomeHospede, String emailHospede, int anoNascimento){
		
		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.anoNascimento = anoNascimento;
	}

	public Hospede criaHospede(String nomeHospede, String emailHospede, int anoNascimento) throws Exception{
		return new Hospede(nomeHospede, emailHospede, anoNascimento);
	}
}
