package hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * 
 * Classe que possui seu construtor e implementa ControleDeGastos
 */
public class TransacoesRestaurante implements ControleDeGastos {
	
	private String nomeCliente, itemMenu;
	private double totalGasto;
	private LocalDate data;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * Construtor de TransacoesRestaurante
	 * 
	 * @param String - nomeCliente
	 * @param String - itemMenu
	 * @param Double - totalGasto
	 * @param LocalDate - data
	 */
	public TransacoesRestaurante(String nomeCliente, String itemMenu, double totalGasto, LocalDate data) {
		this.nomeCliente = nomeCliente;
		this.itemMenu = itemMenu;
		this.totalGasto = totalGasto;
		this.data = data;
	}
	
	@Override
	public String getData() {
		String dataCheckout = formatter.format(this.data);
		return dataCheckout;
	}
	
	@Override
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	@Override
	public String getTransacao() {
		return itemMenu;
	}
	
	@Override
	public double getTotalGasto() {
		return totalGasto;
	}

}
