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

import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@Entity
@Table(name="AM_PEDIDO_GRUPO")
@SequenceGenerator(name="seqPedidoGrupo", sequenceName="SEQ_AM_PEDIDO_GRUPO", allocationSize=1)
public class PedidoGrupo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPedidoGrupo")
	@Column(name="cod_pedido_grupo")
	private int codPedidoGrupo;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_pedido_grupo_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_grupo_pedido")
	private Grupo grupo;		
	
	public PedidoGrupo(String descricao, Pessoa codPessoa, Grupo codGrupo) {
		super();
		this.descricao = descricao;
		this.pessoa = codPessoa;
		this.grupo = codGrupo;
	}

	public PedidoGrupo(){
		
	}

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
	
}
