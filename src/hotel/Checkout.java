package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * 
 * Classe de checkout que possui seu construtor e implementa ControleDeGastos
 */
public class Checkout implements ControleDeGastos {
	
	LocalDate dataCheckout;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String nomeCliente;
	String transacao;
	double totalGasto;
	
	/**
	 * Cosntrutor de Checkout 
	 * 
	 * @param String - nomeCliente
	 * @param String - transacao
	 * @param Double - totalGasto
	 * @param LocalDate - dataCheckout
	 */
	public Checkout(String nomeCliente, String transacao, double totalGasto, LocalDate dataCheckout){
		this.nomeCliente = nomeCliente;
		this.transacao = transacao;
		this.totalGasto = totalGasto;
		this.dataCheckout = dataCheckout;
		
	}
	
	@Override
	public String getData() {
		String dataCheckout = formatter.format(this.dataCheckout);
		return dataCheckout;
	}
	
	@Override
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	@Override
	public String getTransacao() {
		return transacao;
	}
	
	@Override
	public double getTotalGasto() {
		return totalGasto;
	}


}
