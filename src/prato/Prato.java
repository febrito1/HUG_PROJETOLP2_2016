package prato;

import excecoes.*;

public class Prato {

	private String nomePrato, descricaoPrato;
	private double precoPrato;
	
	public Prato(String nomePrato, double precoPrato, String descricaoPrato) throws Exception {
		Excecoes.StringException(nomePrato);
		Excecoes.doubleException(precoPrato);
		Excecoes.StringException(descricaoPrato);
		
		this.nomePrato = nomePrato;
		this.precoPrato = precoPrato;
		this.descricaoPrato = descricaoPrato;
		
	}


	public String getNomePrato() {
		return nomePrato;
	}

	public String getDescricaoPrato() {
		return descricaoPrato;
	}

	public double getPrecoPrato() {
		return precoPrato;
	}

	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}

	public void setDescricaoPrato(String descricaoPrato) {
		this.descricaoPrato = descricaoPrato;
	}

	public void setPrecoPrato(double precoPrato) {
		this.precoPrato = precoPrato;
	}
	
	@Override
	public String toString() {
		return "Prato: " + nomePrato + ", custa: " + precoPrato +
				"Descricao: " + descricaoPrato;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoPrato == null) ? 0 : descricaoPrato.hashCode());
		result = prime * result + ((nomePrato == null) ? 0 : nomePrato.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoPrato);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Prato)){
			return false;
		}
		Prato outro = (Prato) obj;
		
		if(outro.getNomePrato().equalsIgnoreCase(getNomePrato()) 
				&& outro.getPrecoPrato() == getPrecoPrato() 
				&& outro.getDescricaoPrato().equalsIgnoreCase(descricaoPrato)){
			return true;
		}return false;
	}
	
}
