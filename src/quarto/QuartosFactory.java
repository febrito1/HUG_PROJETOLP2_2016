package quarto;

/**
 * Classe criadora de quartos
 * 
 */
public class QuartosFactory {
	
	/**
	 * Cria um novo quarto que pode ser do tipo simples, luxo ou presidencial
	 * 
	 * @param String - ID
	 * @param String - tipoQuarto
	 * @return Quarto - quarto criado
	 * @throws Exception
	 */
	public Quarto criaQuarto(String ID, String tipoQuarto) throws Exception {
		Quarto novoQuarto = null;
		switch (tipoQuarto.toLowerCase()) {
		case "simples":
			novoQuarto = new Quarto(ID, TipoQuarto.SIMPLES);
			break;
		case "luxo":
			novoQuarto = new Quarto(ID, TipoQuarto.LUXO);
			break;
		case "presidencial":
			novoQuarto = new Quarto(ID, TipoQuarto.PRESIDENCIAL);
			break;
		default:
			break;
		}
		return novoQuarto;
	}

}
