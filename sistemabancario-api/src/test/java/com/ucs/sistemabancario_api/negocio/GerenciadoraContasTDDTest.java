package com.ucs.sistemabancario_api.negocio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class GerenciadoraContasTDDTest {

	private ContaCorrente origem;
	private ContaCorrente destino;
	private GerenciadoraContas gerenciadora;

	@Before
	public void setup() {
		origem = new ContaCorrente(1, 100.0, true);
		destino = new ContaCorrente(2, 50.0, true);
		
		List<ContaCorrente> contas = new ArrayList<ContaCorrente>();
		contas.add(origem);
		contas.add(destino);
		
		gerenciadora = new GerenciadoraContas(contas);
	}

	// Requisito 12: Transferir valores entre contas
	@Test
	public void deveTransferirQuandoExisteSaldoSuficiente() {
		boolean resultado = gerenciadora.transfereValor(1, 60.0, 2);
		
		assertTrue(resultado);
		assertEquals(40.0, origem.getSaldo(), 0.001);
		assertEquals(110.0, destino.getSaldo(), 0.001);
	}

	@Test
	public void naoDeveTransferirQuandoSaldoForInsuficiente() {
		boolean resultado = gerenciadora.transfereValor(1, 200.0, 2);
		
		assertFalse(resultado);
		assertEquals(100.0, origem.getSaldo(), 0.001);
		assertEquals(50.0, destino.getSaldo(), 0.001);
	}

	// Requisito 9: Cadastrar conta corrente
	@Test
	public void deveCadastrarContaCorrenteComSucesso() {
		ContaCorrente novaConta = new ContaCorrente(3, 300.0, true);
		gerenciadora.adicionaConta(novaConta);
		
		assertEquals(3, gerenciadora.getContasDoBanco().size());
		assertEquals(novaConta, gerenciadora.pesquisaConta(3));
	}
}