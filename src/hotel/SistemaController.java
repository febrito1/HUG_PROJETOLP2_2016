package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import quarto.Quarto;
import quarto.QuartosFactory;

public class SistemaController {
	
	private FactoryDeHospede factoryHospedes;
	private Map<String, Hospede> clientesCadastrados;
	private Map<String, Quarto> catalogoQuartos;
	private Hospede hospede;
	LocalDate dataNascimento;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public SistemaController() {

		clientesCadastrados = new HashMap<String, Hospede>();
		factoryHospedes = new FactoryDeHospede();

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
	
	public String getInfo(String Info, String atributo) throws Exception{
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
	

}