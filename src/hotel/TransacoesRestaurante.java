package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransacoesRestaurante implements ControleDeGastos {

	
	private String nomeCliente, itemMenu;
	private double totalGasto;
	private LocalDate data;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public TransacoesRestaurante(String nomeCliente, String itemMenu, double totalGasto, LocalDate data) {
		this.nomeCliente = nomeCliente;
		this.itemMenu = itemMenu;
		this.totalGasto = totalGasto;
		this.data = data;
	}
	
	
	public String getData() {
		String dataCheckout = formatter.format(this.data);
		return dataCheckout;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getTransacao() {
		return itemMenu;
	}
	
	public double getTotalGasto() {
		return totalGasto;
	}

}
