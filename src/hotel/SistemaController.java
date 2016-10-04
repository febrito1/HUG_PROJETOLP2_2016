package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import restaurante.Alimentacao;
import restaurante.Prato;
import restaurante.RefeicaoCompleta;
import restaurante.RestauranteController;

/**
 * Classe que controla o sistema do hotel com suas funcionalidades
 * 
 */
public class SistemaController {

	private QuartosFactory factoryQuartos;
	private FactoryDeHospede factoryHospedes;
	private RestauranteController controllerRestaurante;
	private Map<String, Hospede> clientesCadastrados;
	private Map<String, Quarto> catalogoQuartos;
	private Map<String, Quarto> quartosOcupados;
	private Excecoes excecoes = new Excecoes();;
	private List<ControleDeGastos> transacaoes;

	private LocalDate dataNascimento;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Inicia o SistemaController
	 */
	public SistemaController() {
		controllerRestaurante = new RestauranteController();
		transacaoes = new ArrayList<>();
		quartosOcupados = new HashMap<>();
		catalogoQuartos = new HashMap<>();
		clientesCadastrados = new HashMap<>();
		factoryHospedes = new FactoryDeHospede();
		factoryQuartos = new QuartosFactory();

	}

	public void iniciaSistema() {
	}

	/**
	 * Cadastra um novo hospede na lista de clientes cadastrados.
	 * 
	 * @param String - nome
	 * @param String - email
	 * @param String - dataNascimento
	 * @return String - email
	 * @throws Exception
	 */
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

