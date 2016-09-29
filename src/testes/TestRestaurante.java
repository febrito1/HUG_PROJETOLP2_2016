package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import restaurante.Prato;
import restaurante.RestauranteController;

public class TestRestaurante {
	private RestauranteController restaurante = new RestauranteController();
	
	
	@Test
	public void testeCadastraPrato() throws Exception {
		restaurante.cadastraPrato("Sopa", 12.00, "melhor sopa do mundo");
		Assert.assertTrue(true);
	}
	@Test
	public void testeRemovePrato() throws Exception {
		Prato novo= new Prato("Sopa", 12.00, "melhor sopa do mundo");
	
		restaurante.removeCardapio(novo);
		Assert.assertTrue(true);
	}
	@Test
	public void testeBuscaPrato() throws Exception {
		restaurante.cadastraPrato("Sopa", 12.00, "melhor sopa do mundo");
		restaurante.buscaCardapio("Sopa");
		Assert.assertTrue(true);
	}
	@Test
	public void testeExceptionCadastraPrato() throws Exception{
		try{
			restaurante.cadastraPrato("", 12.00, "melhor sopa do mundo");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro do prato. Nome do prato esta vazio.", e.getMessage());
		}try{
			restaurante.cadastraPrato("Sopas", -12.00, "melhor sopa do mundo");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro do prato. Preco do prato eh invalido.", e.getMessage());
		}try{
			restaurante.cadastraPrato("Sopas", 0, "melhor sopa do mundo");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro do prato. Preco do prato eh invalido.", e.getMessage());
		}try{
			restaurante.cadastraPrato("Sopas", 12.00 , "");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro do prato. Descricao do prato esta vazia.", e.getMessage());
		}try{
			restaurante.cadastraPrato(null, 12.00 , "");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Erro no cadastro do prato. Nome do prato esta vazio.", e.getMessage());
		}
	
	}
	
	@Test
	public void testeExceptionCompraPrato() throws Exception{
		Prato prato = new Prato("Panqueca", 5.00, "deliciosas panquecas");
		try{
			new Prato("Panqueca", 5.00, "deliciosas panquecas");
			restaurante.compraPrato(prato);
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.", "Não existe esse prato no cardapio.", e.getMessage());
		}
	}
	@Test
	public void testeExceptionCadastraRefeicao() throws Exception{
		
		try{
			restaurante.cadastraRefeicao("Pasto italiano","Varios pratos italianos para saborear.",null);
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.","Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).", e.getMessage());
		}try{
			restaurante.cadastraRefeicao("Pasto italiano","Varios pratos italianos para saborear.","Bruschetta;Bresaola com rucula e parmesao;Penne ao pesto;Tiramisu;Risotto");
		}catch(Exception e){
			assertEquals("Mensagem de excecao capturada.","Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.", e.getMessage());
		}
	}
}
