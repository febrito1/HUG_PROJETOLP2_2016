package hotel;

public class FactoryDeHospede {
	
	public Hospede criaHospede(String nomeHospede, String emailHospede, int anoNascimento) throws Exception{
		return new Hospede(nomeHospede, emailHospede, anoNascimento);
		
	}
}
