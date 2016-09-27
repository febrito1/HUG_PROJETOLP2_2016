package fidelidade;

public interface CartaoFidelidade {

	
	public int getPontos();
	
	public void addPontos(double preco);
	
	public double convertePontos(int pontos) throws Exception;

	
}
