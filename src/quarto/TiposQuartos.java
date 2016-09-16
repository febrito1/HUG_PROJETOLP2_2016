package quarto;

public enum TiposQuartos {
	
	Luxo(250.0), Simples(100.0), Presencial(450.0);

	private double valorQuarto;
	
	private TiposQuartos(double valor) {
		this.valorQuarto = valor;
	}

	public double getValorQuarto() {
		return valorQuarto;
	}
	
	
}
