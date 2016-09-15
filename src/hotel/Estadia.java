package hotel;

import excecoes.Excecoes;
import quarto.Quarto;
import quarto.QuartosFactory;
import java.util.HashSet;


public class Estadia {
	
	private QuartosFactory novoQuarto;
	private Hospede novoHospede;
	private HashSet<Quarto> quartosHospede;
	private String nomeHospede, emailHospede, idQuarto;
	private QuartosFactory quartos;
	private int qtdDias, anoNascimento;
	
	/*public Estadia(String nomeHospede, String emailHospede, String idQuarto,int qtdDias, int anoNascimento, HashSet<Quarto> quartosHospedes) throws Exception{
		
		Excecoes.StringException(idQuarto);
		Excecoes.StringException(emailHospede);
		Excecoes.StringException(nomeHospede);
		Excecoes.inteiroException(qtdDias);
		Excecoes.inteiroException(anoNascimento);

		
		this.nomeHospede = nomeHospede;
		this.emailHospede = emailHospede;
		this.anoNascimento = anoNascimento;
		this.novoHospede = new Hospede(nomeHospede, emailHospede, anoNascimento);
		this.idQuarto = idQuarto;
		this.qtdDias = qtdDias;
		
		
		
	} */
	
	public Estadia (String idQuarto, int qtdDias) throws Exception{
		
		Excecoes.StringException(idQuarto);
		Excecoes.inteiroException(qtdDias);
		
		
	}
	
	public boolean cadastraHospedeQuarto(String id, String tipoQuarto){
		novoQuarto.criaQuarto(id, tipoQuarto);
		
		
		
	}
	
}
