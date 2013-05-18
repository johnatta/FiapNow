package com.fiap.nac20.TO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TAB_PRODUTO")
public class ProdutoTO implements Serializable{
	@Id
	@GenericGenerator(name="seqProduto", strategy="increment")
	@GeneratedValue(generator="seqProduto")
	private int codProduto;
	
	@Column
	private String descricao;
	@Column
	private int quantidade;
	@Column
	private double preco;
	@Column
	private String campanhaPromocional;
	
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getCampanhaPromocional() {
		return campanhaPromocional;
	}
	public void setCampanhaPromocional(String campanhaPromocional) {
		this.campanhaPromocional = campanhaPromocional;
	}
	
}
