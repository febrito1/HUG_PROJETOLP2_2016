package testes;


import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Testes {
	
	private static DecimalFormat decimal = new DecimalFormat("0.00");
	
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(convertePontos(50));
		 
	}
	
	public static String convertePontos(int pontos)  {	
		
		
		double convertePontos = pontos * 0.7;	
		convertePontos += ((int)(pontos/10)) * 0.5;	

		
		String resultado = String.format("R$%.2f", convertePontos);
			
		 return resultado;
	}
}
