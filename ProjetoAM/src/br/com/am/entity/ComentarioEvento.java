package br.com.am.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AM_COMENTARIO_EVENTO")
@SequenceGenerator(name="seqComentarioEvento", sequenceName="SEQ_AM_COMENTARIO_EVENTO", allocationSize=1)
public class ComentarioEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqComentarioEvento")
	private int codComentario;
	
	@Column(nullable = false, length = 300)
	private String comentario;
	

	@Column(name="DATA_HORA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar dtHora;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Evento codEvento;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa codPessoa;

	
	public Calendar getDtHora() {
		return dtHora;
	}

	public void setDtHora(Calendar dtHora) {
		this.dtHora = dtHora;
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
