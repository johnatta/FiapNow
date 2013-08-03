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

import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
@Entity
@Table(name="AM_MODERADOR_GRUPO")
@SequenceGenerator(name="seqModeradorGrupo", sequenceName="SEQ_AM_MODERADOR_GRUPO", allocationSize=1)
public class ModeradorGrupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqModeradorGrupo")	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_moderador_grupo")
	private Grupo codGrupo;	

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_moderador_pessoa")
	private Pessoa codPessoa;

	public ModeradorGrupo(Grupo codGrupo, Pessoa codPessoa) {
		super();
		this.codGrupo = codGrupo;
		this.codPessoa = codPessoa;
	}
	public ModeradorGrupo(){

	}
	public Grupo getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(Grupo codGrupo) {
		this.codGrupo = codGrupo;
	}

	public Pessoa getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Pessoa codPessoa) {
		this.codPessoa = codPessoa;
	}
}
