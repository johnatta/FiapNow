package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_ENDERECO")
@SequenceGenerator(name="seqEndereco", sequenceName="SEQ_AM_ENDERECO", allocationSize=1)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqEndereco")
	private int codEndereco;
	
	@Column(nullable = false, length = 40)
	private String pais;
	
	@Column(nullable = false, length = 2)
	private String estado;

	@Column(nullable = false, length = 40)
	private String cidade;

	@Column(nullable = false, length = 40)
	private String bairro;
	
	@Column(length = 60)
	private String rua;

	@Column( length = 8)
	private int numero;
	
	@Column(length = 30)
	private String complemento;
	
	@Column(length = 8)
	private String cep;
	
	@Column(length = 10)
	private float latitude;
	
	@Column(length = 10)
	private float longitude;
	
	public Endereco(String pais, String estado, String cidade, String bairro,
			String rua, int numero, String complemento, String cep,
			float latitude, float longitude) {
		super();
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Endereco(){
		
	}


	public int getCodEndereco() {
		return codEndereco;
	}


	public void setCodEndereco(int codEndereco) {
		this.codEndereco = codEndereco;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getRua() {
		return rua;
	}


	public void setRua(String rua) {
		this.rua = rua;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}




}
