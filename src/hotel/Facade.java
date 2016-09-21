package sistema;

import easyaccept.EasyAccept;
import excecoes.Excecoes;
import restaurante.Prato;
import restaurante.RefeicaoCompleta;
import restaurante.RestauranteController;

public class Facade {

	private SistemaController controller;

	public Facade() {
		controller = new SistemaController();

	}

	public void iniciaSistema() {
		controller.iniciaSistema();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception {
		return controller.cadastraHospede(nome, email, dataNascimento);
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		return controller.getInfo(id, atributo);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		controller.atualizaCadastro(id, atributo, valor);

	}

	public void removeHospede(String email) throws Exception {
		controller.removeHospede(email);
	}

	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws Exception {
		controller.realizaCheckin(email, dias, idQuarto, tipoQuarto);
	}

	public String getInfoHospedagem(String email, String atributo) throws Exception {

		return controller.getInfo(email, atributo);
	}

	public String realizaCheckout(String email, String quarto) throws Exception {
		return controller.realizaCheckout(email, quarto);
	}

	public String consultaTransacoes(String atributo) {
		return controller.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice) throws Exception {
		return controller.consultaTransacoes(atributo, indice);
	}

	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return controller.consultaRestaurante(nome, atributo);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		controller.cadastraRefeicao(nome, descricao, componentes);
	}

	public RefeicaoCompleta buscaRefeicao(String nome) {
		return controller.buscaRefeicao(nome);
	}

	public Prato buscaCardapio(String nome) {
		return controller.buscaCardapio(nome);
	}

	public boolean removeCardapio(Prato prato) {
		return controller.removeCardapio(prato);
	}

	public boolean cadastraPrato(String nome, double preco, String descricao) throws Exception {
		return controller.cadastraPrato(nome, preco, descricao);
	}

	public double compraPrato(Prato prato) throws Exception {
		return controller.compraPrato(prato);
	}

	public void fechaSistema() {
		controller.fechaSistema();
	}

	public static void main(String[] args) {
		args = new String[] { "sistema.Facade", "testes/testes_uc3.txt" };
		EasyAccept.main(args);
	}

}
