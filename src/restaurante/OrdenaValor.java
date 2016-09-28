package restaurante;

import java.util.Comparator;

public class OrdenaValor implements Comparator<Alimentacao> {

	public int compare(Alimentacao alimento1, Alimentacao alimento2) {
		if (alimento1.getPreco() > alimento2.getPreco()) {
			return 1;
		}
		if (alimento1.getPreco() <= alimento2.getPreco()) {
			return -1;
		}
		return 0;
	}

}
