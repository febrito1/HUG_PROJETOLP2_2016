package fidelidade;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FidelidadePremium implements CartaoFidelidade {

	private int pontos;

	public FidelidadePremium(int pontos) {
		this.pontos = pontos;
	}

	@Override
	public int getPontos() {

		return pontos;
	}

	@Override
	public void addPontos(double preco) {

		double pontosExtras = ((int)(preco/100)) * 10;
		this.pontos += (int) (preco * 0.3);
		this.pontos += pontosExtras;

	}

	
	@Override
	public double desconto(double preco) {
		double desconto = preco * 0.90;
		BigDecimal decimal = new BigDecimal(desconto).setScale(2, RoundingMode.CEILING);
		return decimal.doubleValue();

	}
	
	@Override
	public String convertePontos(int pontos) throws Exception {

		double convertePontos = pontos * 0.3;
		convertePontos += ((int) (pontos / 10)) * 0.20;
		
		removePontos(pontos);
		
		String resultado = String.format("R$%.2f", convertePontos);
		return resultado;
		
	}
	
	
	private void removePontos(int pontos) throws Exception {
		if(pontos > getPontos()) {
			throw new Exception("FUNCIONA MERDA.");
		}	
		this.pontos = getPontos() - pontos;
	
	}

}
