package cliente;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import excecoes.excecoes;
import quarto.Quarto;

public class Hospede {

	LocalDate anoNascimento, data;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String nomeHospede, emailHospede;
	private List<Estadia> estadias;
	private excecoes excecoes = new excecoes();

	public Hospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception {

		excecoes.StringException(nomeHospede);
		excecoes.StringException(emailHospede);
		excecoes.StringException(anoNascimento);

		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.setAnoNascimento(anoNascimento);

		estadias = new ArrayList<>();
	}

	public void adicionaEstadia(Estadia estadia) throws Exception {
		estadias.add(estadia);
	}

	public String getQuartos() {
		String resultado = "";
		for (Estadia estadia : estadias) {
			Quarto quarto = estadia.getQuarto();
			resultado += quarto.getID();
			resultado += ",";
		}
		return resultado;
	}

	public double getPrecoQuartos() {
		double precoTotal = 0;
		for (Estadia estadia : estadias) {
			precoTotal += estadia.getQuarto().getPreco() * estadia.getQuantidadeDias();
		}
		return precoTotal;
	}

	public List<Estadia> getEstadias() {
		return estadias;
	}

	public int qtdEstadias() {
		return estadias.size();
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void removeEstadia(String ID) {
		for (int i = 0; i < estadias.size(); i++) {
			if (estadias.get(i).getID().equals(ID)) {
				estadias.remove(i);
			}
		}
	}

	public String getEmailHospede() {
		return emailHospede;
	}

	public String getAnoNascimento() {
		String novoAnoNascimento = formatter.format(this.anoNascimento);
		return novoAnoNascimento;
	}

	public int getIdade() throws Exception {
		int idade = (int) ChronoUnit.YEARS.between(anoNascimento, LocalDate.now());
		return idade;
	}

	public void setNomeHospede(String nomeHospede) throws Exception {

		excecoes.StringException(nomeHospede);
		this.nomeHospede = nomeHospede;
	}

	public void setEmailHospede(String emailHospede) throws Exception {
		excecoes.StringException(emailHospede);
		this.emailHospede = emailHospede;
	}

	public void setAnoNascimento(String anoNascimento) throws Exception {
		excecoes.StringException(anoNascimento);
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
		if (novoHospede instanceof Hospede) {
			Hospede outro = (Hospede) novoHospede;
			if (outro.getEmailHospede().equalsIgnoreCase(emailHospede)) {
				return true;
			}
		}
		return true;
	}

}
