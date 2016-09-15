package hotel;

<<<<<<< HEAD
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
=======
import quarto.Quarto;

public class Estadia {
	private int quantidadeDias;
	private double total = 0;
	private Quarto quarto;

	public Estadia() {
	}

	public double calculaGastoTotal() {
		return total += quantidadeDias * quarto.getValorDiaria();
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
>>>>>>> af06d4a59bb3a76755f82ab332ce7478aabbfd2d

		
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
