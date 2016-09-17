package quarto;



public class Quarto{
	
	private String ID;
	private boolean estadoQuarto = false;
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

	public boolean isEstadoQuarto() {
		return estadoQuarto;
	}

	public void setEstadoQuarto(boolean estadoQuarto) {
		if(estadoQuarto = true){
			estadoQuarto = false;
		}else{
			estadoQuarto = true;
		}	
	}
}
