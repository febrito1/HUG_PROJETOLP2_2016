package hotel;

import easyaccept.EasyAccept;

public class Facade {
	
	private SistemaController controler;
	
	public Facade() {
		controler = new SistemaController();
	}

	public void iniciaSistema() {
		controler.iniciaSistema();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception {
		
		return controler.cadastraHospede(nome, email, dataNascimento);
	}

	public String getInfoHospede(String id, String atributo) throws Exception {
		return controler.getInfoHospede(id, atributo);
	}

	public void atualizaCadastro(String id, String atributo, String valor) throws Exception {
		
		controler.atualizaCadastro(id, atributo, valor);
		
	}

	public void removeHospede(String email) throws Exception {
		controler.removeHospede(email);
	}
	
	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws Exception{
		controler.realizaCheckin(email, dias, idQuarto, tipoQuarto);
	}

	
	public String getInfoHospedagem(String email, String atributo) throws Exception{
		
		return controler.getInfoHospedagem(email, atributo);
	}
	
	public String realizaCheckout(String email, String quarto) throws Exception{
		return controler.realizaCheckout(email, quarto);
	}
	
	public String consultaTransacoes(String atributo){
		return controler.consultaTransacoes(atributo);
	}

	public String consultaTransacoes(String atributo, int indice) throws Exception{
		return controler.consultaTransacoes(atributo, indice);
	}
	
	
	public void fechaSistema() {
		controler.fechaSistema();
	}

	public static void main(String[] args) {
		args = new String[] {"hotel.Facade", "acceptance_test/teste_uc1", "acceptance_test/testes_uc1_exception"};
		EasyAccept.main(args);
	}
}
