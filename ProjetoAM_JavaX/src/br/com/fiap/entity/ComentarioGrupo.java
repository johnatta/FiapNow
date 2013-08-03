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

import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
@Entity
@Table(name="AM_COMENTARIO_GRUPO")
@SequenceGenerator(name="seqComentarioGrupo", sequenceName="SEQ_AM_COMENTARIO_GRUPO", allocationSize=1)
public class ComentarioGrupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqComentarioGrupo")
	@Column(name="cod_comentario_grupo")
	private int codComentario;

	@Column(nullable = false, length = 300)
	private String comentario;	
	
	//TimeStamp  data/Hora
	@Column(name="DATA_HORA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar dataHora;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="coment_grupo_pessoa")
	private Pessoa codPessoa;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="coment_cod_grupo")
	private Grupo codGrupo;

	public ComentarioGrupo(String comentario, Calendar dataHora,
			Pessoa codPessoa, Grupo codGrupo) {
		super();
		this.comentario = comentario;
		this.dataHora = dataHora;
		this.codPessoa = codPessoa;
		this.codGrupo = codGrupo;
	}

	public ComentarioGrupo() {

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

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public Pessoa getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Pessoa codPessoa) {
		this.codPessoa = codPessoa;
	}

	public Grupo getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(Grupo codGrupo) {
		this.codGrupo = codGrupo;
	}
}
