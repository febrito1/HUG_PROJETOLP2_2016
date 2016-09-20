package hotel;

import easyaccept.EasyAccept;
import excecoes.*;

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
		Excecoes.EmailInvalidoException(email);
		Excecoes.DatadeNascimentoVazia(dataNascimento);
		
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

	
	public String getInfoHospedagem(String email, String atributo) throws Exception{
		
		return controler.getInfoHospedagem(email, atributo);
	}
	
	
	public void fechaSistema() {
		controler.fechaSistema();
	}

	public static void main(String[] args) {
		args = new String[] {"hotel.Facade", "acceptance_test/teste_caso1_exp"};
		EasyAccept.main(args);
	}
}