package com.ucs.sistemabancario_api.negocio;

public class Cliente {

	private int id;
	private String nome;
	private int idade;
	private String email;
	private StatusCliente status; // Substituí o boolean
	private int idContaCorrente;

	public Cliente(int id, String nome, int idade, String email, int idContaCorrente, StatusCliente status) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.email = email;
		this.idContaCorrente = idContaCorrente;
		this.status = status;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public int getIdade() { return idade; }
	public void setIdade(int idade) { this.idade = idade; }

	public String getEmail() { return email; }

	// REGRA DE DOMÍNIO
	public void setEmail(String email) { 
		if(this.status == StatusCliente.INATIVO) {
			throw new IllegalStateException("Não é possível alterar dados de um cliente inativo.");
		}
		this.email = email; 
	}

	public StatusCliente getStatus() { return status; }

	// Comportamentos do domínio
	public void ativar() { this.status = StatusCliente.ATIVO; }
	public void desativar() { this.status = StatusCliente.INATIVO; }

	public int getIdContaCorrente() { return this.idContaCorrente; }
	public void setIdContaCorrente(int idContaCorrente) { this.idContaCorrente = idContaCorrente; }
}