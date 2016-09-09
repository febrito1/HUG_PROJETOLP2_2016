package hotel;

import excecoes.*;
import quarto.*;

public class Teste {

	public static void main(String[] args) {
		
		try{
			Hospede novoHospede = new Hospede("Joao", "CAD", 1993);
			System.out.println(novoHospede.getAnoNascimento());
			System.out.println(novoHospede.getNomeHospede());		
			System.out.println(novoHospede.getEmailHospede());
			
			Quarto novoQuardo = new QuartoPresidencial("");
			System.out.println(novoQuardo.getID());
			System.out.println(novoQuardo.getValorDiaria());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
}
