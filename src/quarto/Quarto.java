package quarto;



public class Quarto{
	
	private String ID;
	private TipoQuarto tipo;

	public Quarto(String ID, TipoQuarto tipo) throws Exception {
		this.ID = ID;
		this.tipo = tipo;
	}
	
	public String getID(){
		return this.ID;
	}
	
	public double getPreco(){
		return tipo.getPreco();
	}

	public String getTipo(){
		return tipo.name().toLowerCase();
	}
	
}
