package hotel;

import easyaccept.EasyAccept;
import restaurante.Alimentacao;
import restaurante.Prato;
import restaurante.RefeicaoCompleta;

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
	
	public String convertePontos(String email, int pontos) throws Exception{
		return controller.convertePontos(email, pontos);
	}

	public void removeHospede(String email) throws Exception {
		controller.removeHospede(email);
	}

	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws Exception {
		controller.realizaCheckin(email, dias, idQuarto, tipoQuarto);
	}
	public String getInfoHospedagem(String email, String atributo) throws Exception{
		return controller.getInfoHospedagem(email, atributo);
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

	public Alimentacao buscaCardapio(String nome) {
		return controller.buscaCardapio(nome);
	}
	public boolean removeCardapio(Prato prato) {
		return controller.removeCardapio(prato);
	}
	public boolean cadastraPrato(String nome,  double preco ,String descricao) throws Exception {
		return controller.cadastraPrato(nome, preco, descricao);
	}
	public double compraPrato(Alimentacao prato) throws Exception {
		return controller.compraPrato(prato);
	}
	public void ordenaMenu(String tipoOrdenacao){
		controller.ordenaMenu(tipoOrdenacao);
	}
	
	public String consultaMenuRestaurante(){
		return controller.consultaMenuRestaurante();
	}
	
	public String realizaPedido(String id, String itemMenu) throws Exception{
		return controller.realizaPedido(id, itemMenu);
	}
	
	public void fechaSistema() {
		controller.fechaSistema();
	}
	public static void main(String[] args) {
		args = new String[] { "hotel.Facade", "acceptance_hotel/test_uc1","acceptance_hotel/test_uc2","acceptance_hotel/teste_uc2_exception","acceptance_hotel/teste_uc3","acceptance_hotel/teste_uc4","acceptance_hotel/teste_uc4_exception","acceptance_hotel/testes_uc5.txt","acceptance_hotel/testes_uc6.txt","acceptance_hotel/testes_uc7.txt","acceptance_hotel/test_uc1_exception","acceptance_hotel/teste_uc3_exception"};
		EasyAccept.main(args);
	}

}
