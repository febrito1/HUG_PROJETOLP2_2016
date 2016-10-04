package cliente;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import excecoes.Excecoes;
import fidelidade.CartaoFidelidade;
import fidelidade.FidelidadePadrao;
import fidelidade.FidelidadePremium;
import fidelidade.FidelidadeVIP;
import quarto.Quarto;
/** 
 * Classe de Hospede que possui metodos e valores para o mesmo
 *
 */
public class Hospede {

	LocalDate anoNascimento, data;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Excecoes excecoes = new Excecoes();
	private String nomeHospede, emailHospede;
	private List<Estadia> estadias;
	private CartaoFidelidade fidelidade;
	
	/**
	 * Construtor de hospede 
	 * 
	 * @param String - nomeHospede
	 * @param String - emailHospede 
	 * @param String - anoNascimento 
	 * @throws StringException
	 */
	public Hospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception {

		excecoes.StringException(nomeHospede);
		excecoes.StringException(emailHospede);
		excecoes.StringException(anoNascimento);

		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.setAnoNascimento(anoNascimento);

		fidelidade = new FidelidadePadrao();
		estadias = new ArrayList<>();
	}
	
	/**
	 * Adiciona uma estadia a lista de estadias do hospede.
	 * 
	 * @param Estadia estadia - estadia a ser adicionada
	 * @throws Exception
	 */
	public void adicionaEstadia(Estadia estadia) throws Exception {
		estadias.add(estadia);
	}
	
	/**
	 * Recupera as os quartos entre a lista de estadias e retorna uma String com o ID delas.
	 * 
	 * @return String - ID(s)
	 */
	public String getQuartos() {
		String resultado = "";
		for (Estadia estadia : estadias) {
			Quarto quarto = estadia.getQuarto();
			resultado += quarto.getID();
			resultado += ",";
		}
		return resultado;
	}
	
	/**
	 * Calcula e retorna o preco das estadias.
	 * 
	 * @return double - preco total
	 */
	public double getPrecoQuartos() {
		double precoTotal = 0;
		for (Estadia estadia : estadias) {
			precoTotal += estadia.getQuarto().getPreco() * estadia.getQuantidadeDias();
		}
		return precoTotal;
	}
	
	/**
	 * Metodo que chama a funcao que converte pontos e retorna seu resultado.
	 * 
	 * @param Int - pontos
	 * @return String - pontos convertidos
	 * @throws Exception
	 */
	public String convertePontos(int pontos) throws Exception {
		return fidelidade.convertePontos(pontos);
	}
	
	/**
	 * Adiciona pontos ao cartao de fidelidade.
	 * 
	 * @param Double - preco
	 */
	public void adicionaPontos(double preco) {
		fidelidade.addPontos(preco);
	}
	
	/**
	 * Retrona os pontos do cartao de fidelidade do hospede.
	 * 
	 * @return int - pontos
	 */
	public int getPontos() {
		return fidelidade.getPontos();
	}
	
	/**
	 * Muda o tipo de fidelidade do hospede atraves da quantidade de pontos que o mesmo possui
	 * em seu cartao de fidelidade.
	 */
	public void mudaFidelidade(){
	
		if(getPontos() < 350){
			fidelidade = new FidelidadePadrao(fidelidade.getPontos());
		}else if((getPontos() >= 350 && getPontos() <= 1000)){
			fidelidade = new FidelidadePremium(fidelidade.getPontos());
		}else if(getPontos() > 1000){
			fidelidade = new FidelidadeVIP(fidelidade.getPontos());
		}
	}
	
	/**
	 * Retorna um desconto para o hospede a depender do seu tipo de fidelidade.
	 * @param Double - preco
	 * @return double - desconto
	 */
	public double precoDesconto(double preco) {
		return fidelidade.desconto(preco);

	}
	
	/**
	 * Retorna a lista de estadias.
	 * 
	 * @return List<Estadia> - estadias
	 */
	public List<Estadia> getEstadias() {
		return estadias;
	}
	
	/**
	 * Retorna o total de estadias em uso do hospede.
	 * 
	 * @return int - quantidade de estadias
	 */
	public int qtdEstadias() {
		return estadias.size();
	}
	
	/**
	 * Retorna o nome do hospede
	 * 
	 * @return String - nomeHospede
	 */
	public String getNomeHospede() {
		return nomeHospede;
	}
	
	/**
	 * Remove uma estadia da lista de estadias.
	 * 
	 * @param String - ID
	 */
	public void removeEstadia(String ID) {
		for (int i = 0; i < estadias.size(); i++) {
			if (estadias.get(i).getID().equals(ID)) {
				estadias.remove(i);
			}
		}
	}
	
	/**
	 * Retorna o email do hospede
	 * 
	 * @return String - emailHospede
	 */
	public String getEmailHospede() {
		return emailHospede;
	}
	
	/**
	 * Retorna o ano de nascimento do hospede.
	 * 
	 * @return String - ano de nascimento
	 */
	public String getAnoNascimento() {
		String novoAnoNascimento = formatter.format(this.anoNascimento);
		return novoAnoNascimento;
	}
	
	/**
	 * Retorna a idade do hospede.
	 * 
	 * @return int - idade
	 * @throws Exception
	 */
	public int getIdade() throws Exception {
		int idade = (int) ChronoUnit.YEARS.between(anoNascimento, LocalDate.now());
		return idade;
	}

	/**
	 * Acessa e modifica o nome do hospede.
	 * 
	 * @param String nomeHospede - novo nome do hospede
	 * @throws StringException
	 */
	public void setNomeHospede(String nomeHospede) throws Exception {

		excecoes.StringException(nomeHospede);
		this.nomeHospede = nomeHospede;
	}
	
	/**
	 * Acessa e modifica o email do hospede.
	 * 
	 * @param String emailHospede - novo email do hospede
	 * @throws StringException
	 */
	public void setEmailHospede(String emailHospede) throws Exception {
		excecoes.StringException(emailHospede);
		this.emailHospede = emailHospede;
	}
	
	/**
	 * Acessa e modifica o ano de nascimento do hospede.
	 * 
	 * @param String anoNascimento - novo ano de nascimento do hospede
	 * @throws StringException
	 */
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
	
	/**
	 * Equals que compara o hospede atraves do email.
	 */
	@Override
	public boolean equals(Object novoHospede) {
		if (novoHospede instanceof Hospede) {
			Hospede outro = (Hospede) novoHospede;
			if (outro.getEmailHospede().equalsIgnoreCase(this.emailHospede)) {
				return true;
			}
		}
		return true;
	}

}
