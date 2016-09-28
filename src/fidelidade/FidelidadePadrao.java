package fidelidade;


import java.text.DecimalFormat;

public class FidelidadePadrao implements CartaoFidelidade {

	private int pontos = 0;
	private DecimalFormat decimal;
	
	public FidelidadePadrao() {
		decimal = new DecimalFormat(".##");
	}
	
	@Override
	public int getPontos() {
		return pontos;
	}

	@Override
	public void addPontos(double preco) {
		this.pontos += (int) (preco * 0.9);
	}
	
	@Override
	public String convertePontos(int pontos) throws Exception{
		if(pontos < 0) {
			throw new Exception("mensagem do exception.");
		}
		if(getPontos() < pontos){
			throw new Exception("mensagem do exception.");
		}	
		
		double descontoPontos = pontos* 0.1;
		
		
		String resultado = String.format("R$%.2f", descontoPontos );
			
		return resultado;
	}

}
