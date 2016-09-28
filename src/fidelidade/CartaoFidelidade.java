package fidelidade;

public interface CartaoFidelidade {

	
	public int getPontos();
	
	public void addPontos(double preco);
	
	public String convertePontos(int pontos) throws Exception;

	
}
