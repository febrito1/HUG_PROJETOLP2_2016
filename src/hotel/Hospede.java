package hotel;

import java.util.HashSet;

import excecoes.*;


public class Hospede {
	
	private String nomeHospede;
	private String emailHospede;
	private int anoNascimento;
	private HashSet<Estadia> estadias;
	
	
	public Hospede(String nomeHospede, String emailHospede, int anoNascimento) throws Exception {
		
		Excecoes.StringException(nomeHospede);
		Excecoes.StringException(emailHospede);
		Excecoes.inteiroException(anoNascimento);
		
		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.anoNascimento = anoNascimento;
		
		estadias = new HashSet<Estadia>();
		
	}


	

	public String getNomeHospede() {
		return nomeHospede;
	}


	public String getEmailHospede() {
		return emailHospede;
	}


	public int getAnoNascimento() {
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


	public void setAnoNascimento(int anoNascimento) throws Exception {
		Excecoes.inteiroException(anoNascimento);
		this.anoNascimento = anoNascimento;
	}
	
	

}
