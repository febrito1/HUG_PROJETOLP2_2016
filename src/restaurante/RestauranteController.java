package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excecoes.ExcecaoRestaurante;
import excecoes.Excecoes;

/**
 * Gerencia o restaurante usando das classes de Alimentacao.
 *
 */
public class RestauranteController {
	private String tipoOrdenacao = ""; 
	private List<Alimentacao> cardapio;
	private RefeicaoFactory factoryRefeicao;
	private PratosFactory factoryPratos;
	private Excecoes excecoes = new Excecoes();
	private ExcecaoRestaurante exRestaurante = new ExcecaoRestaurante();

	/**
	 * Inicia o controller.
	 */
	public RestauranteController() {

		this.cardapio = new ArrayList<Alimentacao>();
		this.factoryPratos = new PratosFactory();
		this.factoryRefeicao = new RefeicaoFactory();

	}
	
	public void iniciaSistema() {

	}

	/**
	 * Adiciona um novo prato no cardapio.
	 * 
	 * @param String - nome
	 * @param Double - preco
	 * @param String - descricao
	 * @return boolean - se conseguiu cadastrar, retorna true
	 * @throws CadastroInvalidoPrato, verificaPrato
	 */
	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception {

		exRestaurante.CadastroInvalidoPrato(nome, descricao, preco);
		Alimentacao prato = factoryPratos.criaPrato(nome, preco, descricao);
		excecoes.verificaPrato(prato);
		cardapio.add(prato);
		ordenaMenu(tipoOrdenacao);
		return true;
	}

	/**
	 * Remove um alimento do cardapio.
	 * 
	 * @param Alimentacao - prato
	 * @return boolean - true se remover e false caso contrario 
	 */
	public boolean removeCardapio(Alimentacao prato) {
		for (Alimentacao removeprato : cardapio) {
			if (removeprato.equals(prato)) {
				cardapio.remove(removeprato);
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna o valor de uma alimentacao com desconto.
	 * 
	 * @param Alimentacao - prato
	 * @return Double - preco
	 * @throws verificaPrato, Exception
	 */
	public double compraPrato(Alimentacao prato) throws Exception {
		excecoes.verificaPrato(prato);
		if (!(buscaPrato(prato))) {
			throw new Exception("N�o existe esse prato no cardapio.");
		}
		return prato.getPreco() - (prato.getPreco() * 0.1);
	}

	/**
	 * Retorna true se o alimento existir no cardapio e false caso contrario.
	 * 
	 * @param Alimentacao - prato
	 * @return boolean
	 */
	public boolean buscaPrato(Alimentacao prato) {
		return cardapio.contains(prato);
	}

	/**
	 * Retorna uma Alimentacao do cardapio.
	 * 
	 * @param String - nome
	 * @return Alimentacao - alimento
	 */
	public Alimentacao buscaCardapio(String nome) {
		for (Alimentacao alimento : cardapio) {
			if (nome.equalsIgnoreCase(alimento.getNome())) {
				return alimento;
			}
		}
		return null;
	}


	/**
	 * Adiciona uma nova refeicao ao cardapio.
	 * 
	 * @param String - nome
	 * @param String - descricao
	 * @param String - componentes
	 * @throws CadastroInvalidoRefeicao, Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		exRestaurante.CadastroInvalidoRefeicao(nome, descricao, componentes);
		double totalPreco = 0.0;
		String[] pratos = componentes.split(";");

		if (componentes == null || componentes.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		if (pratos.length < 3 || pratos.length > 4) {
			throw new Exception(
					"Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}

		RefeicaoCompleta refeicao = factoryRefeicao.criaRefeicao(nome, descricao);

		for (String prato : pratos) {
			if (buscaCardapio(prato) == null) {
				throw new Exception(
						"Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
			}

			Alimentacao novoPrato = buscaCardapio(prato);
			totalPreco += novoPrato.getPreco();
			refeicao.adicionaPrato(prato);

		}

		refeicao.setPreco(totalPreco);
		cardapio.add(refeicao);
		ordenaMenu(tipoOrdenacao);
	}

	/**
	 * Consulta o preco ou descricao de um alimento do cardapio.
	 * 
	 * @param String - nome
	 * @param String - atributo 
	 * @return String - informacao da consulta
	 * @throws ConsultaRestauranteException
	 */
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		exRestaurante.ConsultaRestauranteException(nome, atributo);
		String informacaoConsulta = "";
		if (buscaCardapio(nome) != null) {
			Alimentacao novoAlimento = buscaCardapio(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				double resultado = novoAlimento.getPreco();
				informacaoConsulta = String.format("R$%.2f", resultado);
			}
			if (atributo.equalsIgnoreCase("descricao")) {
				informacaoConsulta += novoAlimento.informacaoDescricao();
			}
		}
		return informacaoConsulta;
	}

	/**
	 * Ordena o cardapio atraves do nome ou do preco.
	 *
	 * @param String - tipoOrdenacao
	 */
	public void ordenaMenu(String tipoOrdenacao) {
		switch (tipoOrdenacao.toLowerCase()) {
		case ("nome"):
			Collections.sort(this.cardapio, new OrdenaAlfabeto());
			this.tipoOrdenacao = "nome";
			break;
		case ("preco"):
			this.tipoOrdenacao = "preco";
			Collections.sort(cardapio, new OrdenaValor());
			break;
		}
	}

	/**
	 * Retorna uma String contendo todos os itens do cardapio.
	 * 
	 * @return String - itens
	 */
	public String consultaMenuRestaurante() {
		String ordenacao = "";
		for (int i = 0; i < this.cardapio.size(); i++) {
			if (!(i == cardapio.size() - 1)) {
				ordenacao += cardapio.get(i).getNome() + ";";
			} else {
				ordenacao += cardapio.get(i).getNome();
			}
		}
		return ordenacao;
	}

	/**
	 * Retorna o valor de um item do menu.
	 * 
	 * @param String itemMenu
	 * @return Double - preco total
	 * @throws Exception
	 */
	public double totalPedido(String itemMenu) throws Exception{
		double totalPreco = 0;
		
		for (Alimentacao alimento : cardapio) {
			if (itemMenu.equalsIgnoreCase(alimento.getNome())) {
				totalPreco += alimento.getPreco();
			}
		}
		
		if(totalPreco == 0){
			throw new Exception("Nao contem este item nesse cardapio.");
		}
		
		return totalPreco;
	}

	public void fechaSistema() {

	}
}