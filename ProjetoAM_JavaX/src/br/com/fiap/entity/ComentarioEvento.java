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
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="coment_evento")
	private Evento codEvento;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="coment_evento_pessoa")
	private Pessoa codPessoa;
	
	public ComentarioEvento(String comentario, Calendar dtHora,
			Evento codEvento, Pessoa codPessoa) {
		super();
		this.comentario = comentario;
		this.dtHora = dtHora;
		this.codEvento = codEvento;
		this.codPessoa = codPessoa;
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

	public Evento getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(Evento codEvento) {
		this.codEvento = codEvento;
	}

	public Pessoa getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Pessoa codPessoa) {
		this.codPessoa = codPessoa;
	}
}
