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
@Table(name="AM_EVENTO")
@SequenceGenerator(name="seqEvento", sequenceName="SEQ_AM_EVENTO", allocationSize=1)
public class Evento implements Serializable {
	
	private static final long serialVersionUID = -3560198451472921713L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqEvento")
	@Column(nullable = false)
	private int codEvento;
	
	@Column(nullable = false , length = 40)
	private String nome;
	
	@Column(name="DATA_EVENTO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private  Calendar dt_evento;
	
	@Column(name = "Telefone_Contato", length = 12)
	private String telContato;
	
	@Column(nullable = false)
	private double custo;
	
	@Column(length = 150)
	private String descricao;
	
	@Column(name="IMAGEM_EVENTO", nullable = false)
	private byte [] imgEvento;
	
	@Column(nullable = false, length = 1)
	private char privacidade;
	
	private Endereco codEndereco;
	
	private Esporte codEsporte;

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

	public Calendar getDt_evento() {
		return dt_evento;
	}

	public void setDt_evento(Calendar dt_evento) {
		this.dt_evento = dt_evento;
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

	public byte[] getImgEvento() {
		return imgEvento;
	}

	public void setImgEvento(byte[] imgEvento) {
		this.imgEvento = imgEvento;
	}

	public char getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(char privacidade) {
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
	
	

}
