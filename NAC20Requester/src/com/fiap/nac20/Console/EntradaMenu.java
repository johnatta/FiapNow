package com.fiap.nac20.Console;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fiap.nac20.BO.EstoqueBOProxy;
import com.fiap.nac20.Property.PropertySingleton;
import com.fiap.nac20.TO.ProdutoTO;

public class EntradaMenu {
 private  static Logger	log = LoggerFactory.getLogger(EntradaMenu.class);

	public static void main(String[] args) {
		EstoqueBOProxy estoqueBO = new EstoqueBOProxy();
		Scanner sc = new Scanner(System.in);
		int opcao;
		int fim = 0;
		int menu;
		DateFormat dfmt = DateFormat.getDateInstance(DateFormat.MEDIUM);
		

		
		do {
			
			
			System.out.println(PropertySingleton.getInstance().getProperty("nomeFilial"));
			System.out.println(PropertySingleton.getInstance().getProperty("nomeEmpresa"));
			System.out.println(PropertySingleton.getInstance().getProperty("endereco"));
			System.out.println();
			System.out.println("NAC20                            Data: " + dfmt.format(Calendar.getInstance().getTimeInMillis()));
			System.out.println("**************************************");
			System.out.println();
			System.out.println("Menu");
			System.out.println(" ");
			System.out.println
			("1 – Pesquisa de Produtos \n"  +
					"2 – Inclusão de Novo Produto \n" +
					"3 – Remoção de Produto \n" + 
					"4 – Listagem de Produtos \n" + 
					"0 – Encerrar a Aplicação \n");
			opcao = sc.nextInt();

		switch (opcao) {
		case 0:
			System.out.println("A aplicação será encerrada");
			sc.close();
			fim = 1;
			break;
		case 1:
			System.out.println("Código do produto: ");
			ProdutoTO prodCons = new ProdutoTO();
			int codProd = sc.nextInt(); 
			prodCons.setCodProduto(codProd);
		
			
			
			DecimalFormat df = new DecimalFormat("R$ #,#0.00");
			
			try {
				
				ProdutoTO prodResposta = estoqueBO.consultarProduto(codProd);
				System.out.println(df.format(prodResposta.getPreco()));
				System.out.println(prodResposta.getQuantidade());
				System.out.println(prodResposta.getCampanhaPromocional());
				System.out.println(prodResposta.getDescricao());
				 System.out.println();  
	             System.out.println ("Pressine '1' e retorne ao menu principal"); 
	              menu = sc.nextInt();
	              
	              for (int i=0 ; i < 50 ; i++) {
	  				System.out.println();
	  				}
	  			
				break;
	   
				
			} catch (RemoteException e1) {

				e1.printStackTrace();
			}
			 
            
			
		
		case 2:
			
			System.out.println("2 – Inclusão de Novo Produto"); //log.info("Obtendo codigo do produto");

			ProdutoTO prod = new ProdutoTO();
			System.out.println("Preco: ");
			Double preco = sc.nextDouble(); 
			prod.setPreco(preco);
			
			System.out.println("Quantidade: ");
			int qtd = sc.nextInt();
			prod.setQuantidade(qtd);

			System.out.println("Campanha Promocional: ");
			String campPromo  = sc.next();
			prod.setCampanhaPromocional(campPromo);
			
			System.out.println("Descricao: ");
			String desc = sc.next();
			prod.setDescricao(desc);
			
			try {
				estoqueBO.adicionarProduto(prod);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("3 – Remoção de Produto");
			
			System.out.println("Codigo do produto: ");
			ProdutoTO remoProd = new ProdutoTO();
			int codRemover = sc.nextInt();
			remoProd.setCodProduto(codRemover);
			try {
				estoqueBO.removerProduto(remoProd);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case 4:
			System.out.println("4 – Listagem de Produtos");
			
			try {
				for(ProdutoTO listaProd : estoqueBO.listarProdutos()) {
					System.out.println("Código : " + listaProd.getCodProduto());
					System.out.println("Descricao: " + listaProd.getDescricao());
					System.out.println("Preco: "+ listaProd.getPreco());
					System.out.println("Quantidade: " + listaProd.getQuantidade());
					System.out.println("Campanha Promocional : " + listaProd.getCampanhaPromocional());
					System.out.println("    -              -            -           ");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		default:
			System.out.printf("Você digitou uma opção inválida."); 
			
			System.out.println
			   ("1 – Pesquisa de Produtos \n"  +
				"2 – Inclusão de Novo Produto \n" +
				"3 – Remoção de Produto \n" + 
				"4 – Listagem de Produtos \n" + 
				"0 – Encerrar a Aplicação \n");
			opcao = sc.nextInt();
		}
	} while (fim == 0);
		
	}

	
}