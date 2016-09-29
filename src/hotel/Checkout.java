package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Checkout implements ControleDeGastos {
	
	LocalDate dataCheckout;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String nomeCliente;
	String transacao;
	double totalGasto;
	

	public Checkout(String nomeCliente, String transacao, double totalGasto, LocalDate dataCheckout){
		this.nomeCliente = nomeCliente;
		this.transacao = transacao;
		this.totalGasto = totalGasto;
		this.dataCheckout = dataCheckout;
		
	}

	public String getData() {
		String dataCheckout = formatter.format(this.dataCheckout);
		return dataCheckout;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getTransacao() {
		return transacao;
	}
	
	public double getTotalGasto() {
		return totalGasto;
	}


}
