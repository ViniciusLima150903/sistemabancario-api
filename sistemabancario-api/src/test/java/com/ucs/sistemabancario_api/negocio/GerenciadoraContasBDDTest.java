package com.ucs.sistemabancario_api.negocio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class GerenciadoraContasBDDTest {

	private GerenciadoraContas gerenciadora;

	@Before
	public void setup() {
		// DADO que existe uma conta corrente cadastrada no sistema
		ContaCorrente conta = new ContaCorrente(1, 500.0, true);
		
		List<ContaCorrente> contas = new ArrayList<>();
		contas.add(conta);
		gerenciadora = new GerenciadoraContas(contas);
	}

	// Requisito 8: Consultar conta corrente
	/*
	 * Funcionalidade: Consulta de conta corrente
	 * Cenário: Consultar uma conta corrente existente
	 * Dado que existe uma conta corrente cadastrada com ID 1
	 * Quando for solicitada a consulta da conta pelo ID 1
	 * Então a conta com saldo 500.0 deve ser retornada e não ser nula
	 */
	@Test
	public void deveConsultarContaCorrenteExistente() {
		// Quando
		ContaCorrente resultado = gerenciadora.pesquisaConta(1);
		
		// Então
		assertNotNull(resultado);
		assertEquals(500.0, resultado.getSaldo(), 0.001);
	}
}