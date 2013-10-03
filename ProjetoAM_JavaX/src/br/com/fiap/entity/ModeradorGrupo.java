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
	@Column(name="cod_moderador_grupo")
	private int codModeradorGrupo;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_moderador_grupo")
	private Grupo grupo;	

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_moderador_pessoa")
	private Pessoa pessoa;

	public ModeradorGrupo(int codModeradorGrupo, Grupo grupo, Pessoa pessoa) {
		super();
		this.codModeradorGrupo = codModeradorGrupo;
		this.grupo = grupo;
		this.pessoa = pessoa;
	}
	public ModeradorGrupo(){

	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public int getCodModeradorGrupo() {
		return codModeradorGrupo;
	}
	public void setCodModeradorGrupo(int codModeradorGrupo) {
		this.codModeradorGrupo = codModeradorGrupo;
	}
	
}
