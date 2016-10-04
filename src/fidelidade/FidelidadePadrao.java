package fidelidade;

/**
 * Classe que implementa CartaoFidelidade
 * 
 */
public class FidelidadePadrao implements CartaoFidelidade {

	private int pontos;

	/**
	 * Construtor de FidelidadePadrao
	 * 
	 * @param int - pontos
	 */
	public FidelidadePadrao(int pontos) {
		this.pontos = pontos;
	}
	
	/**
	 * Construtor padrao de FidelidadePadrao
	 */
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
				
		double descontoPontos = pontos * 0.1;	
		String resultado = String.format("R$%.2f", descontoPontos);	
		removePontos(pontos);
		return resultado;
	}
	
	/**
	 * Acessa os pontos atuais e remove a quantidade passada como parametro.
	 * 
	 * @param int - pontos
	 * @throws Exception
	 */
	private void removePontos(int pontos) throws Exception {
		if(pontos > getPontos()) {
			throw new Exception("Você não possui pontos suficientes para realizar a operacao.");
		}	
		this.pontos = getPontos() - pontos;
	
	}

}
