package com.ucs.sistemabancario_api.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class GerenciadoraClientesTDDTest {

	private GerenciadoraClientes gerenciadora;

	@Before
	public void setup() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente(1, "João", 30, "joao@email.com", 1, StatusCliente.ATIVO));
		
		gerenciadora = new GerenciadoraClientes(clientes);
	}

	// Requisito 2: Cadastrar cliente
	@Test
	public void deveCadastrarClienteComSucesso() {
		Cliente novoCliente = new Cliente(2, "Maria", 25, "maria@email.com", 2, StatusCliente.ATIVO);
		gerenciadora.adicionaCliente(novoCliente);
		
		assertEquals(2, gerenciadora.getClientesDoBanco().size());
		assertEquals(novoCliente, gerenciadora.pesquisaCliente(2));
	}

	// Requisito 3: Validar idade do cliente
	@Test
	public void deveValidarIdadePermitida() throws IdadeNaoPermitidaException {
		boolean resultado = gerenciadora.validaIdade(25);
		assertTrue(resultado);
	}

	@Test(expected = IdadeNaoPermitidaException.class)
	public void deveLancarExcecaoQuandoIdadeForMenorQue18() throws IdadeNaoPermitidaException {
		gerenciadora.validaIdade(17);
	}

	@Test(expected = IdadeNaoPermitidaException.class)
	public void deveLancarExcecaoQuandoIdadeForMaiorQue65() throws IdadeNaoPermitidaException {
		gerenciadora.validaIdade(66);
	}
}