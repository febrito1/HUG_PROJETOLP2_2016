package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hotel.Hospede;
import junit.framework.Assert;
import quarto.Quarto;

public class HospedeTest {

	@Before
	public void setUp() throws Exception {
		Hospede novoHospede = new Hospede("Livia", "livia_123@em.com", "29/04/1996");
		Assert.assertEquals("Livia", novoHospede.getNomeHospede());
		Assert.assertEquals("livia_123@em.com", novoHospede.getEmailHospede());
		Assert.assertEquals("29/04/1996", novoHospede.getAnoNascimento());

	}

	@Test
	public void testeSets() throws Exception {
		Hospede novoHospede = new Hospede("Livia", "livia_123@em.com", "29/04/1996");
		novoHospede.setNomeHospede("Rebeca");
		novoHospede.setEmailHospede("rebeca_beltrao@em.com");
		novoHospede.setAnoNascimento("29/04/1996");
		Assert.assertEquals("Rebeca", novoHospede.getNomeHospede());
		Assert.assertEquals("rebeca_beltrao@em.com", novoHospede.getEmailHospede());
		Assert.assertEquals("29/04/1996", novoHospede.getAnoNascimento());

	}

	@Test
	public void testeComException() throws Exception {
		try {
			new Hospede(null, "rebeca_beltrao@em.com", "29/04/1996");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		
		try {
			new Hospede("", "rebeca_beltrao@em.com","29/04/1996");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Hospede("Rebeca", null,"29/04/1996");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Hospede("Rebeca","", "29/04/1996");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Hospede(null, null, "29/04/1996");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}try {
			new Hospede("Rebeca", "rebeca_beltrat@em.com", "");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Hospede(null, null, null);
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
		try {
			new Hospede(null, "", "29/04/1996");
		} catch (Exception e) {
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}

	}
}
