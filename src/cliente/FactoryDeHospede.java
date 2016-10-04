package cliente;

/**
 * Classe que cria um novo hospede 
 * 
 */
public class FactoryDeHospede {
	
	/**
	 * Cria o novo hospede com os parametros recebidos
	 * 
	 * @param nomeHospede
	 * @param emailHospede
	 * @param anoNascimento
	 * @return Hospede
	 * @throws StringException
	 */
	public Hospede criaHospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception{
		return new Hospede(nomeHospede, emailHospede, anoNascimento);
		
	}
}
