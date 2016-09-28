package restaurante;

import java.util.Comparator;

public class OrdenaAlfabeto implements Comparator<Alimentacao> {

	public int compare(Alimentacao alimento1, Alimentacao alimento2) {
		return alimento1.getNome().compareToIgnoreCase(alimento2.getNome());

	}

}
