package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


import quarto.Quarto;

import quarto.TipoQuarto;

public class QuartoTest {

	@Test
	public void criaQuartoteste() throws Exception {
		Quarto quartoLuxo = new Quarto("201D", TipoQuarto.LUXO);
		Quarto quartoPresidencial = new Quarto("201C", TipoQuarto.PRESIDENCIAL);
		Quarto quartoSimples = new Quarto("2D2", TipoQuarto.SIMPLES);
		assertEquals("201D", quartoLuxo.getID());
		assertEquals("201C", quartoPresidencial.getID());
		assertEquals("2D2", quartoSimples.getID());
		assertEquals(250, quartoLuxo.getPreco(),250);
	}

	@Test
	public void testeIDComException() throws Exception {
		
		try {
			new Quarto(null, null);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Quarto("", TipoQuarto.SIMPLES);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			 new Quarto(null, null);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Quarto("", TipoQuarto.SIMPLES);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Quarto("", TipoQuarto.PRESIDENCIAL);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Quarto("", TipoQuarto.LUXO);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}

	}
}
