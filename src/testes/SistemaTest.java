package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cliente.Hospede;
import quarto.Quarto;
import quarto.TipoQuarto;
import hotel.SistemaController;

public class SistemaTest {
	private Hospede hospede;
	private SistemaController sistema = new SistemaController();
	private Quarto quarto;

	@Test
	public void testeCadastro() throws Exception {
		sistema.cadastraHospede("Rebeca", "r@em.com", "12/03/1996");
		Assert.assertEquals("Rebeca", sistema.getInfo("r@em.com", "nome"));
		Assert.assertEquals("r@em.com", sistema.getInfo("r@em.com", "email"));
		Assert.assertEquals("12/03/1996", sistema.getInfo("r@em.com", "data de nascimento"));
		Assert.assertNotEquals("Daniyel", sistema.getInfo("r@em.com", "nome"));
		Assert.assertEquals("r@em.com", sistema.buscaHospede("r@em.com"));
		sistema.atualizaCadastro("r@em.com","nome", "Daniyel");
		Assert.assertEquals("Daniyel", sistema.getInfo("r@em.com", "nome"));
	}
	
	public void testeCriaQuarto() throws Exception{
		sistema.criaQuarto("12A","luxo");
		Assert.assertEquals("12A", sistema.criaQuarto("12A","luxo"));
		
	}
	public void testeExceptionCadastra() throws Exception{
		try{
			sistema.cadastraHospede("", "r@em.com", "12/03/1996");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", "", "12/03/1996");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", "r@em.com", "");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}try{
			sistema.cadastraHospede(null, "r@em.com", "12/03/1996");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", null, null);
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "String nao pode ser nula ou vazia", e.getMessage());
		}
	}
	
	public void testeCriaQuartoException(){
		try{
			sistema.criaQuarto("12B", "simples");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro ao realizar checkin. Quarto " + quarto.getID() + " ja esta ocupado.", e.getMessage());
		}try{
			sistema.criaQuarto("12B", "simples");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro ao realizar checkin. Quarto " + quarto.getID() + " ja esta ocupado.", e.getMessage());
		}
	}
	
	public void testeRealizaCheckin(){
		try{
			sistema.realizaCheckin("daniyel@gm.cin", 7, "14", "presidencial");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro na consulta de hospede. Hospede de email " + hospede.getEmailHospede() + " nao foi cadastrado(a).", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", "rebeca.e@gm.com", "29/04/1996");
			sistema.realizaCheckin("rebeca.e@gm.com", 7, "14", "presidencial");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro ao realizar checkin. Quarto " + quarto.getID()+ " ja esta ocupado.", e.getMessage());
		}
	}
	public void testeInfoHospede(){
		try{
			sistema.getInfo("daniyel@gm.cin", "nome");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro de Hospede. Hospede nao cadastrado", e.getMessage());
	}
	}
	public void testeInfoHospedagem(){
	try{
		sistema.getInfo("daniyel@gm.cin", "hospedagens ativas");
	}catch(Exception e){
		assertEquals("Mensagem de excecao capturada.", "Hospede " + hospede.getNomeHospede() + " nao esta hospedado(a).", e.getMessage());
	}try{
		sistema.getInfo("daniyel@gm.cin", "quarto");
	}catch(Exception e){
		assertEquals("Mensagem de excecao capturada.", "Hospede " + hospede.getNomeHospede() + " nao esta hospedado(a).", e.getMessage());
	}try{
		sistema.getInfo("daniyel@gm.cin", "total");
	}catch(Exception e){
		assertEquals("Mensagem de excecao capturada.", "Hospede " + hospede.getNomeHospede() + " nao esta hospedado(a).", e.getMessage());
	}
	
	}
	public void testeCheckout(){
		try{
			sistema.realizaCheckout("thiago@as", "12");
			
			}catch(Exception e){
				assertEquals("Mensagem de excecao capturada.", "fail", e.getMessage());
			}
		}
	}

