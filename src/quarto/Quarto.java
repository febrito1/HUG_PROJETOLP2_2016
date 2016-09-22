package quarto;

public class Quarto {

	private String ID;
	private TipoQuarto tipo;

	public Quarto(String ID, TipoQuarto tipo) {
		this.ID = ID;
		this.tipo = tipo;
	}

	public String getID() {
		return this.ID;
	}

	public double getPreco() {
		return tipo.getPreco();
	}

	public String getTipo() {
		return tipo.name().toLowerCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object outroQuarto) {
		if (outroQuarto instanceof Quarto) {
			Quarto novo = (Quarto) outroQuarto;
			if (novo.getID().equalsIgnoreCase(ID)) {
				return true;
			}
		}
		return false;
	}

}
