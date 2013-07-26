package br.com.fiap.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@Entity
@Table(name="AM_MENSAGEM_GRUPO")
@SequenceGenerator(name="seqMensagemGrupo", sequenceName="SEQ_AM_MENSAGEM_GRUPO", allocationSize=1)
public class MensagemGrupo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMensagemGrupo")
	private int codMensagem;

	@Column( nullable = false, length = 100)
	private String descricao;
	
	@Column(name ="CONFIRMACAO", nullable = false)
	private Confirmacao confirmacao;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa codPessoa;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Grupo codGrupo;
	
	public MensagemGrupo(String descricao, Confirmacao confirmacao,
			br.com.fiap.entity.Pessoa codPessoa,
			br.com.fiap.entity.Grupo codGrupo) {
		super();
		this.descricao = descricao;
		this.confirmacao = confirmacao;
		this.codPessoa = codPessoa;
		this.codGrupo = codGrupo;
	}

	public MensagemGrupo(){

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
	