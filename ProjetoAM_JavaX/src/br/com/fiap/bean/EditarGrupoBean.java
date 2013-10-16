package br.com.fiap.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.bo.GrupoBO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Privacidade;

public class EditarGrupoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private int codGrupo;
	private Grupo grupo;
	private GrupoDAO gruDAO;
	private List<Privacidade> privs;
	private boolean primeiraVezInfoGrupo;
	private GrupoBO grupoBO;

	/**
	 * Efetua a renderiza��o do conte�do que deve estar pre-renderizado por meio do codGrupo que � 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public EditarGrupoBean(Grupo grupo){
		gruDAO = new GrupoDAOImpl(em);
		this.grupo = grupo;
		this.privs = Arrays.asList(this.grupo.getPrivacidade().values());
	}

	/**
	 * Salva as informa��es editadas do grupo
	 * @return p�ginas do os grupos da aplica��o
	 * @author Graziele Vasconcelos
	 */
	public String salvarEdicao(){
		gruDAO.update(grupo);
		return "grupos.xhtml";
	}

	public void realizarUpload(FileUploadEvent event) {
		grupoBO = new GrupoBO();
		grupoBO.uploadFoto(grupo, event);
	}
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Privacidade> getPrivs() {
		return privs;
	}

	public void setPrivs(List<Privacidade> privs) {
		this.privs = privs;
	}

	public boolean isPrimeiraVezInfoGrupo() {
		return primeiraVezInfoGrupo;
	}

	public void setPrimeiraVezInfoGrupo(boolean primeiraVezInfoGrupo) {
		this.primeiraVezInfoGrupo = primeiraVezInfoGrupo;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}
	
}
