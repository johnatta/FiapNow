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
@Table(name="AM_PEDIDO_GRUPO")
@SequenceGenerator(name="seqPedidoGrupo", sequenceName="SEQ_AM_PEDIDO_GRUPO", allocationSize=1)
public class PedidoGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPedidoGrupo")
	@Column(name="COD_PEDIDO")
	private int codPedidoGrupo;
	
	@Column
	private String descricao;
	
	@Column(name="COD_PESSOA")
	private Pessoa codPessoa;
	
	@Column(name="COD_GRUPO")
	private Grupo codGrupo;

	public int getCodPedidoGrupo() {
		return codPedidoGrupo;
	}

	public void setCodPedidoGrupo(int codPedidoGrupo) {
		this.codPedidoGrupo = codPedidoGrupo;
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

	public Grupo getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(Grupo codGrupo) {
		this.codGrupo = codGrupo;
	}
	
}
