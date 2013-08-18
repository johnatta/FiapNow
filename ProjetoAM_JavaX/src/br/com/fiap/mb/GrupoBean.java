package br.com.fiap.mb;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.fiap.entity.Privacidade;

@ManagedBean
@RequestScoped
public class GrupoBean {
	private int codGrupo;
	
	private String nomeGrupo;
	
	private byte [] imgGrupo;
	
	private String descricao;
	
	private Privacidade privacidade;

	private BigDecimal quantidade;
	
	public GrupoBean() {
		// TODO Auto-generated constructor stub
	}

}
