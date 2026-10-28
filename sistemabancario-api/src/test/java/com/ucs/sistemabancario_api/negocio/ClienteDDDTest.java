package com.ucs.sistemabancario_api.negocio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClienteDDDTest {

	// Requisito 6: Verificar situação do cliente (Regra de Domínio)
	@Test(expected = IllegalStateException.class)
	public void naoDevePermitirAlterarEmailDeClienteInativo() {
		Cliente cliente = new Cliente(1, "Pedro", 40, "pedro@email.com", 1, StatusCliente.INATIVO);
		
		// Como o cliente está inativo, o domínio deve barrar essa alteração lançando a exceção
		cliente.setEmail("novoemail@email.com");
	}
	
	@Test
	public void devePermitirAlterarEmailDeClienteAtivo() {
		Cliente cliente = new Cliente(2, "Lucas", 35, "lucas@email.com", 2, StatusCliente.ATIVO);
		
		cliente.setEmail("novoemail@email.com");
		assertEquals("novoemail@email.com", cliente.getEmail());
	}
}