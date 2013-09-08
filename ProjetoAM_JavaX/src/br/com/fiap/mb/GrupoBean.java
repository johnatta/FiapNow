package br.com.fiap.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.Grupo;

@ManagedBean
@RequestScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private Grupo grupo;
	private int codGrupo;
	private GrupoDAO grupoDAO;
	
	
	
	public void buscaGrupo(){
		grupo = grupoDAO.buscarInfoGrupo(codGrupo);
	}
	
	@PostConstruct
	public void obterIdGrupo() {
		grupoDAO = new GrupoDAOImpl(em);
		
		/*
		UIComponent link = evento.getComponent();
		UIParameter param = (UIParameter) link.findComponent("obterIdGrupo");
		grupo.setCodGrupo((Integer) param.getValue());
		System.out.println("O CODIGO DO GRUPO E... : " + grupo.getCodGrupo());
		System.out.println();
		*/
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}
	
	
	
}
