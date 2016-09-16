package hotel;

import java.util.HashSet;
import excecoes.*;
import hotel.*;

public class SistemaController {
	
	private HashSet<Hospede> clientesCadastrados;
	private HashSet<Hospede> clientesHospedados;
	
	public SistemaController() {
		
		clientesCadastrados = new HashSet<Hospede>();

	}

	public boolean cadastraSistema(String nome, String email, int anoNascimento) throws Exception{
		Excecoes.StringException(nome);
		Excecoes.StringException(email);
		Excecoes.inteiroException(anoNascimento);
		
		return clientesCadastrados.add(new Hospede(nome, email, anoNascimento));
	}
	
	public boolean mudaNome(String email, String nome) throws Exception{
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(email)){
				hospede.setNomeHospede(nome);
				return true;
			} 
		}return false;
		
	}
	
	public void mudaEmail(String emailAntigo, String email) throws Exception{
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(emailAntigo)){
				hospede.setEmailHospede(emailAntigo);
			}
		}
	}
	
	public void mudaDataNascimento(String email, int anoNascimento)throws Exception{
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(email)){
				hospede.setAnoNascimento(anoNascimento);
			}
		}
	}
	
	public String buscaHospede(String email) throws Exception{
		Excecoes.StringException(email);
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(email)){
				return hospede.getEmailHospede();
			}
		} return "Erro na consulta de hospede. Hospede de email " + email + "nao foi cadastrado(a).";
	}
	
	public Hospede getHospete(String email) throws Exception{
		Hospede procuraHospede = null;
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(email)){
				procuraHospede = hospede;
			}
		} throw new Exception("Hospede nao encontrado.");
	}
	
	
	public boolean removeHospede(String email){
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(email)){
				return clientesCadastrados.remove(hospede);
			}
		} return false;
	}
	
	/* RESOLVER QUESTOES DE HOSPEDE/ESTADIA*/
	
	/*public boolean checkIn(Hospede hospede) throws Exception{
		
	}*/
	
	public String checkIn(String email, String quarto, int dias) throws Exception{
		for
	}
		
}
