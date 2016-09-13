package excecoes;

import java.util.HashSet;
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

	
	public static void doubleException(double numDouble) throws Exception {
		if(numDouble < 0){
			throw new Exception("Numero nao pode ser negativo.");
		}
	}
	
	public static void verificaTamanhoSet(HashSet<Prato> listaPrato) throws Exception {
		if(listaPrato.size() < 3){
			throw new Exception("A lista de pratos nao pode ser menor que 3");
		}
		if(listaPrato.size() < 4){
			throw new Exception("A lista de pratos nao pode ser menor que 4");
		}
	}
	
	public static void verificaPrato(Prato prato) throws Exception{
		
		if(prato == null){
			throw new Exception("Prato não pode ser nulo.");
		}
		
	}
	
}
