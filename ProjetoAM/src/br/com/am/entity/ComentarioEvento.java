package br.com.am.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AM_COMENTARIO_EVENTO")
@SequenceGenerator(name="seqComentarioEvento", sequenceName="SEQ_AM_COMENTARIO_EVENTO", allocationSize=1)
public class ComentarioEvento implements Serializable {

	private static final long serialVersionUID = -2020128513392338462L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqComentarioEvento")
	@Column(nullable = false)
	private int codComentario;
	
	@Column(nullable = false, length = 300)
	private String comentario;
	

	@Column(name="DATA_HORA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar dt_hora;
	
	private Evento codEvento;
	
	private Pessoa codPessoa;

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

	public Calendar getDt_hora() {
		return dt_hora;
	}

	public void setDt_hora(Calendar dt_hora) {
		this.dt_hora = dt_hora;
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
