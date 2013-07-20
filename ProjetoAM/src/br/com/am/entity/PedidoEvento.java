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
@Table(name="AM_PEDIDO_EVENTO")
@SequenceGenerator(name="seqPedidoEvento", sequenceName="SEQ_AM_PEDIDO_EVENTO", allocationSize=1)
public class PedidoEvento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seqPedidoEvento")
	@Column(name="COD_PEDIDO")
	private int codPedido;
	
	@Column
	private String descricao;
	
	@Column(name="COD_PESSOA")
	private Pessoa codPessoa;
	
	@Column(name="COD_EVENTO")
	private Evento codEvento;

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
