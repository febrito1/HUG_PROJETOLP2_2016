package quarto;



public class Quarto{
	
	private double valorQuarto;
	

	public Quarto() throws Exception {
		
	}
	
	public void getTipo(String tipoQuarto){
		switch(tipoQuarto.toLowerCase()){
		case("luxo"):
			valorQuarto = TiposQuartos.Luxo.getValorQuarto();
		case("Simples"):
			valorQuarto = TiposQuartos.Simples.getValorQuarto();
		case("Presidencial"):
			valorQuarto = TiposQuartos.Presencial.getValorQuarto();
		}
	}

	public double getValorQuarto() {
		return valorQuarto;
	}
}
