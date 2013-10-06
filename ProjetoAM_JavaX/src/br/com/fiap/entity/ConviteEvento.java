package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;

@Entity
@Table(name="AM_CONVITE_EVENTO")
@SequenceGenerator(name="seqConviteEvento", sequenceName="SEQ_AM_CONVITE_EVENTO", allocationSize=1)
public class ConviteEvento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqConviteEvento")
	@Column(name= "cod_convite")
	private int codConvite;

	@Column(nullable = false, length = 100)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name= "cod_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name= "cod_evento")
	private Evento evento;		

	public ConviteEvento(String descricao, Pessoa pessoa, Evento evento) {
		super();
		this.descricao = descricao;
		this.pessoa = pessoa;
		this.evento = evento;
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


	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
