package br.com.fiap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

@Entity
@Table(name="AM_GRUPO")
@SequenceGenerator(name="seqGrupo", sequenceName="SEQ_AM_GRUPO", allocationSize=1)
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqGrupo")
	@Column(name="cod_grupo")
	private int codGrupo;
	
	@Column(nullable = false , name="nome_grupo", length = 40)
	private String nomeGrupo;
	
	@Column(name="IMAGEM_GRUPO", nullable = false)
	@Lob
	private byte [] imgGrupo;
	
	@Column(nullable = false , length = 300)
	private String descricao;
	
	@Column(name = "PRIVACIDADE", nullable = false)
	private Privacidade privacidade;

	@Transient
	private BigDecimal quantidade;
	
	@Transient
	private StreamedContent foto;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="AM_GRUPO_ESPORTE",
	joinColumns={@JoinColumn(name="COD_GRUPO")},
	inverseJoinColumns={@JoinColumn(name="COD_ESPORTE")})
	private List<Esporte> esportes;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="COD_ADM", nullable = false)
	private Pessoa adm;
	
	public Grupo(String nomeGrupo, byte[] imgGrupo, String descricao,
			Privacidade privacidade, List<Esporte> esportes, Pessoa adm) {
		super();
		this.nomeGrupo = nomeGrupo;
		this.imgGrupo = imgGrupo;
		this.descricao = descricao;
		this.privacidade = privacidade;
		this.esportes = esportes;
		this.adm = adm;
	}

	public Grupo(){
		
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

	public List<Esporte> getEsportes() {
		return esportes;
	}

	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public Pessoa getAdm() {
		return adm;
	}

	public void setAdm(Pessoa adm) {
		this.adm = adm;
	}
	public StreamedContent getFoto() {
		return foto;
	}
	
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
}

	