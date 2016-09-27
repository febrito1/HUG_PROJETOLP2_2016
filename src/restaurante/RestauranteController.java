package restaurante;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import excecoes.ExcecaoRestaurante;
import excecoes.excecoes;

public class RestauranteController implements Comparable<Alimentacao>{

	private HashSet<Alimentacao> cardapio;
	private List<RefeicaoCompleta> refeicoes;
	private RefeicaoFactory factoryRefeicao;
	private PratosFactory factoryPratos;
	private excecoes excecoes = new excecoes();

	public RestauranteController() {

		this.cardapio = new HashSet<Alimentacao>();
		this.refeicoes = new ArrayList<RefeicaoCompleta>();
		this.factoryPratos = new PratosFactory();
		this.factoryRefeicao = new RefeicaoFactory();

	}

	public void iniciaSistema() {

	}

	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception {

		ExcecaoRestaurante.CadastroInvalidoPrato(nome, descricao, preco);
		Alimentacao prato = factoryPratos.criaPrato(nome, preco, descricao);
		excecoes.verificaPrato(prato);
		return cardapio.add(prato);
	}

	public boolean removeCardapio(Alimentacao prato) {
		for (Alimentacao removeprato : cardapio) {
			if (removeprato.equals(prato)) {
				removeprato.equals(removeprato);
				return true;
			}
		}
		return false;
	}

	public boolean buscaPrato(Alimentacao prato) {
		return cardapio.contains(prato);
	}

	public Alimentacao buscaCardapio(String nome) {
		for (Alimentacao prato : cardapio) {
			if (nome.equalsIgnoreCase(prato.getNome())) {
				return prato;
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

			Alimentacao novoPrato = buscaCardapio(nome);
			totalPreco += novoPrato.getPreco();
			refeicao.adicionaPrato(prato);

		}
		
		refeicao.setPreco(totalPreco);
		cardapio.add(refeicao);
	}

	public String consultaRestaurante(String nome, String atributo) throws Exception {
		ExcecaoRestaurante.ConsultaRestauranteException(nome, atributo);
		String informacaoConsulta = "";
		if (buscaCardapio(nome) != null) {
			Alimentacao prato = buscaCardapio(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				double tado = prato.getPreco();
				informacaoConsulta = String.format("R$%.2f", tado);

			}
			if (atributo.equalsIgnoreCase("descricao")) {
				informacaoConsulta += prato.getDescricao();
			}
		}

		if (buscaRefeicao(nome) != null) {
			RefeicaoCompleta refeicao = buscaRefeicao(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				double i = refeicao.getPreco();
				informacaoConsulta = String.format("R$%.2f", i);
			}
			if (atributo.equalsIgnoreCase("descricao")) {
				return refeicao.informacaoRefeicao();
			}
		}
		return informacaoConsulta;
	}
	public int compareTo(Alimentacao){
		
	}

	public void fechaSistema() {

	}
}