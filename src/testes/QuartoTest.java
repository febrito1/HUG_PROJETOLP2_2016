package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import junit.framework.Assert;
import quarto.Quarto;
import quarto.QuartoLuxo;
import quarto.QuartoPresidencial;
import quarto.QuartoSimples;

public class QuartoTest {

	@Before
	public void setUp() throws Exception {
		Quarto quartoLuxo = new QuartoLuxo("120B");
		Quarto quartoPresidencial = new QuartoPresidencial("201C");
		Quarto quartoSimples = new QuartoSimples("30A");
		Assert.assertEquals("120B", quartoLuxo.getID());
		Assert.assertEquals("201C", quartoPresidencial.getID());
		Assert.assertEquals("30A", quartoSimples.getID());

	}

	@Test
	public void testeID() throws Exception {
		Quarto quartoLuxo = new QuartoLuxo("120B");
		Quarto quartoPresidencial = new QuartoPresidencial("201C");
		Quarto quartoSimples = new QuartoSimples("30A");
		quartoLuxo.setID("33A");
		Assert.assertEquals("33A", quartoLuxo.getID());
		quartoPresidencial.setID("44A");
		Assert.assertEquals("44A", quartoPresidencial.getID());
		quartoSimples.setID("55A");
		Assert.assertEquals("55A", quartoSimples.getID());

	}

	@Test
	public void testeIDComException() throws Exception {
		
		try {
			new QuartoLuxo(null);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new QuartoLuxo("");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new QuartoPresidencial(null);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new QuartoPresidencial("");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new QuartoSimples(null);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new QuartoLuxo("");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}

	}
}
