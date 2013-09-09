package br.com.fiap.pesquisa;

import java.io.Serializable;

public class ItemGrafico implements Serializable {
	
	public ItemGrafico(String nome, int qtd){
		this.nome = nome;
		this.qtd = qtd;
	}
	
	private String nome;
	private int qtd;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

}
