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
	private Pessoa codPessoa;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_evento_ped")
	private Evento codEvento;

	public PedidoEvento(String descricao, Pessoa codPessoa, Evento codEvento) {
		super();
		this.descricao = descricao;
		this.codPessoa = codPessoa;
		this.codEvento = codEvento;
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