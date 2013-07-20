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
@Table(name="AM_MENSAGEM_GRUPO")
@SequenceGenerator(name="seqMensagemGRUPO", sequenceName="SEQ_AM_MENSAGEM_GRUPO", allocationSize=1)
public class MensagemGrupo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMensagemGRUPO")
	@Column(name="COD_MENSAGEM")
	private int codMensagem;
	
	@Column
	private String descricao;
	
	@Column
	private Confirmacao confirmacao;
	
	@Column(name="COD_PESSOA")
	private Pessoa codPessoa;
	
	@Column(name="COD_GRUPO")
	private Grupo codGrupo;
	
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

	public Grupo getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(Grupo codGrupo) {
		this.codGrupo= codGrupo;
	}
	
}
