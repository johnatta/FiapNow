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
@Table(name="AM_PEDIDO_EVENTO")
@SequenceGenerator(name="seqPedidoEvento", sequenceName="SEQ_AM_PEDIDO_EVENTO", allocationSize=1)
public class PedidoEvento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqPedidoEvento")
	@Column(name="cod_pedido_evento")
	private int codPedido;

	@Column( nullable = false, length = 100)
	private String descricao;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_pedido_evento_pessoa")
	private Pessoa pessoa;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_evento_ped")
	private Evento evento;

	public PedidoEvento(String descricao, Pessoa codPessoa, Evento codEvento) {
		super();
		this.descricao = descricao;
		this.pessoa = codPessoa;
		this.evento = codEvento;
	}

	public PedidoEvento(){

	}

	public int getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
