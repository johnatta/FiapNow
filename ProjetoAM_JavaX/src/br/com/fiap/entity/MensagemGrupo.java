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
import javax.persistence.Transient;

@Entity
@Table(name="AM_MENSAGEM_GRUPO")
@SequenceGenerator(name="seqMensagemGrupo", sequenceName="SEQ_AM_MENSAGEM_GRUPO", allocationSize=1)
public class MensagemGrupo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMensagemGrupo")
	@Column(name="cod_mensagem_grupo")
	private int codMensagem;
	
	@Column(nullable = false, length = 30)
	private String titulo;

	@Column( nullable = false, length = 100)
	private String descricao;
	
	@Column(name ="CONFIRMACAO", nullable = false)
	private Confirmacao confirmacao;	
	
	@ManyToOne
	@JoinColumn(name="cod_pessoa")
	private Pessoa pessoa;	
	
	@ManyToOne
	@JoinColumn(name="cod_grupo")
	private Grupo grupo;
	
	@Transient
	private String icon;
	
	public MensagemGrupo(String descricao, Confirmacao confirmacao,
			br.com.fiap.entity.Pessoa pessoa,
			br.com.fiap.entity.Grupo grupo) {
		super();
		this.descricao = descricao;
		this.confirmacao = confirmacao;
		this.pessoa = pessoa;
		this.grupo = grupo;
	}

	public MensagemGrupo(){

	}
	
	public int getCodMensagem() {
		return codMensagem;
	}

	public void setCodMensagem(int codMensagem) {
		this.codMensagem = codMensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Confirmacao getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(Confirmacao confirmacao) {
		this.confirmacao = confirmacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
	
