package restaurante;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import excecoes.ExcecaoRestaurante;
import excecoes.Excecoes;

public class RestauranteController {

	private HashSet<Prato> cardapio;
	private List<RefeicaoCompleta> refeicoes;
	private RefeicaoFactory factoryRefeicao;
	private PratosFactory factoryPratos;
	private Excecoes excecoes = new Excecoes();

	public RestauranteController() {

		this.cardapio = new HashSet<Prato>();
		this.refeicoes = new ArrayList<RefeicaoCompleta>();
		this.factoryPratos = new PratosFactory();
		this.factoryRefeicao = new RefeicaoFactory();

	}

	public void iniciaSistema(){
	}

	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception {
		
		ExcecaoRestaurante.CadastroInvalidoPrato(nome, descricao, preco);
		Prato prato = factoryPratos.criaPrato(nome, preco, descricao);
		excecoes.verificaPrato(prato);
		return cardapio.add(prato);
	}

	public boolean removeCardapio(Prato prato) {
		for (Prato removeprato : cardapio) {
			if (removeprato.equals(prato)) {
				removeprato.equals(removeprato);
				return true;
			}
		}
		return false;
	}

	public double compraPrato(Prato prato) throws Exception {
		excecoes.verificaPrato(prato);
		if (!(buscaPrato(prato))) {
			throw new Exception("Não existe esse prato no cardapio.");
		}
		return prato.getPrecoPrato() - (prato.getPrecoPrato() * 0.1);
	}

	public boolean buscaPrato(Prato prato) {
		return cardapio.contains(prato);
	}

	public Prato buscaCardapio(String nome) {
		for (Prato prato : cardapio) {
			if (nome.equalsIgnoreCase(prato.getNomePrato())) {
				return prato;
			}
		}
		return null;
	}

	public RefeicaoCompleta buscaRefeicao(String nome) {
		for (RefeicaoCompleta refeicao : refeicoes) {
			if (nome.equalsIgnoreCase(refeicao.getNomeRefeicao())) {
				return refeicao;
			}
		}
		return null;
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		ExcecaoRestaurante.CadastroInvalidoRefeicao(nome, descricao, componentes);
		String[] pratos = componentes.split(";");
		if(componentes == null || componentes.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		if (pratos.length < 3 || pratos.length > 4) {
			throw new Exception(
					"Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		for (String prato : pratos) {
			if (buscaCardapio(prato) == null) {
				throw new Exception(
						"Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
			}

		}

		RefeicaoCompleta refeicao = factoryRefeicao.criaRefeicao(nome, descricao);

		refeicoes.add(refeicao);
		for (String outroPrato : pratos) {
			refeicao.adicionaPrato(buscaCardapio(outroPrato));
		}
	}
			
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		ExcecaoRestaurante.ConsultaRestauranteException(nome, atributo);
		String informacaoConsulta = "";
		if (buscaCardapio(nome) != null) {
			Prato prato = buscaCardapio(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				double tado = prato.getPrecoPrato();
				informacaoConsulta = String.format("R$%.2f", tado);
				
				
			}
			if (atributo.equalsIgnoreCase("descricao")) {
				informacaoConsulta += prato.getDescricaoPrato();
			}
		}

		if (buscaRefeicao(nome) != null) {
			RefeicaoCompleta refeicao = buscaRefeicao(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				double i = refeicao.calculaPrecoTotal();
				informacaoConsulta = String.format("R$%.2f", i);
			}
			if (atributo.equalsIgnoreCase("descricao")) {
				return refeicao.informacaoRefeicao();
			}
		}
		return informacaoConsulta;
	}

	public void fechaSistema() {

	}
}