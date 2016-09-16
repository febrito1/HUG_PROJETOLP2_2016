package quarto;

public class QuartoPresidencial extends Quarto {

	private double valorDiaria = 450.0;
	
	public QuartoPresidencial(String ID) throws Exception {
		super(ID);
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
	

}
