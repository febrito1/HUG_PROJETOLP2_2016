package hotel;

import java.util.List;

import easyaccept.EasyAccept;
import excecoes.Excecoes;
import restaurante.Alimentacao;
import restaurante.Prato;
import restaurante.RefeicaoCompleta;
import restaurante.RestauranteController;

public class Facade {

	private SistemaController controler;
	private RestauranteController controlerRestaurante;

	public Facade() {
		controler = new SistemaController();
		controlerRestaurante = new RestauranteController();
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
	public String getInfoHospedagem(String email, String atributo) throws Exception{
		return controler.getInfoHospedagem(email, atributo);
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

	public Alimentacao buscaCardapio(String nome) {
		return controlerRestaurante.buscaCardapio(nome);
	}
	public boolean removeCardapio(Prato prato) {
		return controlerRestaurante.removeCardapio(prato);
	}
	public boolean cadastraPrato(String nome,  double preco ,String descricao) throws Exception {
		return controlerRestaurante.cadastraPrato(nome, preco, descricao);
	}
	public double compraPrato(Alimentacao prato) throws Exception {
		return controlerRestaurante.compraPrato(prato);
	}
	public void ordenaMenu(String tipoOrdenacao){
		controlerRestaurante.ordenaMenu(tipoOrdenacao);
	}
	
	public String consultaMenuRestaurante(){
		return controlerRestaurante.consultaMenuRestaurante();
	}
	
	public String realizaPedido(String id, String itemMenu){
		return controler.realizaPedido(id, itemMenu);
	}
	
	public void fechaSistema() {
		controler.fechaSistema();
	}
	public static void main(String[] args) {
		args = new String[] { "hotel.Facade", "acceptance_hotel/test_uc1","acceptance_hotel/test_uc2","acceptance_hotel/teste_uc2_exception","acceptance_hotel/teste_uc3","acceptance_hotel/teste_uc3_exception", "acceptance_hotel/teste_uc4", "acceptance_hotel/teste_uc4_exception", "acceptance_hotel/testes_uc5.txt"};
		EasyAccept.main(args);
	}

}
