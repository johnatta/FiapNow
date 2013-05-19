package com.fiap.nac20.MB;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.fiap.nac20.BO.EstoqueBOProxy;
import com.fiap.nac20.TO.ProdutoTO;


@ManagedBean
@RequestScoped
public class ProdutoMB {
    private ProdutoTO produto;
    private List<ProdutoTO> produtos;
    private int totalProdutos; 
    
    @PostConstruct
    public void inicializar(){
    	produto = new ProdutoTO();
    }
    
    public List<ProdutoTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoTO> produtos) {
		this.produtos = produtos;
	}

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public ProdutoTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoTO produto) {
		this.produto = produto;
	}
	
	@PostConstruct
    public void consultarProduto(){
    	EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
    	try {
			produto = estoqueBO.consultarProduto(produto.getCodProduto());
			if (produto == null){
				produto = new ProdutoTO();
				produto.setCodProduto(000);
				produto.setDescricao("Produto Inexistente");
				produto.setQuantidade(000);
				produto.setPreco(0);
				produto.setCampanhaPromocional("Campanha Inexistente para o produto selecionado");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
	@PostConstruct
    public void adicionarProduto(){
    	EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
    	FacesMessage fm = new FacesMessage();
    	FacesContext fc = FacesContext.getCurrentInstance();
    	try {
			estoqueBO.adicionarProduto(produto);
			fm.setSummary("Cadastro realizado com sucesso.");
			fc.addMessage("", fm);
		} catch (RemoteException e) {
			e.printStackTrace();
			fm.setSummary("Erro durante cadastro");
			fc.addMessage("", fm);
		}
    }
	@PostConstruct
    public void listarProduto(){
    	EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
    	try {
			produtos = Arrays.asList(estoqueBO.listarProdutos());
			totalProdutos = produtos.size();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
	
	@PostConstruct
	public void atualizarProduto(){
		EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
		FacesMessage fm = new FacesMessage();
    	FacesContext fc = FacesContext.getCurrentInstance();
    	try {
    		estoqueBO.atualizarProduto(produto);
			fm.setSummary("Alteracao realizada com sucesso.");
			fc.addMessage("", fm);
		} catch (RemoteException e) {
			e.printStackTrace();
			fm.setSummary("Erro durante a alteracao");
			fc.addMessage("", fm);
		}
	}
	
	@PostConstruct
	public void removerProduto(){
	EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
	produto.getCodProduto();
	try {
		estoqueBO.removerProduto(produto);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}