package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import restaurante.Prato;
import restaurante.TipoPrato;

public class testePrato {

	@Test
	public void criaPrato() throws Exception{
		
		Prato novoPrato = new Prato("Bolacha", 30.29, "Bolacha tipica das ilhas maldivas");
		assertEquals("Bolacha", novoPrato.getNomePrato());
		assertEquals(30.29, novoPrato.getPrecoPrato(),0);
		assertEquals("Bolacha tipica das ilhas maldivas", novoPrato.getDescricaoPrato());
	
	}

	@Test
	public void criaPratoComTipo() throws Exception{
		
		Prato novoPratoTipo = new Prato("Pão de alho", 12.0, "Pão de alho a requeijão", TipoPrato.ENTRADA);
		assertEquals("Pão de alho", novoPratoTipo.getNomePrato());
		assertEquals(12.0, novoPratoTipo.getPrecoPrato(), 12.0);
		assertEquals("Pão de alho a requeijão", novoPratoTipo.getDescricaoPrato());
		assertEquals(TipoPrato.ENTRADA, novoPratoTipo.getTipoPrato());
		
		
		Prato novoPratoSobremesa = new Prato("Sorvete", 6.0, "Sorvete Quibom", TipoPrato.SOBREMESA);
		assertEquals("Sorvete", novoPratoSobremesa.getNomePrato());
		assertEquals(6.0, novoPratoSobremesa.getPrecoPrato(), 0.1);
		assertEquals("Sorvete Quibom", novoPratoSobremesa.getDescricaoPrato());
		assertEquals(TipoPrato.SOBREMESA, novoPratoSobremesa.getTipoPrato());
		
	}
	
	@Test
	public void modificaPrato() throws Exception{
		
		Prato prato = new Prato("Pão de alho", 12.0, "Pão de alho a requeijão", TipoPrato.ENTRADA);
		prato.setNomePrato("Macarrão a carbonara");
		prato.setPrecoPrato(27.5);
		prato.setDescricaoPrato("Macarrão ao molho de carbonara");
		prato.setTipoPrato(TipoPrato.PRINCIPAL);
		
		assertEquals("Macarrão a carbonara", prato.getNomePrato());
		assertEquals(27.5, prato.getPrecoPrato(), 12.0);
		assertEquals("Macarrão ao molho de carbonara", prato.getDescricaoPrato());
		assertEquals(TipoPrato.PRINCIPAL, prato.getTipoPrato());
		
	
	}
	
	@Test
	public void criaPratoException(){
		
		try{
			new Prato("Arroz a milanesa", 20.0, "");
		}
		catch(Exception exp){
			assertEquals("Mensagem de excecão capturada", "String nao pode ser nula ou vazia", exp.getMessage());
		}
		try{
			new Prato(null, 20.0, null);
		}
		catch(Exception exp){
			assertEquals("Mensagem de excecão capturada", "String nao pode ser nula ou vazia", exp.getMessage());
		}
		try{
			new Prato("Arroz com cuzcuz", -1.3, "Delicia nordestina");
		}
		catch(Exception exp){
			assertEquals("Mensagem de excecão capturada", "Numero nao pode ser negativo.", exp.getMessage());
		}
		try{
			new Prato("Carbonara", 20.0, "");
		}
		catch(Exception exp){
			assertEquals("Mensagem de excecão capturada", "String nao pode ser nula ou vazia", exp.getMessage());
		}
	
	}
}
