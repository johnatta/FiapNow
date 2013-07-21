package br.com.am.entity;

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

@Entity
@Table(name="AM_MENSAGEM_EVENTO")
@SequenceGenerator(name="seqMensagemEvento", sequenceName="SEQ_AM_MENSAGEM_EVENTO", allocationSize=1)
public class MensagemEvento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMensagemEvento")
	private int codMensagem;
	
	@Column( nullable = false, length = 100)
	private String descricao;
	
	@Column(nullable = false)
	private Confirmacao confirmacao;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa codPessoa;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Evento codEvento;
	
	public MensagemEvento(String descricao, Confirmacao confirmacao,
			Pessoa codPessoa, Evento codEvento) {
		super();
		this.descricao = descricao;
		this.confirmacao = confirmacao;
		this.codPessoa = codPessoa;
		this.codEvento = codEvento;
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

	public Pessoa getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Pessoa codPessoa) {
		this.codPessoa = codPessoa;
	}

	public Evento getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(Evento codEvento) {
		this.codEvento = codEvento;
	}
	
}
