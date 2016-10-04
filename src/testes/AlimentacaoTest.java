package testes;

import org.junit.Test;

import hotel.SistemaController;
import junit.framework.Assert;

public class AlimentacaoTest {
	
	private SistemaController sistema = new SistemaController();

	@Test
	public void TestOrdena() throws Exception{
		sistema.cadastraPrato("Sopa", 14.00, "melhor sopa do mundo");
		sistema.cadastraPrato("Pure", 5.00, "pure de batatas vegano");
		sistema.cadastraPrato("Arroz", 6.00, "arroz soltinho, temperado com ervilhas e alho");
		sistema.cadastraRefeicao("Trio de Jantar", "3 tipos de acompanhamentos deliciosos", "Sopa;Pure;Arroz");
		sistema.ordenaMenu("preco");
		Assert.assertEquals("Pure;Arroz;Sopa;Trio de Jantar", sistema.consultaMenuRestaurante());
		sistema.ordenaMenu("nome");
		Assert.assertEquals("Arroz;Pure;Sopa;Trio de Jantar", sistema.consultaMenuRestaurante());
	}
	
	@Test
	public void realizaPedidoTest() throws Exception{
		sistema.cadastraPrato("Sopa", 14.00, "melhor sopa do mundo");
		sistema.cadastraPrato("Pure", 5.00, "pure de batatas vegano");
		sistema.cadastraPrato("Arroz", 6.00, "arroz soltinho, temperado com ervilhas e alho");
		sistema.cadastraHospede("Rebeca", "rebeca@em.com", "12/03/1996");
		sistema.realizaCheckin("rebeca@em.com", 7, "14", "presidencial");
		sistema.realizaPedido("rebeca@em.com", "Sopa");
		Assert.assertEquals("R$14,00", sistema.consultaTransacoes("Total"));
		Assert.assertEquals("1", sistema.consultaTransacoes("quantidade"));
		Assert.assertEquals("Rebeca", sistema.consultaTransacoes("nome"));
		sistema.cadastraHospede("Daniyel", "dan@em.com", "11/04/1996");
		sistema.realizaCheckin("dan@em.com", 2, "25", "luxo");
		sistema.realizaPedido("dan@em.com", "Arroz");
		Assert.assertEquals("R$20,00", sistema.consultaTransacoes("Total"));
		Assert.assertEquals("2", sistema.consultaTransacoes("quantidade"));
		Assert.assertEquals("Rebeca;Daniyel", sistema.consultaTransacoes("nome"));

	}
	}
