package quarto;

import excecoes.*;
import hotel.Estadia;

public abstract class Quarto extends Estadia {
	
	private String ID;
	/* CORRIGIR*/
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
