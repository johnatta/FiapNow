/**
 * EstoqueBO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fiap.nac20.BO;

public interface EstoqueBO extends java.rmi.Remote {
    public com.fiap.nac20.TO.ProdutoTO consultarProduto(int codProduto) throws java.rmi.RemoteException;
    public void adicionarProduto(com.fiap.nac20.TO.ProdutoTO produtoTO) throws java.rmi.RemoteException;
    public com.fiap.nac20.TO.ProdutoTO[] listarProdutos() throws java.rmi.RemoteException;
    public void removerProduto(com.fiap.nac20.TO.ProdutoTO produtoTO) throws java.rmi.RemoteException;
    public java.lang.String[] buscarTodasDescricoesProdutos(java.lang.String descricao) throws java.rmi.RemoteException;
}
