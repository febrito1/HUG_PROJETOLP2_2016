package quarto;

import excecoes.*;

public abstract class Quarto {
	
	private String ID;
	
	public Quarto(String ID) throws Exception {
		Excecoes.StringException(ID);
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}


	public void setID(String ID) throws Exception {
		Excecoes.StringException(ID);
		this.ID = ID;
	}

	public abstract double getValorDiaria();

}
