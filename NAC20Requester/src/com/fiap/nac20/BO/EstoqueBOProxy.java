package com.fiap.nac20.BO;

public class EstoqueBOProxy implements com.fiap.nac20.BO.EstoqueBO {
  private String _endpoint = null;
  private com.fiap.nac20.BO.EstoqueBO estoqueBO = null;
  
  public EstoqueBOProxy() {
    _initEstoqueBOProxy();
  }
  
  public EstoqueBOProxy(String endpoint) {
    _endpoint = endpoint;
    _initEstoqueBOProxy();
  }
  
  private void _initEstoqueBOProxy() {
    try {
      estoqueBO = (new com.fiap.nac20.BO.EstoqueBOServiceLocator()).getEstoqueBO();
      if (estoqueBO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)estoqueBO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)estoqueBO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (estoqueBO != null)
      ((javax.xml.rpc.Stub)estoqueBO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.fiap.nac20.BO.EstoqueBO getEstoqueBO() {
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    return estoqueBO;
  }
  
  public com.fiap.nac20.TO.ProdutoTO consultarProduto(int codProduto) throws java.rmi.RemoteException{
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    return estoqueBO.consultarProduto(codProduto);
  }
  
  public void adicionarProduto(com.fiap.nac20.TO.ProdutoTO produtoTO) throws java.rmi.RemoteException{
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    estoqueBO.adicionarProduto(produtoTO);
  }
  
  public com.fiap.nac20.TO.ProdutoTO[] listarProdutos() throws java.rmi.RemoteException{
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    return estoqueBO.listarProdutos();
  }
  
  public void removerProduto(com.fiap.nac20.TO.ProdutoTO produtoTO) throws java.rmi.RemoteException{
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    estoqueBO.removerProduto(produtoTO);
  }
  
  public java.lang.String[] buscarTodasDescricoesProdutos(java.lang.String descricao) throws java.rmi.RemoteException{
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    return estoqueBO.buscarTodasDescricoesProdutos(descricao);
  }
  
  public void atualizarProduto(com.fiap.nac20.TO.ProdutoTO produtoTO) throws java.rmi.RemoteException{
    if (estoqueBO == null)
      _initEstoqueBOProxy();
    estoqueBO.atualizarProduto(produtoTO);
  }
  
  
}