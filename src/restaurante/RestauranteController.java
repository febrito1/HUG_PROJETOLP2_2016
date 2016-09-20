package restaurante;

import java.io.LineNumberInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excecoes.ConsultaHospedagemException;
import excecoes.Excecoes;

public class RestauranteController {

	private HashSet<Prato> cardapio;
	private List<RefeicaoCompleta> refeicoes;
	private RefeicaoFactory factoryRefeicao;
	private PratosFactory factoryPratos;
	public RestauranteController() {

		this.cardapio = new HashSet<Prato>();

	}

	public void iniciaSistema() {

	}

	public boolean cadastraPrato(String nome,  double preco ,String descricao) throws Exception {
		ExcecaoRestaurante.CadastroInvalidoPrato(nome, descricao, preco);
		Prato prato = factoryPratos.criaPrato(nome, preco, descricao);
		Excecoes.verificaPrato(prato);
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
		Excecoes.verificaPrato(prato);
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
		if (pratos.length < 3 || pratos.length > 4) {
			throw new Exception("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		for (String prato : pratos) {
			if (buscaCardapio(prato) == null) {
				throw new Exception("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
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
		String resultado = "";
		if (buscaCardapio(nome) != null) {
			Prato prato = buscaCardapio(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				resultado += "R$ " + prato.getPrecoPrato();
			}
			if (atributo.equalsIgnoreCase("descricao")) {
				resultado += prato.getDescricaoPrato();
			}
		}

		if (buscaRefeicao(nome) != null) {
			RefeicaoCompleta refeicao = buscaRefeicao(nome);
			if (atributo.equalsIgnoreCase("preco")) {
				resultado += "R$" + refeicao.calculaPrecoTotal();
			}
			if (atributo.equalsIgnoreCase("descricao")) {
				resultado += refeicao.getDescricaoRefeicao() + "Serao servidos: ";
				for (int i = 0; i < refeicoes.size(); i++) {
					if (i == refeicoes.size() - 1) {
						resultado += "(" + (i + 1) + ")" + refeicoes.get(i).getNomeRefeicao() + ".";
					} else {
						resultado += "(" + (i + 1) + ")" + refeicoes.get(i).getNomeRefeicao() + ", ";
					}
				}

			}
		}
		return resultado;
	}

	public void fechaSistema() {

	}
}