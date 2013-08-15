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
@Table(name="AM_PEDIDO_EVENTO")
@SequenceGenerator(name="seqPedidoEvento", sequenceName="SEQ_AM_PEDIDO_EVENTO", allocationSize=1)
public class PedidoEvento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqPedidoEvento")
	private int codPedido;
	
	@Column( nullable = false, length = 100)
	private String descricao;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
	private Pessoa codPessoa;
	
	@Column
	@ManyToOne(cascade=CascadeType.ALL)
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