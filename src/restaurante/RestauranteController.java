package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excecoes.ExcecaoRestaurante;
import excecoes.Excecoes;

public class RestauranteController {
	private String tipoOrdenação = ""; 
	private List<Alimentacao> cardapio;
	private List<RefeicaoCompleta> refeicoes;
	private RefeicaoFactory factoryRefeicao;
	private PratosFactory factoryPratos;
	private Excecoes excecoes = new Excecoes();

	public RestauranteController() {

		this.cardapio = new ArrayList<Alimentacao>();
		this.factoryPratos = new PratosFactory();
		this.factoryRefeicao = new RefeicaoFactory();

	}

	public void iniciaSistema() {

	}

	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception {

		ExcecaoRestaurante.CadastroInvalidoPrato(nome, descricao, preco);
		Alimentacao prato = factoryPratos.criaPrato(nome, preco, descricao);
		excecoes.verificaPrato(prato);
		cardapio.add(prato);
		ordenaMenu(tipoOrdenação);
		return true;
	}

	public boolean removeCardapio(Alimentacao prato) {
		for (Alimentacao removeprato : cardapio) {
			if (removeprato.equals(prato)) {
				cardapio.remove(removeprato);
				return true;
			}
		}
		return false;
	}

	public double compraPrato(Alimentacao prato) throws Exception {
		excecoes.verificaPrato(prato);
		if (!(buscaPrato(prato))) {
			throw new Exception("Não existe esse prato no cardapio.");
		}
		return prato.getPreco() - (prato.getPreco() * 0.1);
	}

	public boolean buscaPrato(Alimentacao prato) {
		return cardapio.contains(prato);
	}

	public Alimentacao buscaCardapio(String nome) {
		for (Alimentacao alimento : cardapio) {
			if (nome.equalsIgnoreCase(alimento.getNome())) {
				return alimento;
			}
		}
		return null;
	}

	public RefeicaoCompleta buscaRefeicao(String nome) {
		for (RefeicaoCompleta refeicao : refeicoes) {
			if (nome.equalsIgnoreCase(refeicao.getNome())) {
				return refeicao;
			}
		}
		return null;
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		ExcecaoRestaurante.CadastroInvalidoRefeicao(nome, descricao, componentes);
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
		ordenaMenu(tipoOrdenação);
	}

	public String consultaRestaurante(String nome, String atributo) throws Exception {
		ExcecaoRestaurante.ConsultaRestauranteException(nome, atributo);
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

	public void ordenaMenu(String tipoOrdenacao) {
		switch (tipoOrdenacao.toLowerCase()) {
		case ("nome"):
			Collections.sort(this.cardapio, new OrdenaAlfabeto());
			this.tipoOrdenação = "nome";
			break;
		case ("preco"):
			this.tipoOrdenação = "preco";
			Collections.sort(cardapio, new OrdenaValor());
			break;
		}
	}

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

	public double totalPedido(String itemMenu) {
		double totalPreco = 0;
		for (Alimentacao alimento : cardapio) {
			if (itemMenu.equalsIgnoreCase(alimento.getNome())) {
				totalPreco += alimento.getPreco();
			}
		}
		return totalPreco;
	}

	public void fechaSistema() {

	}
}