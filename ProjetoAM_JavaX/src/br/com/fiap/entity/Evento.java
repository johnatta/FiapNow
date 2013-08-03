package br.com.fiap.entity;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Privacidade;


@Entity
@Table(name="AM_EVENTO")
@SequenceGenerator(name="seqEvento", sequenceName="SEQ_AM_EVENTO", allocationSize=1)
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqEvento")
	@Column(name="cod_evento")
	private int codEvento;

	@Column(nullable = false , length = 40)
	private String nome;

	@Column(name="DATA_EVENTO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar dtEvento;

	@Column(name = "Telefone_Contato", length = 12)
	private String telContato;

	@Column(nullable = false)
	private double custo;

	@Column(length = 150)
	private String descricao;

	@Column(name="PRIVACIDADE",nullable = false)
	private Privacidade privacidade;	

	@Column(name="IMAGEM_EVENTO", nullable = false)
	@Lob
	private byte [] imgEvento;


	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_evento_endereco")
	private Endereco codEndereco;	

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_evento_esporte")
	private Esporte codEsporte;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="AM_EVENTO_GRUPO",
	joinColumns={@JoinColumn(name="COD_EVENTO")},
	inverseJoinColumns={@JoinColumn(name="COD_GRUPO")})
	private List<Grupo> grupos;

	public Evento(String nome, Calendar dtEvento, String telContato,
			double custo, String descricao, Privacidade privacidade,
			Endereco codEndereco, Esporte codEsporte, List<Grupo> grupos,
			byte[] imgEvento) {
		super();
		this.nome = nome;
		this.dtEvento = dtEvento;
		this.telContato = telContato;
		this.custo = custo;
		this.descricao = descricao;
		this.privacidade = privacidade;
		this.codEndereco = codEndereco;
		this.codEsporte = codEsporte;
		this.grupos = grupos;
		this.imgEvento = imgEvento;
	}

	public Evento(){

	}

	public int getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(int codEvento) {
		this.codEvento = codEvento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDtEvento() {
		return dtEvento;
	}

	public void setDtEvento(Calendar dtEvento) {
		this.dtEvento = dtEvento;
	}

	public String getTelContato() {
		return telContato;
	}

	public void setTelContato(String telContato) {
		this.telContato = telContato;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
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

	public Endereco getCodEndereco() {
		return codEndereco;
	}

	public void setCodEndereco(Endereco codEndereco) {
		this.codEndereco = codEndereco;
	}

	public Esporte getCodEsporte() {
		return codEsporte;
	}

	public void setCodEsporte(Esporte codEsporte) {
		this.codEsporte = codEsporte;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public byte[] getImgEvento() {
		return imgEvento;
	}

	public void setImgEvento(byte[] imgEvento) {
		this.imgEvento = imgEvento;
	}
}


