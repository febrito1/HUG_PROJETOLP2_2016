package restaurante;

import java.util.HashSet;
import excecoes.Excecoes;

public class Restaurante {
	
	private HashSet<Prato> cardapio;

	public Restaurante() {
		
		this.cardapio = new HashSet<Prato>();
		
	}

	
	public boolean adicionaCardapio(Prato prato) throws Exception{	
		Excecoes.verificaPrato(prato);
		return cardapio.add(prato);
	}
	public boolean removeCardapio(Prato prato){
		for (Prato removeprato : cardapio) {
			if(removeprato.equals(prato)){
				removeprato.equals(removeprato);
				return true;
			}	
		} return false;
	}
	
	public double compraPrato(Prato prato) throws Exception{
		Excecoes.verificaPrato(prato);
		if(!(buscaCardapio(prato))){
			throw new Exception("Não existe esse prato no cardapio.");
		}	
		return prato.getPrecoPrato() - (prato.getPrecoPrato()* 0.1);	
	}
	
	
	public boolean buscaCardapio(Prato prato){
		return cardapio.contains(prato);
	}
	
}
