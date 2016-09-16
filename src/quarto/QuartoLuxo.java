package quarto;

public class QuartoLuxo extends Quarto{

	private double valorDiaria = 250.0;
	
	public QuartoLuxo(String ID) throws Exception {
		super(ID);
		
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
	
}
