package hotel;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banco.BancoDeDados;
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

public class SistemaController implements Serializable{

	private static final long serialVersionUID = 7905505016443154566L;
	private QuartosFactory factoryQuartos;
	private FactoryDeHospede factoryHospedes;
	private RestauranteController controllerRestaurante;
	private Map<String, Hospede> clientesCadastrados;
	private Map<String, Quarto> catalogoQuartos;
	private Map<String, Quarto> quartosOcupados;
	private Excecoes excecoes = new Excecoes();;
	private List<ControleDeGastos> transacaoes;
	private static final String FIM_DE_LINHA = System.lineSeparator();
	private BancoDeDados dados;
	private LocalDate dataNascimento;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public SistemaController() {
		
		controllerRestaurante = new RestauranteController();
		setTransacaoes(new ArrayList<>());
		quartosOcupados = new HashMap<>();
		catalogoQuartos = new HashMap<>();
		setClientesCadastrados(new HashMap<>());
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
		getClientesCadastrados().put(email, novoHospede);
		return email;
	}

	public String buscaHospede(String email) throws Exception {
		if (!getClientesCadastrados().containsKey(email)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		return email;
	}

	public void removeHospede(String email) throws Exception {
		if (!getClientesCadastrados().containsKey(email)) {
			throw new Exception("Erro na remocao do Hospede. Formato de email invalido.");
		}
		getClientesCadastrados().remove(email);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		if (!getClientesCadastrados().containsKey(id)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + id + " nao foi cadastrado(a).");
		}

		Hospede clienteatualizado = getClientesCadastrados().get(id);
		switch (atributo.toLowerCase()) {

		case ("nome"):
			excecoes.atualizaCadastroException(valor);
			clienteatualizado.setNomeHospede(valor);
			break;

		case ("email"):
			excecoes.atualizaEmailException(valor);

			clienteatualizado.setEmailHospede(valor);

			getClientesCadastrados().remove(id);
			getClientesCadastrados().put(valor, clienteatualizado);
			
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
	

	public String getInfo(String Info, String atributo) throws Exception {
				if (!(getClientesCadastrados().containsKey(Info))) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + Info + " nao foi cadastrado(a).");
		}

		String informacao = "";
		Hospede hospedeInfo = getClientesCadastrados().get(Info);
		
		
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
			System.out.println("inv�lido");
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
		
		
		if (!(getClientesCadastrados().containsKey(email))) {
			
			throw new Exception("Erro ao realizar checkin. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		
		Hospede hospede = getClientesCadastrados().get(email);

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

	public String getInfoHospedagem(String email, String atributo) throws Exception {
		
		excecoes.HospedagemAtivaException(email);

		if (!(getClientesCadastrados().containsKey(email))) {
			throw new Exception("Erro no cadastro de Hospede. Hospede nao cadastrado");
		}

		Hospede hospede = getClientesCadastrados().get(email);
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
		
		Hospede clienteOperacao = getClientesCadastrados().get(email);
		
		List<Estadia> estadias = clienteOperacao.getEstadias();
		
		for (Estadia estadia : estadias) {
			if(estadia.getQuarto().getID().equals(quarto)){
				
				double precoBruto = estadia.getQuarto().getPreco()*estadia.getQuantidadeDias();	
				
				clienteOperacao.adicionaPontos(precoBruto);
				double preco = clienteOperacao.precoDesconto(precoBruto);
				ControleDeGastos novoCheckout = new Checkout(clienteOperacao.getNomeHospede(), quarto,preco,LocalDate.now());	
				getTransacaoes().add(novoCheckout);
				
				resultado = String.format("R$%.2f",preco);
				clienteOperacao.removeEstadia(quarto);
				quartosOcupados.remove(quarto);
				
				clienteOperacao.mudaFidelidade();
				break;
			}
		
		}
		
		return resultado;
	}

	
	public String consultaTransacoes(String operacao) {

		String resultado = "";

		switch (operacao.toLowerCase()) {

		case "quantidade":

			resultado = Integer.toString(getTransacaoes().size());
			break;

		case "total":		
			resultado = String.format("R$%.2f", precoTotalOperacoes());
			break;

		case "nome":
			for (ControleDeGastos transacoes : getTransacaoes()) {
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
	

	public String consultaTransacoes(String operacao, int indice) throws Exception {

		String resultado = "";
		if (indice > getTransacaoes().size() || indice < 0) {
			throw new Exception("Erro na consulta de transacoes. Indice invalido.");
		}
		switch (operacao.toLowerCase()) {

		case "total":
			resultado = String.format("R$%.2f", getTransacaoes().get(indice).getTotalGasto());
			break;
		case "nome":
			resultado = getTransacaoes().get(indice).getNomeCliente();	
			break;
			
		case("detalhes"):
			resultado = getTransacaoes().get(indice).getTransacao();
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
	
	public String historicoRestaurante(){
		return controllerRestaurante.historicoRestaurante();
	}

	public String realizaPedido(String id, String itemMenu) throws Exception{
		  String resultado = "";
		  double precoBruto = 0.0;
		  if(!(getClientesCadastrados().containsKey(id))){
			  throw new Exception("Erro no realiza pedido. Nao contem este ");
		  }
		 
		  Hospede hospedeOperacao = getClientesCadastrados().get(id);
		   precoBruto = controllerRestaurante.totalPedido(itemMenu);
		   hospedeOperacao.adicionaPontos(precoBruto);
		   double precoDesconto = hospedeOperacao.precoDesconto(precoBruto);
		   resultado += String.format("R$%.2f", precoDesconto);
		   ControleDeGastos gastoRestaurante = new TransacoesRestaurante(hospedeOperacao.getNomeHospede(), itemMenu, precoDesconto, 
				   LocalDate.now());
		   getTransacaoes().add(gastoRestaurante);
		  
		   hospedeOperacao.mudaFidelidade();
		  
		  return resultado;
		 }
	
	
	public double precoTotalOperacoes(){
		double valor = 0;
		for (ControleDeGastos controleDeGastos : getTransacaoes()) {
			 valor += controleDeGastos.getTotalGasto();
		}
		return valor;	
	}
	
	public String convertePontos(String email, int pontos) throws Exception {
		
		Hospede hospedeOperecao = getClientesCadastrados().get(email);
		String resultado = hospedeOperecao.convertePontos(pontos);
		hospedeOperecao.mudaFidelidade();
		return resultado;
		
	}
	
	public String escreveRelatorioHospede(String id) throws Exception{
		return dados.historicoHospede(id);
	}
	
	public String historicoTransacoes(){
		return dados.historicoTransacoes();
		
	}
	
	public void escreveHistorico() throws Exception{
		dados.escreveHistorico();
	}
	
	public String historicoHospede(String id) throws Exception{
		return dados.historicoHospede(id);
	}
	
	public void escreveRelatorioRestaurante() throws Exception{
		dados.escreveRelatorioRestaurante();
	
	}
	/**
	 * Procura por um hospede cadastrado atraves do email.
	 * 
	 * @param String - email
	 * @return String - email
	 * @throws Exception
	 */
		
	
	public void fechaSistema() {
	}

	public Map<String, Hospede> getClientesCadastrados() {
		return clientesCadastrados;
	}

	public void setClientesCadastrados(Map<String, Hospede> clientesCadastrados) {
		this.clientesCadastrados = clientesCadastrados;
	}

	public List<ControleDeGastos> getTransacaoes() {
		return transacaoes;
	}

	public void setTransacaoes(List<ControleDeGastos> transacaoes) {
		this.transacaoes = transacaoes;
	}
}
