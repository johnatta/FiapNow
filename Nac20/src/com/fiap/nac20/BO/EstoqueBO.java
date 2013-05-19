package com.fiap.nac20.BO;

import java.util.List;

import com.fiap.nac20.DAO.DAO;
import com.fiap.nac20.TO.ProdutoTO;

public class EstoqueBO {
	
	public ProdutoTO consultarProduto(int codProduto){
		DAO<ProdutoTO> produtoDAO = new DAO<ProdutoTO>(ProdutoTO.class);
		ProdutoTO produtoTO = produtoDAO.consultarProduto(codProduto);
		return produtoTO;
	}
	
	public void adicionarProduto(ProdutoTO produtoTO)  {
		DAO<ProdutoTO> produtoDAO = new DAO<ProdutoTO>(ProdutoTO.class);
		produtoDAO.adicionarProduto(produtoTO);
	}
	public ProdutoTO[] listarProdutos(){
		DAO<ProdutoTO> produtoDAO = new DAO<ProdutoTO>(ProdutoTO.class);
		List<ProdutoTO> produtos = produtoDAO.listarProdutos();
		ProdutoTO[] produtosArray = new ProdutoTO[produtos.size()];
		produtos.toArray(produtosArray);
		return produtosArray;
	}
	
	public void removerProduto(ProdutoTO produtoTO){
		DAO<ProdutoTO> produtoDAO = new DAO<ProdutoTO>(ProdutoTO.class);
		produtoDAO.removerProduto(produtoTO);
	}
	
	public String[] buscarTodasDescricoesProdutos(String descricao){
		DAO<ProdutoTO> produtoDAO = new DAO<ProdutoTO>(ProdutoTO.class);
		List<String> descricoes = produtoDAO.buscarTodasDescricoesProdutos(descricao);
		String[] descricoesArray = new String[descricoes.size()];
		descricoes.toArray(descricoesArray);
		return descricoesArray;
		
	}
	
	public void atualizarProduto(ProdutoTO produtoTO){
		DAO<ProdutoTO> produtoDAO = new DAO<ProdutoTO>(ProdutoTO.class);
		produtoDAO.atualizarProduto(produtoTO);
	}
}
