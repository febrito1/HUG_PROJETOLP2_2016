package quarto;

public class QuartoSimples extends Quarto{

	private double valorDiaria = 100.0;
		public QuartoSimples(String ID) throws Exception {
			super(ID);
			
		}
		public double getValorDiaria() {
			return valorDiaria;
		}
		public void setValorDiaria(double valorDiaria) {
			this.valorDiaria = valorDiaria;
		}
	
}
