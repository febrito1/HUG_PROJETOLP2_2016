package hotel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.ConsultaHospedagemException;
import excecoes.Excecoes;
import quarto.Quarto;
import quarto.QuartosFactory;

public class SistemaController {
	
	
	FactoryDeHospede factoryHospedes;
	QuartosFactory factoryQuartos;
	
	Map<String, Hospede> clientesCadastrados;
	Map<String, Quarto> catalogoQuartos;
	List<Estadia> estadias;
	
	public SistemaController() {

		catalogoQuartos = new HashMap<>();
		clientesCadastrados = new HashMap<String, Hospede>();
		factoryHospedes = new FactoryDeHospede();
		factoryQuartos = new QuartosFactory();
		estadias = new ArrayList<>();

	}
	
	public void iniciaSistema() {}
	
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
			break;
		case("email"):
			clienteatualizado.setEmailHospede(valor);
			clientesCadastrados.remove(id);
			clientesCadastrados.put(valor, clienteatualizado);
			break;
		case("data de nascimento"):
			clienteatualizado.setAnoNascimento(valor);
			break;
		} 
		
	}
	
	public String getInfo(String Info, String atributo) throws Exception {
		
		if(!(clientesCadastrados.containsKey(Info))){
			throw new Exception("Erro na consulta de hospede. Hospede de email " + Info + " nao foi cadastrado(a).");
		}
		
		String informacao = "";
		Hospede hospedeInfo = clientesCadastrados.get(Info);
		
		switch(atributo.toLowerCase()){
		case("nome"):
			informacao = hospedeInfo.getNomeHospede();
			break;
		case("email"):
			informacao = hospedeInfo.getEmailHospede();
			break;
		case("data de nascimento"):
			informacao = hospedeInfo.getAnoNascimento();
			break;
		}
		return informacao;
	}
	

	public String criaQuarto(String ID, String tipoQuarto) throws Exception {
		if(catalogoQuartos.containsKey(ID)){
			throw new Exception("O quarto de ID" + ID + " já existe.");
		}
		factoryQuartos.criaQuarto(ID, tipoQuarto);
		return ID;
	}
	
	
	public void checkIn(String email, int dias, String ID, String tipoQuarto) throws Exception {
		/* tratar excecoes de email, ID nulos ou vazios */
		Excecoes.tipoInvalido(tipoQuarto);
		
		if(!(clientesCadastrados.containsKey(email))){
			throw new Exception("Erro na consulta de hospede. Hospede de email " + ID + " nao foi cadastrado(a).");
		}
	
		Hospede cliente = clientesCadastrados.get(email);
		
		if((catalogoQuartos.containsKey(ID) && catalogoQuartos.get(ID).getTipo().equalsIgnoreCase(tipoQuarto))){
			Quarto quarto = catalogoQuartos.get(ID);
			
			for (Estadia estadia : estadias) {
				if(estadia.getQuarto().equals(quarto)){
					throw new Exception("Erro ao realizar checkin. Quarto " + ID + " ja esta ocupado.");
				}
			}
		
			Estadia novaEstadia = new Estadia(cliente, quarto, dias);
			estadias.add(novaEstadia);
		}else{
			Quarto quarto = factoryQuartos.criaQuarto(ID, tipoQuarto);
			catalogoQuartos.put(ID, quarto);
			Estadia estadia = new Estadia(cliente, quarto , dias);
			estadias.add(estadia);
		}
		
	}

	public String getInfoHospedagem(String email, String atributo) throws Exception {
		
		if(!clientesCadastrados.containsKey(email)){
			throw new Exception("Erro no cadastro de Hospede. Hospede nao cadastrado");
		}
		
		Hospede hospede = clientesCadastrados.get(email);
		String resultado = "";
		
		switch(atributo.toLowerCase()){
		
		case "hospedagens ativas":
			int estadiasAtivas = 0;	
			for (Estadia estadia : estadias) {
				if(estadia.getHospede().equals(hospede)){
					estadiasAtivas +=1;
				}
			}	
			if(estadiasAtivas == 0){
				throw new Exception("Erro na consluta de hospede "+ hospede.getNomeHospede()+
						" nao esta hospedado(a).");
			}
			resultado = Integer.toString(estadiasAtivas);
			break;
		
		case "total":
			double dinheiroTotal = 0.0;
			
			for (Estadia estadia : estadias) {
				if(estadia.getHospede().equals(hospede)) {
					dinheiroTotal = estadia.getTotal();
				}
			}
		
			if(dinheiroTotal == 0.0){
				throw new ConsultaHospedagemException("Hospede " + hospede.getNomeHospede() +
						" nao esta hospedado(a).");
			}
		
			resultado = String.format("R$%.2f", dinheiroTotal);
			break;
			
		case "quarto":	
			for (Estadia estadia : estadias) {
				if(estadia.getHospede().equals(hospede));
				Quarto quarto = estadia.getQuarto();
				resultado += quarto.getID() + ",";
			}
			
			if(resultado.isEmpty()){
				throw new ConsultaHospedagemException("Hospede " + hospede.getNomeHospede()+
						" nao esta hospedado(a).");
				
			} else if(resultado.charAt(resultado.length() -1)==','){
					resultado = resultado.substring(0, resultado.length()-1);
			}
			break;
			
		default:
			throw new ConsultaHospedagemException("Atributo de pesquisa invalido.");
			
		}
		return resultado;		
	}
	
	
	
	public void fechaSistema(){}

}