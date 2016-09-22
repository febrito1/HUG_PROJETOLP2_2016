package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Checkout {

	LocalDate dataCheckout;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String nomeCliente;
	String quarto;
	double totalGasto;

	public Checkout(String nomeCliente, String quarto, double totalGasto, LocalDate dataCheckout) {
		this.nomeCliente = nomeCliente;
		this.quarto = quarto;
		this.totalGasto = totalGasto;
		this.dataCheckout = dataCheckout;

	}

	public String getDataCheckout() {
		String dataCheckout = formatter.format(this.dataCheckout);
		return dataCheckout;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getQuarto() {
		return quarto;
	}

	public double getTotalGasto() {
		return totalGasto;
	}

}
