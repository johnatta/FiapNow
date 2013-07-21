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
@Table(name="AM_GRUPO")
@SequenceGenerator(name="seqGrupo", sequenceName="SEQ_AM_GRUPO", allocationSize=1)
public class Grupo implements Serializable {
	
	private static final long serialVersionUID = -1460947989648440636L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqGrupo")
	@Column(nullable = false)
	private int codGrupo;
	
	@Column(nullable = false , length = 50)
	private String nomeGrupo;
	
	
	// no mapeamento esta como float checar com o ariel depois
	@Column(name="IMAGEM_GRUPO", nullable = false)
	private byte [] imgGrupo;
	
	
	@Column(nullable = false , length = 300)
	private String descricao;
	
	@Column(nullable = false , length = 1)
	private char privacidade;

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public byte[] getImgGrupo() {
		return imgGrupo;
	}

	public void setImgGrupo(byte[] imgGrupo) {
		this.imgGrupo = imgGrupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(char privacidade) {
		this.privacidade = privacidade;
	}
	

}
