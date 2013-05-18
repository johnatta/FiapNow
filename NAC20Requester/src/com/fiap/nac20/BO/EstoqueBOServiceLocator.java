/**
 * EstoqueBOServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fiap.nac20.BO;

public class EstoqueBOServiceLocator extends org.apache.axis.client.Service implements com.fiap.nac20.BO.EstoqueBOService {

    public EstoqueBOServiceLocator() {
    }


    public EstoqueBOServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EstoqueBOServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EstoqueBO
    private java.lang.String EstoqueBO_address = "http://localhost:8080/Nac20/services/EstoqueBO";

    public java.lang.String getEstoqueBOAddress() {
        return EstoqueBO_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EstoqueBOWSDDServiceName = "EstoqueBO";

    public java.lang.String getEstoqueBOWSDDServiceName() {
        return EstoqueBOWSDDServiceName;
    }

    public void setEstoqueBOWSDDServiceName(java.lang.String name) {
        EstoqueBOWSDDServiceName = name;
    }

    public com.fiap.nac20.BO.EstoqueBO getEstoqueBO() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EstoqueBO_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEstoqueBO(endpoint);
    }

    public com.fiap.nac20.BO.EstoqueBO getEstoqueBO(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.fiap.nac20.BO.EstoqueBOSoapBindingStub _stub = new com.fiap.nac20.BO.EstoqueBOSoapBindingStub(portAddress, this);
            _stub.setPortName(getEstoqueBOWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEstoqueBOEndpointAddress(java.lang.String address) {
        EstoqueBO_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.fiap.nac20.BO.EstoqueBO.class.isAssignableFrom(serviceEndpointInterface)) {
                com.fiap.nac20.BO.EstoqueBOSoapBindingStub _stub = new com.fiap.nac20.BO.EstoqueBOSoapBindingStub(new java.net.URL(EstoqueBO_address), this);
                _stub.setPortName(getEstoqueBOWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EstoqueBO".equals(inputPortName)) {
            return getEstoqueBO();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://BO.nac20.fiap.com", "EstoqueBOService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://BO.nac20.fiap.com", "EstoqueBO"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EstoqueBO".equals(portName)) {
            setEstoqueBOEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
