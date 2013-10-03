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
@Table(name="AM_MODERADOR_EVENTO")
@SequenceGenerator(name="seqModeradorEvento", sequenceName="SEQ_AM_MODERADOR_EVENTO", allocationSize=1)
public class ModeradorEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqModeradorEvento")
	@Column(name="cod_moderador_evento")
	private int codModeradorEvento;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_moderador_evento")
	private Evento evento;		

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="moderador_evento_pessoa")
	private Pessoa pessoa;

	public ModeradorEvento(int codModeradorEvento, Evento evento, Pessoa pessoa) {
		super();
		this.codModeradorEvento = codModeradorEvento;
		this.evento = evento;
		this.pessoa = pessoa;
	}

	public ModeradorEvento(){

	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getCodModeradorEvento() {
		return codModeradorEvento;
	}

	public void setCodModeradorEvento(int codModeradorEvento) {
		this.codModeradorEvento = codModeradorEvento;
	}

	
}
