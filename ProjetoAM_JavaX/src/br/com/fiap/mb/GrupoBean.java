package br.com.fiap.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Privacidade;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class GrupoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private Esporte esporte;
	private Grupo grupo;
	private GrupoDAO gruDAO; 
	private List<Privacidade> privs;
	private StreamedContent foto;

	@PostConstruct
	public void init(){
		grupo = new Grupo();
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
	}
	public List<Esporte> listaEsporte(){
		List<Esporte> esportes = new ArrayList<Esporte>();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		esportes = espDAO.buscarTodosEsportes();
		return esportes;
	}

	public void btnCriarGrupo(ActionEvent ae){
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		try{
			gruDAO.insert(getGrupo());
			fm.setSummary("Cadastro Realizado com Sucesso");
			fc.addMessage("", fm);
		} catch(Exception e){
			e.printStackTrace();
			fm.setSummary("Erro na Realiza��o do Cadastro");
			fc.addMessage("", fm);
		}
	}

	public void realizarUpload(FileUploadEvent event) {
		String arq = event.getFile().getFileName();
		System.out.println("Nome do arquivo:" + arq);
		System.out.println("Tamanho do arquivo:" + event.getFile().getSize());
		
		try {
			grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
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
	public StreamedContent getFoto() {
		FacesContext context = FacesContext.getCurrentInstance();
		DefaultStreamedContent content = new DefaultStreamedContent();
		content.setContentType("image/jpg");
		try{
			if (context.getRenderResponse() || grupo.getImgGrupo() == null){
				content.setStream(context.getExternalContext().getResourceAsStream("/resources/img/semFoto.jpg"));
			}else{
				content.setStream(new ByteArrayInputStream(grupo.getImgGrupo()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}


}