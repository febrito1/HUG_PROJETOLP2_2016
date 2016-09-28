package fidelidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FidelidadeVIP implements CartaoFidelidade {

	
	
	private int pontos = 0;
	
	public FidelidadeVIP(){
		
	
	}
	
	
	@Override
	public int getPontos() {
		return pontos;
	}

	@Override
	public void addPontos(double preco) {
		pontos += (int) preco * 0.5;
		
	}

	@Override
	public double desconto(double preco) {
		
		double pontos = preco * 0.85;	
		pontos += ((int)(pontos/100)) * 10;
		BigDecimal decimal = new BigDecimal(pontos).setScale(2, RoundingMode.CEILING);
		return decimal.doubleValue();
		
	}
	
	
	@Override
	public String convertePontos(int pontos) throws Exception {	
		if(pontos < 0) {
			throw new Exception("mensagem do exception.");
		}
		if(getPontos() < pontos){
			throw new Exception("mensagem do exception.");
		}	
		
		double convertePontos = pontos * 0.7;	
		convertePontos += ((int)(pontos/10)) * 0.5;		
		String resultado = String.format("R$%.2f", convertePontos);	
		 return resultado;
	}
	
}
