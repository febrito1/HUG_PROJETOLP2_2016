package cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import excecoes.Excecoes;
import quarto.Quarto;

public class Hospede {
	
	LocalDate anoNascimento, data;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String nomeHospede, emailHospede;
	private Set<String> quartos;
	
	
	public Hospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception {
	
		Excecoes.StringException(nomeHospede);
		Excecoes.StringException(emailHospede);
		Excecoes.StringException(anoNascimento);
		
		
		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.setAnoNascimento(anoNascimento);
		
		quartos = new HashSet<>();
	}

	
	public boolean adicionaQuarto(String ID){
		return quartos.add(ID);
	}
	

	public boolean removeQuarto(String ID){
		return quartos.remove(ID);
	}
	
	
	public String getQuartos(){
		String resultado = "";
		for (String quarto : quartos){
			resultado += quarto + ",";
		}
		return resultado;
	}
	
	public String getNomeHospede() {
		return nomeHospede;
	}


	public String getEmailHospede() {
		return emailHospede;
	}


	public String getAnoNascimento() {
		String novoAnoNascimento = formatter.format(this.anoNascimento);
		return novoAnoNascimento;
	}

	public int getIdade() throws Exception{
		int idade = (int)ChronoUnit.YEARS.between(anoNascimento, LocalDate.now());
		return idade;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoNascimento == null) ? 0 : anoNascimento.hashCode());
		result = prime * result + ((emailHospede == null) ? 0 : emailHospede.hashCode());
		result = prime * result + ((formatter == null) ? 0 : formatter.hashCode());
		result = prime * result + ((nomeHospede == null) ? 0 : nomeHospede.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object novoHospede) {
		if(novoHospede instanceof Hospede){
			Hospede outro = (Hospede) novoHospede;
			if(outro.getEmailHospede().equalsIgnoreCase(emailHospede)){
				return true;
			}
		}
		return true;
	}
	
	
	

}
