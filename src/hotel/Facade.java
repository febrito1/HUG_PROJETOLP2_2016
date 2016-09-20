package sistema;

import easyaccept.EasyAccept;
import excecoes.Excecoes;
import restaurante.Prato;
import restaurante.RefeicaoCompleta;
import restaurante.RestauranteController;

public class Facade {

	private SistemaController controler;
	private RestauranteController controlerRestaurante;

	public Facade() {
		controler = new SistemaController();
		controlerRestaurante =  new RestauranteController();

	}

	public void iniciaSistema() {
		controler.iniciaSistema();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception {
		return controler.cadastraHospede(nome, email, dataNascimento);
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		return controler.getInfo(id, atributo);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		controler.atualizaCadastro(id, atributo, valor);

	}

	public void removeHospede(String email) throws Exception {
		controler.removeHospede(email);
	}

	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws Exception {
		controler.realizaCheckin(email, dias, idQuarto, tipoQuarto);
	}

	public String getInfoHospedagem(String email, String atributo) throws Exception {

		return controler.getInfo(email, atributo);
	}

	public String realizaCheckout(String email, String quarto) throws Exception {
		return controler.realizaCheckout(email, quarto);
	}

	public String consultaTransacoes(String atributo) {
		return controler.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice) throws Exception {
		return controler.consultaTransacoes(atributo, indice);
	}

	
	public String consultaRestaurante(String nome, String atributo) throws Exception {
		return controlerRestaurante.consultaRestaurante(nome, atributo);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		controlerRestaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	public RefeicaoCompleta buscaRefeicao(String nome) {
		return controlerRestaurante.buscaRefeicao(nome);
	}

	public Prato buscaCardapio(String nome) {
		return controlerRestaurante.buscaCardapio(nome);
	}
	public boolean removeCardapio(Prato prato) {
		return controlerRestaurante.removeCardapio(prato);
	}
	public boolean cadastraPrato(String nome,  double preco ,String descricao) throws Exception {
		return controlerRestaurante.cadastraPrato(nome, preco, descricao);
	}
	public double compraPrato(Prato prato) throws Exception {
		return controlerRestaurante.compraPrato(prato);
	}
	
	public void fechaSistema() {
		controler.fechaSistema();
	}
	public static void main(String[] args) {
		args = new String[] { "sistema.Facade", "testes/testes_uc4_exception.txt" };
		EasyAccept.main(args);
	}

}
