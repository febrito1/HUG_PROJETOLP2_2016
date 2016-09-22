package quarto;

public class QuartosFactory {

	public QuartosFactory() {
	}

	public Quarto criaQuarto(String id, String tipoQuarto) throws Exception {
		Quarto novoQuarto = null;
		switch (tipoQuarto.toLowerCase()) {
		case "simples":
			novoQuarto = new Quarto(id, TipoQuarto.SIMPLES);
			break;
		case "luxo":
			novoQuarto = new Quarto(id, TipoQuarto.LUXO);
			break;
		case "presidencial":
			novoQuarto = new Quarto(id, TipoQuarto.PRESIDENCIAL);
			break;
		default:
			break;
		}
		return novoQuarto;
	}

}
