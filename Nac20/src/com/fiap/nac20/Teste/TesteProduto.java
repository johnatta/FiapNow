package com.fiap.nac20.Teste;

import com.fiap.nac20.BO.EstoqueBO;
import com.fiap.nac20.TO.ProdutoTO;

public class TesteProduto {
	public static void main(String[] args) {
		
		EstoqueBO estqBO = new EstoqueBO();
		ProdutoTO prodTO = new ProdutoTO();
		prodTO.setDescricao("Teste");
		prodTO.setPreco(6789);
		prodTO.setQuantidade(7);
		prodTO.setCampanhaPromocional("Foi");
		
		estqBO.adicionarProduto(prodTO);
		
		System.out.println("*** PRIMEIRA LISTAGEM ***");
		for(ProdutoTO prod : estqBO.listarProdutos()) {
			System.out.println("Código : " + prod.getCodProduto());
			System.out.println("Descricao: " + prod.getDescricao());
		}
	}
}
