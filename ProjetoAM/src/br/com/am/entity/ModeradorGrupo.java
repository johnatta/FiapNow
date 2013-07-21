package br.com.am.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="AM_MODERADOR_GRUPO")
@SequenceGenerator(name="seqModeradorGrupo", sequenceName="SEQ_AM_MODERADOR_GRUPO", allocationSize=1)
public class ModeradorGrupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private Grupo codGrupo;
	
	@Column
	private Pessoa codPessoa;

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
