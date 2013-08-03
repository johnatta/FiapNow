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
@Table(name="AM_ESPORTE")
@SequenceGenerator(name="seqEsporte", sequenceName="SEQ_AM_ESPORTE", allocationSize=1)
public class Esporte implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqEsporte")
	@Column(name="cod_esporte")
	private int codEsporte;
	
	@Column(nullable = false, length = 60)
	private String nome;

	public Esporte(String nome) {
		super();
		this.nome = nome;
	}
	 public Esporte(){
		 
	 }

	public int getCodEsporte() {
		return codEsporte;
	}

	public void setCodEsporte(int codEsporte) {
		this.codEsporte = codEsporte;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