	/**
	 * Procura por um hospede cadastrado atraves do email.
	 * 
	 * @param String - email
	 * @return String - email
	 * @throws Exception
	 */
	public String buscaHospede(String email) throws Exception {
		if (!clientesCadastrados.containsKey(email)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		return email;
	}

	/**
	 * Remove um hospede cadastrado atraves do email.
	 * 
	 * @param String - email
	 * @throws Exception
	 */
	public void removeHospede(String email) throws Exception {
		if (!clientesCadastrados.containsKey(email)) {
			throw new Exception("Erro na remocao do Hospede. Formato de email invalido.");
		}
		clientesCadastrados.remove(email);
	}

	/**
	 * Atualiza um atributo do hospede, esse atributo pode ser o nome, email ou data de nascimento.
	 * 
	 * @param String - id
	 * @param String - atributo
	 * @param String - valor
	 * @throws Exception
	 */
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
				throw new Exception(
						"Erro na atualizacao do cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.");
			}
			clienteatualizado.setAnoNascimento(valor);
			break;

		}

	}

	/**
	 * Retorna uma informacao de um clente cadastrado, a informacao pode ser o nome, email, data de nascimento ou os pontos do cartao fidelidade.
	 * @param String - Info
	 * @param String - atributo
	 * @return String - informacao
	 * @throws Exception
	 */
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
		case ("pontos"):
			
			informacao += hospedeInfo.getPontos();
			break;	
			
		default:
			System.out.println("invalido");
			break;
			
		}
		return informacao;
	}

	/**
	 * Cria um novo quarto, delegando essa funcao para a factory de quarto.
	 * 
	 * @param String - ID
	 * @param String - tipoQuarto
	 * @return
	 * @throws Exception
	 */
	public String criaQuarto(String ID, String tipoQuarto) throws Exception {
		if (catalogoQuartos.containsKey(ID)) {
			throw new Exception("O quarto de ID" + ID + " ja existe.");
		}
		factoryQuartos.criaQuarto(ID, tipoQuarto);
		return ID;
	}

	/**
	 * Realiza o chekin de um hospede criando uma nova estadia para o mesmo.
	 * 
	 * @param String - email
	 * @param Int - dias
	 * @param String - ID
	 * @param String - tipoQuarto
	 * @throws Exception
	 */
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
			
			if (quartosOcupados.containsKey(ID)) {
				throw new CheckinException("Quarto " + ID + " ja esta ocupado.");
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
			catalogoQuartos.put(ID, quarto);
			quartosOcupados.put(ID, quarto);
		}
	}

	/**
	 * Retorna uma informacao a cerca da hospedagem de um hospede, essa informacao pode ser sobre 
	 * as hospedaogens aivas, valor total das hospedagens ou os quartos ocupados. 
	 * 
	 * @param String - email
	 * @param String - atributo
	 * @return String - informacao
	 * @throws Exception
	 */
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
						"Hospede " + hospede.getNomeHospede() + " nao esta hospedado(a).");}

		  if (resultado.charAt(resultado.length() - 1) == ',') {
				resultado = resultado.substring(0, resultado.length() - 1);

			
			}
			break;

		default:
			throw new ConsultaHospedagemException("Atributo de pesquisa invalido.");

		}
		return resultado;
	}

	/**
	 * Realiza o checkout de um hospede e retorna uma String contendo o valor gasto nas estadias.
	 * 
	 * @param String - email
	 * @param String - quarto
	 * @return String - valor gasto
	 * @throws Exception
	 */
	public String realizaCheckout(String email, String quarto) throws Exception {
		
		if(email == null  || email.trim().isEmpty()) {
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		}
		if (!(email.contains("@")) || !(email.contains("."))) {
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		}
		
		for(int i = 0; i < quarto.length(); i++){
			char letra = quarto.charAt(i);
			if(!(Character.isLetter(letra) ||	Character.isDigit(letra))){
				throw new Exception("Erro ao realizar checkout. ID do quarto invalido, use apenas numeros ou letras.");
			}
		}
		
		String resultado = "";
		
		Hospede clienteOperacao = clientesCadastrados.get(email);
		
		List<Estadia> estadias = clienteOperacao.getEstadias();
		
		for (Estadia estadia : estadias) {
			if(estadia.getQuarto().getID().equals(quarto)){
				
				double precoBruto = estadia.getQuarto().getPreco()*estadia.getQuantidadeDias();	
				
				clienteOperacao.adicionaPontos(precoBruto);
				double preco = clienteOperacao.precoDesconto(precoBruto);
				ControleDeGastos novoCheckout = new Checkout(clienteOperacao.getNomeHospede(), quarto,preco,LocalDate.now());	
				transacaoes.add(novoCheckout);
				
				resultado = String.format("R$%.2f",preco);
				clienteOperacao.removeEstadia(quarto);
				quartosOcupados.remove(quarto);
				
				clienteOperacao.mudaFidelidade();
				break;
			}
		
		}
		
		return resultado;
	}

	/**
	 * Retorna informacoes a respeito das transacoes.
	 * As informacoes podem ser sobre a quantidade, total ou um historico de transacoes.
	 * @param String - operacao
	 * @return String - resultado
	 */
	public String consultaTransacoes(String operacao) {

		String resultado = "";

		switch (operacao.toLowerCase()) {

		case "quantidade":

			resultado = Integer.toString(transacaoes.size());
			break;

		case "total":		
			resultado = String.format("R$%.2f", precoTotalOperacoes());
			break;

		case "nome":
			for (ControleDeGastos transacoes : transacaoes) {
				resultado += transacoes.getNomeCliente() + ";";
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
	
	/**
	 * Consulta uma determinada transacao atraves do indice.
	 * A operacao de consulta pode ser acerca do total, nome do cliente ou detalhes da transacao.
	 * 
	 * @param String - operacao
	 * @param Int - indice
	 * @return String - resultado
	 * @throws Exception
	 */
	public String consultaTransacoes(String operacao, int indice) throws Exception {

		String resultado = "";
		if (indice > transacaoes.size() || indice < 0) {
			throw new Exception("Erro na consulta de transacoes. Indice invalido.");
		}
		switch (operacao.toLowerCase()) {

		case "total":
			resultado = String.format("R$%.2f", transacaoes.get(indice).getTotalGasto());
			break;
		case "nome":
			resultado = transacaoes.get(indice).getNomeCliente();	
			break;
			
		case("detalhes"):
			resultado = transacaoes.get(indice).getTransacao();
			break;
		default:
			break;
		}
		return resultado;
	}
	
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return controllerRestaurante.consultaRestaurante(nome, atributo);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		controllerRestaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	public Alimentacao buscaCardapio(String nome) {
		return controllerRestaurante.buscaCardapio(nome);
	}
	public boolean removeCardapio(Prato prato) {
		return controllerRestaurante.removeCardapio(prato);
	}
	public boolean cadastraPrato(String nome,  double preco ,String descricao) throws Exception {
		return controllerRestaurante.cadastraPrato(nome, preco, descricao);
	}
	public double compraPrato(Alimentacao prato) throws Exception {
		return controllerRestaurante.compraPrato(prato);
	}
	public void ordenaMenu(String tipoOrdenacao){
		controllerRestaurante.ordenaMenu(tipoOrdenacao);
	}
	
	public String consultaMenuRestaurante(){
		return controllerRestaurante.consultaMenuRestaurante();
	}
	
	/**
	 * Realiza um pedido no restaurante e retorna uma String com o valor do pedido.
	 * 
	 * @param String - id
	 * @param String - itemMenu
	 * @return String - valor do pedido
	 * @throws Exception
	 */
	public String realizaPedido(String id, String itemMenu) throws Exception{
		  String resultado = "";
		  double precoBruto = 0.0;
		  if(!(clientesCadastrados.containsKey(id))){
			  throw new Exception("Erro no realiza pedido. Nao contem este ");
		  }
		 
		  Hospede hospedeOperacao = clientesCadastrados.get(id);
		   precoBruto = controllerRestaurante.totalPedido(itemMenu);
		   hospedeOperacao.adicionaPontos(precoBruto);
		   double precoDesconto = hospedeOperacao.precoDesconto(precoBruto);
		   resultado += String.format("R$%.2f", precoDesconto);
		   ControleDeGastos gastoRestaurante = new TransacoesRestaurante(hospedeOperacao.getNomeHospede(), itemMenu, precoDesconto, 
				   LocalDate.now());
		   transacaoes.add(gastoRestaurante);
		  
		   hospedeOperacao.mudaFidelidade();
		  
		  return resultado;
		 }
	
	/**
	 * Retorna o lucro de todas as operacoes.
	 * 
	 * @return Double - valor total
	 */
	public double precoTotalOperacoes(){
		double valor = 0;
		for (ControleDeGastos controleDeGastos : transacaoes) {
			 valor += controleDeGastos.getTotalGasto();
		}
		return valor;
	}
	
	/**
	 * Converte pontos do cartao fidelidade em dinheiro.
	 * 
	 * @param String - email
	 * @param Int - pontos
	 * @return String - resultado
	 * @throws Exception
	 */
	public String convertePontos(String email, int pontos) throws Exception {
		
		Hospede hospedeOperecao = clientesCadastrados.get(email);
		String resultado = hospedeOperecao.convertePontos(pontos);
		hospedeOperecao.mudaFidelidade();
		return resultado;
		
	}
	
	
	public void fechaSistema() {
	}
}
