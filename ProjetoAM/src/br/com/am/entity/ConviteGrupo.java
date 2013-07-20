package br.com.am.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_CONVITE_GRUPO")
@SequenceGenerator(name="seqConviteGrupo", sequenceName="SEQ_AM_CONVITE_GRUPO", allocationSize=1)
public class ConviteGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqConviteGrupo")
	@Column(name="COD_Convite")
	private int codConvite;
	
	@Column
	private String descricao;
	
	@Column(name="COD_PESSOA")
	private Pessoa codPessoa;
	
	@Column(name="COD_GRUPO")
	private Grupo codGrupo;

	public int getCodConvite() {
		return codConvite;
	}

	public void setCodConvite(int codConvite) {
		this.codConvite= codConvite;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
