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

		if (preco > 100) {
			this.pontos += (int) (preco / 10);
		}
		this.pontos += (int) (preco * 0.3);

	}

	
	@Override
	public double desconto(double preco) {
		double desconto = preco * 0.90;
		BigDecimal decimal = new BigDecimal(desconto).setScale(2, RoundingMode.CEILING);
		return decimal.doubleValue();

	}

	@Override
	public String convertePontos(int pontos) throws Exception {

		if (getPontos() < pontos) {
			throw new Exception("mensagme de exception");
		}

		if (pontos < 0) {
			throw new Exception("mensagem de exception");
		}

		double convertePontos = pontos * 0.3;
		convertePontos += ((int) (pontos / 10)) * 0.20;
		String resultado = String.format("R$%.2f", convertePontos);
		return resultado;
		
	}

}
