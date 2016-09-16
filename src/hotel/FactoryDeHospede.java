package hotel;

public class FactoryDeHospede {
	
	public Hospede criaHospede(String nomeHospede, String emailHospede, String anoNascimento) throws Exception{
		return new Hospede(nomeHospede, emailHospede, anoNascimento);
		
	}
}
