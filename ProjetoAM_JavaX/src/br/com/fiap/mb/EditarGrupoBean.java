package br.com.fiap.mb;

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
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Privacidade;

@ManagedBean
@SessionScoped
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


	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public void infoGrupo(){
		if(primeiraVezInfoGrupo){
			primeiraVezInfoGrupo = false;
			grupo = gruDAO.searchByID(codGrupo);
			this.privs = Arrays.asList(grupo.getPrivacidade().values());
		}
	}

	@PostConstruct
	public void onInit() {
		gruDAO = new GrupoDAOImpl(em);
		primeiraVezInfoGrupo = true;
	}

	/**
	 * Salva as informações editadas do grupo
	 * @return páginas do os grupos da aplicação
	 * @author Graziele Vasconcelos
	 */
	public String salvarEdicao(){
		gruDAO.update(grupo);
		return "grupos.xhtml";
	}
	
	public void realizarUpload(FileUploadEvent event) {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
			FacesMessage fm = new FacesMessage("Upload Concluído com Sucesso!");
			fc.addMessage("messages", fm);
		} catch (IOException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Erro no Processo de Upload!");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
			e.printStackTrace();
		}
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
