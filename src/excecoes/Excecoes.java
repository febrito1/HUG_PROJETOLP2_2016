package excecoes;

import java.util.ArrayList;
import java.util.HashSet;

import hotel.Hospede;
import restaurante.Prato;

public class Excecoes {
	
	public Excecoes(){
		
	}
	
	public static void StringException(String excecao) throws Exception {
		if(excecao == null || excecao.trim().isEmpty()){
			throw new Exception("String nao pode ser nula ou vazia");
		}
	}
	
	public static void inteiroException(int numInteiro) throws Exception {
		if(numInteiro <= 0){
			throw new Exception("Numero nao pode ser negativo ou zero.");
		}
	}

	public static void CadastroInvalidoException(String nome) throws Exception {
		if (nome == null || nome.trim().equals("")) {
			throw new Exception("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		}
		if (nome.contains("@")) {
			throw new Exception("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
		}
	}


	public static void EmailInvalidoException(String email) throws Exception {
		if (email == null || email.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.");
		}
		if (!(email.contains("@")) || !(email.contains("."))) {
			throw new Exception("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.");
		}
		
		
	}

	public static void DatadeNascimentoVazia(String data) throws Exception {
		if (data == null || data.trim().isEmpty()) {
			throw new Exception("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		}
		if (!(data.contains("/")) || data.length() != 10) {
			throw new Exception("Erro no cadastro de Hospede. Formato de data invalido.");
		}

	}

	
	
	public static void doubleException(double numDouble) throws Exception {
		if(numDouble < 0){
			throw new Exception("Numero nao pode ser negativo.");
		}
	}
	
	public static void verificaTamanhoArray(ArrayList<Prato> listaPrato) throws Exception {
		if(listaPrato.size() < 3){
			throw new Exception("A lista de pratos nao pode ser menor que 3");
		}
		if(listaPrato.size() < 4){
			throw new Exception("A lista de pratos nao pode ser menor que 4");
		}
	}
	
	public static void verificaPrato(Prato prato) throws Exception {
		
		if(prato == null){
			throw new Exception("Prato n�o pode ser nulo.");
		}
	
		
	}
	public static void verificaCadastro(Hospede hospede) throws Exception {
		if(hospede == null){
			throw new Exception("Hospede n�o pode ser nulo.");
		}
	}
	
	public static void tipoInvalido(String tipoQuarto) throws Exception {
		if(!(tipoQuarto.equalsIgnoreCase("luxo") || tipoQuarto.equalsIgnoreCase("simples") || tipoQuarto.equalsIgnoreCase("presidencial"))){
			throw new Exception("Erro ao realizar checkin. Tipo de quarto invalido.");
		}
	}
	
	
}
