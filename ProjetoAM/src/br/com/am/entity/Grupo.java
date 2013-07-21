package br.com.am.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AM_GRUPO")
@SequenceGenerator(name="seqGrupo", sequenceName="SEQ_AM_GRUPO", allocationSize=1)
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqGrupo")
	private int codGrupo;
	
	@Column(nullable = false , length = 50)
	private String nomeGrupo;
	
	@Column(name="IMAGEM_GRUPO", nullable = false)
	@Lob
	private byte [] imgGrupo;
	
	@Column(nullable = false , length = 300)
	private String descricao;
	
	@Column(nullable = false)
	private Privacidade privacidade;
	
	@Column
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="AM_GRUPO_ESPORTE",
	joinColumns={@JoinColumn(name="COD_GRUPO")},
	inverseJoinColumns={@JoinColumn(name="COD_ESPORTE")})
	private List<Esporte> esportes;
	
	public List<Esporte> getEsportes() {
		return esportes;
	}

	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}

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

	public Privacidade getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(Privacidade privacidade) {
		this.privacidade = privacidade;
	}



}
