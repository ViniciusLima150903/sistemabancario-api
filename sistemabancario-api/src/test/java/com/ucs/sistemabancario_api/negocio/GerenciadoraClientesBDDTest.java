package com.ucs.sistemabancario_api.negocio;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GerenciadoraClientesBDDTest {

	private GerenciadoraClientes gerenciadora;
	private Cliente clienteInativo;
	private Cliente clienteAtivo;

	@Before
	public void setup() {
		// DADO que existem clientes cadastrados no sistema
		clienteInativo = new Cliente(1, "Carlos", 25, "carlos@gmail.com", 1, StatusCliente.INATIVO);
		clienteAtivo = new Cliente(2, "Ana", 30, "ana@gmail.com", 2, StatusCliente.ATIVO);
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(clienteInativo);
		clientes.add(clienteAtivo);
		gerenciadora = new GerenciadoraClientes(clientes);
	}

	// Requisito 1: Consultar cliente
	/*
	 * Funcionalidade: Consulta de cliente
	 * Cenário: Consultar um cliente existente com sucesso
	 * Dado que existe um cliente cadastrado com ID 2
	 * Quando for solicitada a consulta do cliente pelo ID 2
	 * Então os dados do cliente "Ana" devem ser retornados
	 */
	@Test
	public void deveConsultarClienteExistente() {
		// Quando
		Cliente resultado = gerenciadora.pesquisaCliente(2);
		
		// Então
		assertNotNull(resultado);
		assertEquals("Ana", resultado.getNome());
	}

	// Requisito 4: Ativar cliente
	/*
	 * Funcionalidade: Ativação de cliente
	 * Cenário: Ativar um cliente atualmente inativo
	 * Dado que existe um cliente cadastrado
	 * E que o cliente está inativo (ID 1)
	 * Quando for solicitada a ativação do cliente
	 * Então o cliente deverá passar para a situação ativa
	 * E a consulta da situação deverá indicar que o cliente está ativo
	 */
	@Test
	public void deveAtivarClienteInativo() {
		// Dado
		assertEquals(StatusCliente.INATIVO, clienteInativo.getStatus());
		
		// Quando
		clienteInativo.ativar(); // Usando comportamento do domínio
		
		// Então
		assertEquals(StatusCliente.ATIVO, clienteInativo.getStatus());
		assertTrue(gerenciadora.clienteAtivo(1));
	}

	// Requisito 5: Desativar cliente
	/*
	 * Funcionalidade: Desativação de cliente
	 * Cenário: Desativar um cliente atualmente ativo
	 * Dado que existe um cliente cadastrado
	 * E que o cliente está ativo (ID 2)
	 * Quando for solicitada a desativação do cliente
	 * Então o cliente deverá passar para a situação inativa
	 * E a consulta da situação deverá indicar que o cliente está inativo
	 */
	@Test
	public void deveDesativarClienteAtivo() {
		// Dado
		assertEquals(StatusCliente.ATIVO, clienteAtivo.getStatus());
		
		// Quando
		clienteAtivo.desativar(); // Usando comportamento do domínio
		
		// Então
		assertEquals(StatusCliente.INATIVO, clienteAtivo.getStatus());
		assertFalse(gerenciadora.clienteAtivo(2));
	}
}