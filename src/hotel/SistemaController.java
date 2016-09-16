package hotel;

import java.util.HashMap;

import excecoes.Excecoes;

public class SistemaController {
	
	
	private FactoryDeHospede factoryHospedes;
	private HashMap<String, Hospede> clientesCadastrados;
	private HashMap<String, Hospede> clientesHospedados;

	public SistemaController() {

		clientesCadastrados = new HashMap<String, Hospede>();

	}
	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception{
		clientesCadastrados.put(email, factoryHospedes.criaHospede(nome, email, dataNascimento));
		return email;
	}
	
	public String buscaHospede(String email) throws Exception {
		if (!clientesCadastrados.containsKey(email)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		return email;
	}
		
	
	public void removeHospede(String email) throws Exception {
			if (!clientesCadastrados.containsKey(email)) {
				throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
			}
			clientesCadastrados.remove(email);
		}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		if (!clientesCadastrados.containsKey(id)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}
		Hospede clienteatualizado = clientesCadastrados.get(id); 
		switch (atributo.toLowerCase()){
		case("nome"):
			clienteatualizado.setNomeHospede(valor);
		case("email"):
			clienteatualizado.setEmailHospede(valor);
			clientesCadastrados.remove(id);
			clientesCadastrados.put(valor, clienteatualizado);
		case("ano"):
			clienteatualizado.setAnoNascimento(valor);
			break;
		} 
	}
}
