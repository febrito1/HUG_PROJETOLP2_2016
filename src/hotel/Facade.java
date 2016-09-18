package hotel;

import easyaccept.EasyAccept;
import excecoes.Excecoes;

public class Facade {
	
	private SistemaController controler;
	
	public Facade() {
		controler = new SistemaController();
	}

	public void iniciaSistema() {
		controler.iniciaSistema();
	}

	public String cadastraHospede(String nome, String email, String dataNascimento) throws Exception {
		
		Excecoes.CadastroInvalidoException(nome);
		
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

	
	public String getInfoHospedagem(String email, String atributo) throws Exception{
		
		return controler.getInfo(email, atributo);
	}
	
	
	public void fechaSistema() {
		controler.fechaSistema();
	}

	public static void main(String[] args) {
		args = new String[] {"hotel.Facade", "acceptance_test/teste_caso1"};
		EasyAccept.main(args);
	}
}
