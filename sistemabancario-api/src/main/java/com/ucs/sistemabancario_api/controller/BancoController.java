package com.ucs.sistemabancario_api.controller;

import com.ucs.sistemabancario_api.negocio.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/banco")
public class BancoController {

    private GerenciadoraClientes gerClientes = new GerenciadoraClientes(new ArrayList<>());
    private GerenciadoraContas gerContas = new GerenciadoraContas(new ArrayList<>());

    @PostMapping("/clientes")
    public ResponseEntity<String> cadastrarCliente(@RequestBody Cliente novoCliente) {
        try {
            gerClientes.validaIdade(novoCliente.getIdade());
            gerClientes.adicionaCliente(novoCliente);
            return ResponseEntity.ok("Cliente cadastrado com sucesso!");
        } catch (IdadeNaoPermitidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/clientes/{id}/ativar")
    public ResponseEntity<String> ativarCliente(@PathVariable int id) {
        Cliente cliente = gerClientes.pesquisaCliente(id);
        if (cliente != null) {
            cliente.ativar();
            return ResponseEntity.ok("Cliente ativado com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/contas")
    public ResponseEntity<String> cadastrarConta(@RequestBody ContaCorrente novaConta) {
        gerContas.adicionaConta(novaConta);
        return ResponseEntity.ok("Conta cadastrada com sucesso!");
    }

    @GetMapping("/contas/{id}")
    public ResponseEntity<ContaCorrente> consultarConta(@PathVariable int id) {
        ContaCorrente conta = gerContas.pesquisaConta(id);
        if (conta != null) {
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String> transferir(@RequestParam int idOrigem, @RequestParam int idDestino, @RequestParam double valor) {
        boolean sucesso = gerContas.transfereValor(idOrigem, valor, idDestino);
        if (sucesso) {
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        }
        return ResponseEntity.badRequest().body("Saldo insuficiente ou conta inválida.");
    }
}