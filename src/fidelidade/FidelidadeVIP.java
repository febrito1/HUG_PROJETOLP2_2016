package fidelidade;

public class FidelidadeVIP implements CartaoFidelidade {

	private int pontos = 0;
	
	@Override
	public int getPontos() {
		return pontos;
	}

	@Override
	public void addPontos(double preco) {
		pontos += (int) preco * 0.5;
	}

	@Override
	public double convertePontos(int pontos) throws Exception {	
		if(pontos < 0) {
			throw new Exception("mensagem do exception.");
		}
		if(getPontos() < pontos){
			throw new Exception("mensagem do exception.");
		}	
		double convertePontos = pontos * 0.7;
		
		convertePontos += ((int)(pontos/10)) * 0.5;
		
		return convertePontos;
	}

}
