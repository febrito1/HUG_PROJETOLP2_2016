package fidelidade;



public class FidelidadePadrao implements CartaoFidelidade {

	private int pontos;

	
	public FidelidadePadrao(int pontos) {
		this.pontos = pontos;
	}

	public FidelidadePadrao() {
	}

	@Override
	public int getPontos() {
		return pontos;
	}

	@Override
	public void addPontos(double preco) {
		this.pontos += (int) (preco * 0.1);
	}
	
	@Override
	public double desconto(double preco) {
		return preco;
	}
	

	@Override
	public String convertePontos(int pontos) throws Exception{
				
		double descontoPontos = pontos* 0.1;	
		String resultado = String.format("R$%.2f", descontoPontos);	
		removePontos(pontos);
		return resultado;
	}
	
	private void removePontos(int pontos) throws Exception {
		if(pontos > getPontos()) {
			throw new Exception("FUNCIONA MERDA.");
		}	
		this.pontos = getPontos() - pontos;
	
	}

}
