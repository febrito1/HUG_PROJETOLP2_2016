package excecoes;

import java.util.ArrayList;

import cliente.Hospede;
import restaurante.Prato;

public class excecoes {

	public excecoes() {}

	public void StringException(String excecao) throws Exception {
		if (excecao == null || excecao.trim().isEmpty()) {
			throw new Exception("String nao pode ser nula ou vazia");
		}
	}

	public void inteiroException(int numInteiro) throws Exception {
		if (numInteiro <= 0) {
			throw new Exception("Numero nao pode ser negativo ou zero.");
		}
	}

	public void EmailInvalidoException(String email) throws Exception {
		if (email == null || email.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if (!(email.contains("@")) || !(email.contains("."))) {
			throw new Exception("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}

	}

	public void doubleException(double numDouble) throws Exception {
		if (numDouble < 0) {
			throw new Exception("Numero nao pode ser negativo.");
		}
	}

	public void verificaTamanhoArray(ArrayList<Prato> listaPrato) throws Exception {
		if (listaPrato.size() < 3) {
			throw new Exception("A lista de pratos nao pode ser menor que 3");
		}
		if (listaPrato.size() < 4) {
			throw new Exception("A lista de pratos nao pode ser menor que 4");
		}
	}

	public void verificaPrato(Prato prato) throws Exception {

		if (prato == null) {
			throw new Exception("Prato nÃ£o pode ser nulo.");
		}

	}

	public void verificaCadastro(Hospede hospede) throws Exception {
		if (hospede == null) {
			throw new Exception("Hospede nÃ£o pode ser nulo.");
		}
	}

	public void CadastroInvalidoException(String nome) throws Exception {
		if (nome == null || nome.trim().equals("")) {
			throw new Exception("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (nome.contains("@")) {
			throw new Exception("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
	}

	public void DatadeNascimentoVazia(String data) throws Exception {
		if (data == null || data.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if (!(data.contains("/")) || data.length() != 10) {
			throw new Exception("Erro no cadastro de Hospede. Formato de data invalido.");
		}
	}

	public void atualizaCadastroException(String valor) throws Exception {
		if (valor == null || valor.trim().isEmpty()) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (valor.contains("@")) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
	}

	public void atualizaEmailException(String email) throws Exception {
		if (email == null || email.trim().isEmpty()) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		if (!(email.contains("@")) || !(email.contains("."))) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
	}

	public void atualizaDataException(String data) throws Exception {
		if (data == null || data.trim().isEmpty()) {
			throw new Exception(
					"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if (!(data.contains("/")) || data.length() != 10) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
		}

		String[] datas = data.split("/");
		int dias = Integer.parseInt(datas[0]);
		int mes = Integer.parseInt(datas[1]);
		if (dias > 31) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
		}
		if (mes > 12) {
			throw new Exception("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.");
		}
	}

	public void tipoInvalido(String tipoQuarto) throws Exception {
		if (!(tipoQuarto.equalsIgnoreCase("luxo") || tipoQuarto.equalsIgnoreCase("simples")
				|| tipoQuarto.equalsIgnoreCase("presidencial"))) {
			throw new Exception("Erro ao realizar checkin. Tipo de quarto invalido.");
		}

	}

	public void checkinEmailException(String email) throws Exception {
		if ((email == null || email.trim().isEmpty())) {
			throw new Exception("Erro ao realizar checkin. Email do(a) hospede nao pode ser vazio.");
		} else if (!(email.matches(("[A-Za-z]+@[A-Za-z]+\\.[A-Za-z|a-zA-Z\\.a-zA-Z]+")))) {
			throw new CheckinException("Email do(a) hospede esta invalido.");
		}

	}

	public void checkinDataInvalida(int dias) throws Exception {
		if (dias <= 0) {
			throw new CheckinException("Quantidade de dias esta invalida.");
		}
	}

	public void checkoutEmailException(String email) throws Exception {
		if ((email == null || email.trim().isEmpty())) {
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede nao pode ser vazio.");
		} else if (!(email.matches(("[A-Za-z]+@[A-Za-z]+\\.[A-Za-z|a-zA-Z\\.a-zA-Z]+")))) {
			throw new Exception("Erro ao realizar checkout. Email do(a) hospede esta invalido.");
		}

	}

	public void checkinIDException(String ID) throws Exception {

		if (ID.trim().isEmpty()) {
			throw new CheckinException("ID do quarto invalido, use apenas numeros ou letras.");
		}

		for (int i = 0; i < ID.length(); i++) {
			char letra = ID.charAt(i);
			if (!(Character.isLetter(letra) || Character.isDigit(letra))) {
				throw new CheckinException("ID do quarto invalido, use apenas numeros ou letras.");
			}
		}
	}

	public void HospedagemAtivaException(String email) throws Exception {
		if (email.trim().isEmpty()) {
			throw new HospedagemAtivaException("Email do(a) hospede nao pode ser vazio.");
		} else if (!(email.matches(("[A-Za-z]+@[A-Za-z]+\\.[A-Za-z|a-zA-Z\\.a-zA-Z]+")))) {
			throw new HospedagemAtivaException("Email do(a) hospede esta invalido.");
		}
	}
}
