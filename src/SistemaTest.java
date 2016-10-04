package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import cliente.Hospede;
import quarto.Quarto;
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
	@Test
	public void testeCriaQuarto() throws Exception{
		sistema.criaQuarto("12A","luxo");
		Assert.assertEquals("12A", sistema.criaQuarto("12A","luxo"));
		
	}
	@Test
	public void testeExceptionCadastra() throws Exception{
		try{
			sistema.cadastraHospede("", "r@em.com", "12/03/1996");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", "", "12/03/1996");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", "r@em.com", "");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.", e.getMessage());
		}try{
			sistema.cadastraHospede(null, "r@em.com", "12/03/1996");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.", e.getMessage());
		}try{
			sistema.cadastraHospede("Rebeca", null, null);
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.", e.getMessage());
		}
	}
	@Test
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

	@Test
	public void testeCheckout(){
		try{
			sistema.realizaCheckout("thiago@as", "12");
			
			}catch(Exception e){
				assertEquals("Mensagem de excecao capturada.", "Erro ao realizar checkout. Email do(a) hospede esta invalido.", e.getMessage());
			}
		}
	}

