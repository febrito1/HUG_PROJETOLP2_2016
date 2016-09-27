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
	public double convertePontos(int pontos) throws Exception{
		if(pontos < 0) {
			throw new Exception("mensagem do exception.");
		}
		if(getPontos() < pontos){
			throw new Exception("mensagem do exception.");
		}	
		
		return pontos * 0.1;
	}

}
