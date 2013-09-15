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

import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;

@Entity
@Table(name="AM_MENSAGEM_EVENTO")
@SequenceGenerator(name="seqMensagemEvento", sequenceName="SEQ_AM_MENSAGEM_EVENTO", allocationSize=1)
public class MensagemEvento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMensagemEvento")
	@Column(name="cod_mensagem_evento")
	private int codMensagem;

	@Column( nullable = false, length = 100)
	private String descricao;

	@Column(name = "CONFIRMACAO", nullable = false)
	private Confirmacao confirmacao;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="msg_evento_pessoa")
	private Pessoa pessoa;	

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="msg_evento")
	private Evento evento;	

	public MensagemEvento(String descricao, Confirmacao confirmacao,
			Pessoa codPessoa, Evento codEvento) {
		super();
		this.descricao = descricao;
		this.confirmacao = confirmacao;
		this.pessoa = codPessoa;
		this.evento = codEvento;
	}

	public MensagemEvento(){

	}
	
	public int getCodMensagem() {
		return codMensagem;
	}

	public void setCodMensagem(int codMensagem) {
		this.codMensagem = codMensagem;
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

	public void setCodPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
