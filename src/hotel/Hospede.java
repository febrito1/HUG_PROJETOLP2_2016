package hotel;

import excecoes.*;

public class Hospede {
	
	
	private String nomeHospede;
	private String emailHospede;
	private String anoNascimento;
	
	
	public Hospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception {
		
		Excecoes.StringException(nomeHospede);
		Excecoes.StringException(emailHospede);
		//Excecoes.inteiroException(anoNascimento);
		
		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.anoNascimento = anoNascimento;
	}

	

	public String getNomeHospede() {
		return nomeHospede;
	}


	public String getEmailHospede() {
		return emailHospede;
	}


	public String getAnoNascimento() {
		return anoNascimento;
	}


	public void setNomeHospede(String nomeHospede) throws Exception {
		Excecoes.StringException(nomeHospede);
		this.nomeHospede = nomeHospede;
	}


	public void setEmailHospede(String emailHospede) throws Exception {
		Excecoes.StringException(emailHospede);
		this.emailHospede = emailHospede;
	}


	public void setAnoNascimento(String anoNascimento2) throws Exception {
		//Excecoes.inteiroException(anoNascimento2);
		this.anoNascimento = anoNascimento2;
	}
	
	

}
