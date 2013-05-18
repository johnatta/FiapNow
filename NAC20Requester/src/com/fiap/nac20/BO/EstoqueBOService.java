/**
 * EstoqueBOService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fiap.nac20.BO;

public interface EstoqueBOService extends javax.xml.rpc.Service {
    public java.lang.String getEstoqueBOAddress();

    public com.fiap.nac20.BO.EstoqueBO getEstoqueBO() throws javax.xml.rpc.ServiceException;

    public com.fiap.nac20.BO.EstoqueBO getEstoqueBO(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
