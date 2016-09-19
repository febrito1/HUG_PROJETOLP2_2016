package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotel.Estadia;
import hotel.Hospede;
import excecoes.ConsultaHospedagemException;
import hotel.Checkout;
import excecoes.*;
import quarto.Quarto;
import quarto.QuartosFactory;

public class SistemaController {
	
	private QuartosFactory factoryQuartos;
	private FactoryDeHospede factoryHospedes;
	
	private Map<String, Hospede> clientesCadastrados;
	private Map<String, Quarto> catalogoQuartos;	
	private List<Estadia> estadias;
	private List<Checkout> checkouts;
	
	LocalDate dataNascimento;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public SistemaController() {
		
		
		estadias = new ArrayList<>();
		checkouts = new ArrayList<>();
		catalogoQuartos= new HashMap<>();
		clientesCadastrados = new HashMap<>();
		factoryHospedes = new FactoryDeHospede();
		factoryQuartos = new QuartosFactory();

	}
	
	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception{
		
		String novoAnoNascimento = formatter.format(this.dataNascimento);
		LocalDate data = LocalDate.parse(novoAnoNascimento, formatter);
		int idade = (int)ChronoUnit.YEARS.between(data, LocalDate.now());
		if(!(idade >= 18)){
			throw new Exception("Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
		}
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
	
	public String getInfoHospede(String Info, String atributo) throws Exception {
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
	

	public String criaQuarto(String ID, String tipoQuarto) throws Exception{
		if(catalogoQuartos.containsKey(ID)){
			throw new Exception("O quarto de ID" + ID + " já existe.");
		}
		QuartosFactory novoQuarto = new QuartosFactory();
		novoQuarto.criaQuarto(ID, tipoQuarto);
		
		return ID;
	}
	
	public void realizaCheckin(String email, int dias, String ID, String tipoQuarto) throws Exception {
		/* tratar excecoes de email, ID nulos ou vazios */
		Excecoes.tipoInvalido(tipoQuarto);
		
		if(!(clientesCadastrados.containsKey(email))){
			throw new Exception("Erro na consulta de hospede. Hospede de email " + ID + " nao foi cadastrado(a).");
		}
	
		Hospede cliente = clientesCadastrados.get(email);
		
		if((catalogoQuartos.containsKey(ID) && catalogoQuartos.get(ID).
				getTipo().equalsIgnoreCase(tipoQuarto))){
			
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
	
	
	public String realizaCheckout(String email, String quarto) throws Exception{
		String resultado ="";
		if(!clientesCadastrados.containsKey(email)){
			throw new ConsultaHospedagemException("fail");
		}
		Hospede clienteOperacao = clientesCadastrados.get(email);
		if(catalogoQuartos.containsKey(quarto)){
			Quarto quartoOperacao = catalogoQuartos.get(quarto);
			for(Estadia estadia : estadias){
				if(estadia.getHospede().equals(clienteOperacao) && estadia.getQuarto().equals(quartoOperacao)){
					Checkout novoCheckout = new Checkout(clienteOperacao.getNomeHospede(), quarto, estadia.getTotal(), LocalDate.now());
					checkouts.add(novoCheckout);
					resultado = String.format("R$%.2f", estadia.getTotal());
					estadias.remove(estadia);
					break;
				}
			}
		}
		return resultado;
	}
	
	public String consultaTransacoes(String operacao) {
		
		String resultado = "";
		
		switch(operacao.toLowerCase()) {
		
		case "quantidade":
			
			resultado = Integer.toString(checkouts.size());
			break;
		
		case "total":	
			double valorTotal = 0.0;
			
			for (Checkout checkout : checkouts) {
				valorTotal += checkout.getTotalGasto();
				
			}
			resultado = String.format("R$%.2f", valorTotal);
			break;
		
		case "nome":
			for (Checkout checkout : checkouts) {
				resultado += checkout.getNomeCliente()+";";
			}
			if(resultado != "" && resultado.charAt(resultado.length() -1)==';'){
				resultado = resultado.substring(0, resultado.length() -1);
			}
			break;
		
		default:
			break;
			}
		return resultado;
	}
	
	public String consultaTransacoes(String operacao, int indice) throws Exception {
		
		String resultado = "";
		if(indice > checkouts.size()){
			throw new ConsultaHospedagemException("Indice invalido.");
		}
		switch(operacao.toLowerCase()){
		
		case "total":
			resultado = String.format("R$ %2f",checkouts.get(indice).getTotalGasto());
			break;
		case "nome":
			resultado = checkouts.get(indice).getNomeCliente();
			break;
		default:
			break;
		}
		return resultado;
	}
	

}