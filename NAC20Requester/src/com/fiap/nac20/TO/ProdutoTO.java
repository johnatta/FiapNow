/**
 * ProdutoTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fiap.nac20.TO;

public class ProdutoTO  implements java.io.Serializable {
    private java.lang.String campanhaPromocional;

    private int codProduto;

    private java.lang.String descricao;

    private double preco;

    private int quantidade;

    public ProdutoTO() {
    }

    public ProdutoTO(
           java.lang.String campanhaPromocional,
           int codProduto,
           java.lang.String descricao,
           double preco,
           int quantidade) {
           this.campanhaPromocional = campanhaPromocional;
           this.codProduto = codProduto;
           this.descricao = descricao;
           this.preco = preco;
           this.quantidade = quantidade;
    }


    /**
     * Gets the campanhaPromocional value for this ProdutoTO.
     * 
     * @return campanhaPromocional
     */
    public java.lang.String getCampanhaPromocional() {
        return campanhaPromocional;
    }


    /**
     * Sets the campanhaPromocional value for this ProdutoTO.
     * 
     * @param campanhaPromocional
     */
    public void setCampanhaPromocional(java.lang.String campanhaPromocional) {
        this.campanhaPromocional = campanhaPromocional;
    }


    /**
     * Gets the codProduto value for this ProdutoTO.
     * 
     * @return codProduto
     */
    public int getCodProduto() {
        return codProduto;
    }


    /**
     * Sets the codProduto value for this ProdutoTO.
     * 
     * @param codProduto
     */
    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }


    /**
     * Gets the descricao value for this ProdutoTO.
     * 
     * @return descricao
     */
    public java.lang.String getDescricao() {
        return descricao;
    }


    /**
     * Sets the descricao value for this ProdutoTO.
     * 
     * @param descricao
     */
    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }


    /**
     * Gets the preco value for this ProdutoTO.
     * 
     * @return preco
     */
    public double getPreco() {
        return preco;
    }


    /**
     * Sets the preco value for this ProdutoTO.
     * 
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }


    /**
     * Gets the quantidade value for this ProdutoTO.
     * 
     * @return quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }


    /**
     * Sets the quantidade value for this ProdutoTO.
     * 
     * @param quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProdutoTO)) return false;
        ProdutoTO other = (ProdutoTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.campanhaPromocional==null && other.getCampanhaPromocional()==null) || 
             (this.campanhaPromocional!=null &&
              this.campanhaPromocional.equals(other.getCampanhaPromocional()))) &&
            this.codProduto == other.getCodProduto() &&
            ((this.descricao==null && other.getDescricao()==null) || 
             (this.descricao!=null &&
              this.descricao.equals(other.getDescricao()))) &&
            this.preco == other.getPreco() &&
            this.quantidade == other.getQuantidade();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCampanhaPromocional() != null) {
            _hashCode += getCampanhaPromocional().hashCode();
        }
        _hashCode += getCodProduto();
        if (getDescricao() != null) {
            _hashCode += getDescricao().hashCode();
        }
        _hashCode += new Double(getPreco()).hashCode();
        _hashCode += getQuantidade();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProdutoTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://TO.nac20.fiap.com", "ProdutoTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campanhaPromocional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://TO.nac20.fiap.com", "campanhaPromocional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://TO.nac20.fiap.com", "codProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://TO.nac20.fiap.com", "descricao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://TO.nac20.fiap.com", "preco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://TO.nac20.fiap.com", "quantidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
