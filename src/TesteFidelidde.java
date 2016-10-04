package testes;

import static org.junit.Assert.*;
import fidelidade.FidelidadePadrao;
import fidelidade.FidelidadePremium;
import fidelidade.FidelidadeVIP;

import org.junit.Test;

import cliente.Hospede;

public class TesteFidelidde {

			
			
		@Test
		public void testeFidelidadePadrao() throws Exception{
			FidelidadePadrao fidelidadePadrao = new FidelidadePadrao();
			fidelidadePadrao.addPontos(80.0);
			assertEquals(8,fidelidadePadrao.getPontos());
			fidelidadePadrao.addPontos(780);
			assertEquals(86, fidelidadePadrao.getPontos());
			assertEquals("R$5,00",fidelidadePadrao.convertePontos(50));
			assertEquals(36, fidelidadePadrao.getPontos());
		}
		
		@Test
		public void testeFidelidadePremium() throws Exception{
			
			FidelidadePremium fidelidade = new FidelidadePremium(360);
			assertEquals(360, fidelidade.getPontos());
			fidelidade.addPontos(431.25);
			assertEquals(529, fidelidade.getPontos());
			assertEquals("R$128,00", fidelidade.convertePontos(400));
			assertEquals(129, fidelidade.getPontos());
			
		}
		
		@Test
		public void testeFidelidadeVIP() throws Exception{
			
			FidelidadeVIP fidelidadeVip = new FidelidadeVIP(1100);
			assertEquals(1100, fidelidadeVip.getPontos());
			fidelidadeVip.addPontos(186.99);
			assertEquals(1193, fidelidadeVip.getPontos());
			assertEquals("R$270,00",fidelidadeVip.convertePontos(360));
			assertEquals(833, fidelidadeVip.getPontos());
		}
		
		@Test
		public void testeMudaFidelidade() throws Exception{
			Hospede novoHospede = new Hospede("Rebeca", "r@em.com", "12/03/1996");
			novoHospede.adicionaPontos(890);
			assertEquals(89, novoHospede.getPontos());
			novoHospede.adicionaPontos(8980);
			novoHospede.mudaFidelidade();//Fidelidade Premium
			assertEquals(987, novoHospede.getPontos());
			assertEquals("R$12,80", novoHospede.convertePontos(40));
			novoHospede.adicionaPontos(700);
			assertEquals(1227, novoHospede.getPontos());
			novoHospede.mudaFidelidade();//Fidelidade VIP
			assertEquals("R$225,00", novoHospede.convertePontos(300));
		}
		
}

