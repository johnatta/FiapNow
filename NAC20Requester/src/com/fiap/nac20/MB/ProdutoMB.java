package com.fiap.nac20.MB;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import com.fiap.nac20.BO.EstoqueBOProxy;
import com.fiap.nac20.TO.ProdutoTO;


@ManagedBean
@RequestScoped
public class ProdutoMB implements Serializable {
	
    private ProdutoTO produto;
    private List<ProdutoTO> produtos;
    private int totalProdutos; 
    private PieChartModel estatistica;
    
    @PostConstruct
    public void inicializar(){
    	produto = new ProdutoTO();
    	
    	estatistica = new PieChartModel();
		
		EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
    	try {
    		produtos = Arrays.asList(estoqueBO.listarProdutos());
			for(ProdutoTO prod : produtos){
				estatistica.set(prod.getDescricao(), prod.getQuantidade());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	
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
	
	public PieChartModel getEstatisticaPizza() {
		return estatistica;
	}
	
	
    public PieChartModel getEstatistica() {
		return estatistica;
	}

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
    
    public void listarProduto(){
    	EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
    	try {
			produtos = Arrays.asList(estoqueBO.listarProdutos());
			totalProdutos = produtos.size();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
	
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
	
	public void removerProduto(){
	EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
	FacesMessage fm = new FacesMessage();
	FacesContext fc = FacesContext.getCurrentInstance();
	try {
		estoqueBO.removerProduto(produto);
		fm.setSummary("Exclusao realizada com sucesso.");
		fc.addMessage("", fm);
	} catch (RemoteException e) {
		e.printStackTrace();
		fm.setSummary("Erro durante a exclusao");
		fc.addMessage("", fm);
		}
	}
}