package br.com.am.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_CONVITE_EVENTO")
@SequenceGenerator(name="seqConviteEvento", sequenceName="SEQ_AM_CONVITE_EVENTO", allocationSize=1)
public class ConviteEvento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqConviteEvento")
	private int codConvite;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa codPessoa;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Evento codEvento;

	public ConviteEvento( String descricao, Pessoa codPessoa,
			Evento codEvento) {
		super();
		this.descricao = descricao;
		this.codPessoa = codPessoa;
		this.codEvento = codEvento;
	}
	 
	public ConviteEvento() {
		
	}

	public int getCodConvite() {
		return codConvite;
	}

	public void setCodConvite(int codConvite) {
		this.codConvite = codConvite;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Pessoa codPessoa) {
		this.codPessoa = codPessoa;
	}

	public Evento getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(Evento codEvento) {
		this.codEvento = codEvento;
	}
	
}
