package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;

@Entity
@Table(name="AM_COMENTARIO_EVENTO")
@SequenceGenerator(name="seqComentarioEvento", sequenceName="SEQ_AM_COMENTARIO_EVENTO", allocationSize=1)
public class ComentarioEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqComentarioEvento")
	@Column(name="cod_comentario_evento")
	private int codComentario;

	@Column(nullable = false, length = 300)
	private String comentario;
	
	@Column(name="DATA_HORA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar dtHora;
	
	@ManyToOne
	@JoinColumn(name="cod_evento")
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name="cod_pessoa")
	private Pessoa pessoa;
	
	public ComentarioEvento(String comentario, Calendar dtHora,
			Evento evento, Pessoa pessoa) {
		super();
		this.comentario = comentario;
		this.dtHora = dtHora;
		this.evento = evento;
		this.pessoa = pessoa;
	}

	public ComentarioEvento(){

	}

	public int getCodComentario() {
		return codComentario;
	}

	public void setCodComentario(int codComentario) {
		this.codComentario = codComentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Calendar getDtHora() {
		return dtHora;
	}

	public void setDtHora(Calendar dtHora) {
		this.dtHora = dtHora;
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

}
