package br.com.am.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_CONVITE_EVENTO")
@SequenceGenerator(name="seqConviteEvento", sequenceName="SEQ_AM_CONVITE_EVENTO", allocationSize=1)
public class ConviteEvento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqConviteEvento")
	@Column(name="COD_CONVITE")
	private int codConvite;
	
	@Column
	private String descricao;
	
	@Column(name="COD_PESSOA")
	private Pessoa codPessoa;
	
	@Column(name="COD_EVENTO")
	private Evento codEvento;

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
