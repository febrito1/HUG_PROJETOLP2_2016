package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hotel.Checkout;
import excecoes.*;
import cliente.Estadia;
import cliente.FactoryDeHospede;
import cliente.Hospede;
import quarto.Quarto;
import quarto.QuartosFactory;

public class SistemaController {

	private QuartosFactory factoryQuartos;
	private FactoryDeHospede factoryHospedes;

	private Map<String, Hospede> clientesCadastrados;
	private Map<String, Quarto> catalogoQuartos;
	private Map<String, Quarto> quartosOcupados;
	private Excecoes excecoes = new Excecoes();;
	private List<Checkout> checkout;

	private double totalArrecadado = 0.0;
	
	public SistemaController() {

		checkout = new ArrayList<>();
		quartosOcupados = new HashMap<>();
		catalogoQuartos = new HashMap<>();
		clientesCadastrados = new HashMap<>();
		factoryHospedes = new FactoryDeHospede();
		factoryQuartos = new QuartosFactory();

	}

	public void iniciaSistema() {
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception {

		excecoes.CadastroInvalidoException(nome);
		excecoes.EmailInvalidoException(email);
		excecoes.DatadeNascimentoVazia(dataNascimento);

		Hospede novoHospede = factoryHospedes.criaHospede(nome, email, dataNascimento);
		if (novoHospede.getIdade() < 18) {
			throw new Exception("Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
		}
		clientesCadastrados.put(email, novoHospede);
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
			throw new Exception("Erro na remocao do Hospede. Formato de email invalido.");
		}
		clientesCadastrados.remove(email);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		if (!clientesCadastrados.containsKey(id)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}

		Hospede clienteatualizado = clientesCadastrados.get(id);
		switch (atributo.toLowerCase()) {

		case ("nome"):
			excecoes.atualizaCadastroException(valor);
			clienteatualizado.setNomeHospede(valor);
			break;

		case ("email"):
			excecoes.atualizaEmailException(valor);

			clienteatualizado.setEmailHospede(valor);

			clientesCadastrados.remove(id);
			clientesCadastrados.put(valor, clienteatualizado);
			break;

		case ("data de nascimento"):

			excecoes.atualizaDataException(valor);
			Hospede novoHospede = factoryHospedes.criaHospede(valor, valor, valor);
			if (novoHospede.getIdade() < 18) {
				throw new Exception("Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}
			clienteatualizado.setAnoNascimento(valor);
			break;

		}

	}

	public String getInfo(String Info, String atributo) throws Exception {

		if (!(clientesCadastrados.containsKey(Info))) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + Info + " nao foi cadastrado(a).");
		}

		String informacao = "";

		Hospede hospedeInfo = clientesCadastrados.get(Info);

		switch (atributo.toLowerCase()) {

		case ("nome"):
			informacao += hospedeInfo.getNomeHospede();
			break;
		case ("email"):

			informacao += hospedeInfo.getEmailHospede();
			break;

		case ("data de nascimento"):
			informacao += hospedeInfo.getAnoNascimento();
			break;
		}
		return informacao;
	}

	public String criaQuarto(String ID, String tipoQuarto) throws Exception {
		if (catalogoQuartos.containsKey(ID)) {
			throw new Exception("O quarto de ID" + ID + " ja existe.");
		}
		factoryQuartos.criaQuarto(ID, tipoQuarto);
		return ID;
	}

	public void realizaCheckin(String email, int dias, String ID, String tipoQuarto) throws Exception {

		excecoes.checkinIDException(ID);
		excecoes.checkinEmailException(email);
		excecoes.tipoInvalido(tipoQuarto);
		excecoes.checkinDataInvalida(dias);

		if (!(clientesCadastrados.containsKey(email))) {

			throw new Exception("Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");
		}

		Hospede hospede = clientesCadastrados.get(email);

		if (catalogoQuartos.containsKey(ID)) {
			if (catalogoQuartos.get(ID).getTipo().equals(tipoQuarto)) {
			}

			if (quartosOcupados.containsKey(ID)) {
				throw new Exception("Erro ao realizar checkin. Quarto " + ID + " ja esta ocupado.");
			}

			Quarto quarto = catalogoQuartos.get(ID);
			Estadia estadia = new Estadia(quarto, dias);
			hospede.adicionaEstadia(estadia);
			quartosOcupados.put(ID, quarto);
		}

		else {

			Quarto quarto = factoryQuartos.criaQuarto(ID, tipoQuarto);
			Estadia estadia = new Estadia(quarto, dias);
			hospede.adicionaEstadia(estadia);
			quartosOcupados.put(ID, quarto);
			catalogoQuartos.put(ID, quarto);
		}
	}

	public String getInfoHospedagem(String email, String atributo) throws Exception {

		excecoes.HospedagemAtivaException(email);

		if (!(clientesCadastrados.containsKey(email))) {
			throw new Exception("Erro no cadastro de Hospede. Hospede nao cadastrado");
		}

		Hospede hospede = clientesCadastrados.get(email);
		String resultado = "";

		switch (atributo.toLowerCase()) {

		case "hospedagens ativas":

			int estadiasAtivas = 0;

			estadiasAtivas = hospede.qtdEstadias();

			if (estadiasAtivas == 0) {
				throw new Exception("Erro na consulta de hospedagem. Hospede " + hospede.getNomeHospede()
						+ " nao esta hospedado(a).");
			}

			resultado = Integer.toString(estadiasAtivas);
			break;

		case "total":
			if (hospede.getPrecoQuartos() == 0) {
				throw new Exception("Erro na consulta de hospedagem. Hospede " + hospede.getNomeHospede()
						+ " nao esta hospedado(a).");
			}
			resultado = String.format("R$%.2f", hospede.getPrecoQuartos());

			break;

		case "quarto":
			resultado = hospede.getQuartos();

			if (resultado.isEmpty()) {
				throw new ConsultaHospedagemException(
						"Hospede " + hospede.getNomeHospede() + " nao esta hospedado(a).");
			}

			if (resultado.charAt(resultado.length() - 1) == ',') {
				resultado = resultado.substring(0, resultado.length() - 1);

			}
			break;

		default:
			throw new ConsultaHospedagemException("Atributo de pesquisa invalido.");

		}
		
		return resultado;
	}

	public String realizaCheckout(String email, String quarto) throws Exception {

		if (email == null || email.trim().isEmpty()) {
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		}
		if (!(email.contains("@")) || !(email.contains("."))) {
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		}

		for (int i = 0; i < quarto.length(); i++) {
			char letra = quarto.charAt(i);
			if (!(Character.isLetter(letra) || Character.isDigit(letra))) {
				throw new Exception("Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");
			}
		}

		String resultado = "";

		Hospede clienteOperacao = clientesCadastrados.get(email);

		List<Estadia> estadias = clienteOperacao.getEstadias();

		for (Estadia estadia : estadias) {
			if (estadia.getQuarto().getID().equals(quarto)) {

				Checkout novoCheckout = new Checkout(clienteOperacao.getNomeHospede(), quarto,
						estadia.getQuarto().getPreco() * estadia.getQuantidadeDias(), LocalDate.now());
				checkout.add(novoCheckout);
				resultado = String.format("R$%.2f", estadia.getQuarto().getPreco() * estadia.getQuantidadeDias());
				clienteOperacao.removeEstadia(quarto);
				totalArrecadado += estadia.getQuarto().getPreco() * estadia.getQuantidadeDias();
				quartosOcupados.remove(quarto);
				break;
			}

		}

		return resultado;
	}

	public String consultaTransacoes(String operacao) {

		String resultado = "";

		switch (operacao.toLowerCase()) {

		case "quantidade":

			resultado = Integer.toString(checkout.size());
			break;

		case "total":
			
			double valorTotal = 0.0;

			valorTotal = totalArrecadado;

			resultado = String.format("R$%.2f", valorTotal);
			break;

		case "nome":
			
			for (Checkout checkout : checkout) {
				resultado += checkout.getNomeCliente() + ";";
			}
			if (resultado != "" && resultado.charAt(resultado.length() - 1) == ';') {
				resultado = resultado.substring(0, resultado.length() - 1);
			}
			
			break;

		default:
			
			break;
			
		}
		
		return resultado;
	}

	public String consultaTransacoes(String operacao, int indice) throws Exception {

		String resultado = "";
		
		if (indice > checkout.size() || indice < 0) {
			throw new Exception("Erro na consulta de transacoes. Indice invalido.");
		}
		switch (operacao.toLowerCase()) {

		case "total":
			
			resultado = String.format("R$%.2f", checkout.get(indice).getTotalGasto());
			break;
			
		case "nome":
			resultado = checkout.get(indice).getNomeCliente();

			break;
		default:
			break;
		}
		return resultado;
	}

	public void fechaSistema() {
	}
}
