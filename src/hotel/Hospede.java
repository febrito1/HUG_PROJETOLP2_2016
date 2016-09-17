package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import excecoes.Excecoes;

public class Hospede {
	
	LocalDate anoNascimento;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String nomeHospede, emailHospede;
	
	
	public Hospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception {
		
		Excecoes.StringException(nomeHospede);
		Excecoes.StringException(emailHospede);
		Excecoes.StringException(anoNascimento);
		
		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.setAnoNascimento(anoNascimento);
	}

	

	public String getNomeHospede() {
		return nomeHospede;
	}


	public String getEmailHospede() {
		return emailHospede;
	}


	public String getAnoNascimento() {
		String anoNascimento = formatter.format(this.anoNascimento);
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


	public void setAnoNascimento(String anoNascimento) throws Exception {
		Excecoes.StringException(anoNascimento);
		LocalDate data = LocalDate.parse(anoNascimento, formatter);
		this.anoNascimento = data;
	}
	
	

}
