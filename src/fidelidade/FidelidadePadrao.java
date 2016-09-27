package fidelidade;

public class FidelidadePadrao implements CartaoFidelidade {

	private int pontos = 0;

	
	@Override
	public int getPontos() {
		return pontos;
	}

	@Override
	public void addPontos(double preco) {
		this.pontos += (int) (preco * 0.9);
	}

	@Override
	public double convertePontos(int pontos) {
		return pontos * 0.1;
	}

}
