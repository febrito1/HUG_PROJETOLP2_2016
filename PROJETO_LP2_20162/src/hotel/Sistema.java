package hotel;

import java.util.HashSet;
import excecoes.*;
import hotel.*;

public class Sistema {
	
	private HashSet<Hospede> clientesCadastrados;
	private HashSet<Hospede> clientesHospedados;
	
	public Sistema() {
		
		clientesCadastrados = new HashSet<Hospede>();

	}

	public boolean cadastraSistema(Hospede hospede) throws Exception{
		Excecoes.verificaCadastro(hospede);
		return clientesCadastrados.add(hospede);
	}
	
	public void mudaNome(Hospede hospede, String nome) throws Exception{
		hospede.setNomeHospede(nome);
	}
	
	public void mudaEmail(Hospede hospede, String email) throws Exception{
		hospede.setEmailHospede(email);
	}
	
	public void mudaDataNascimento(Hospede hospede, int anoNascimento)throws Exception{
		hospede.setAnoNascimento(anoNascimento);
	}
	
	public boolean buscaHospede(String email) throws Exception{
		Excecoes.StringException(email);
		for (Hospede hospede : clientesCadastrados) {
			if(hospede.getEmailHospede().equalsIgnoreCase(email)){
				return true;
			}
		} return false;
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
	
		
}
