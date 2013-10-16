package br.com.fiap.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.bo.GrupoBO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;

@ManagedBean
@SessionScoped
public class CriacaoGrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private List<Privacidade> privs;
	private List<Esporte> listEsporte;
	private Esporte[] espSelecionados;
	private Grupo grupo;
	private Pessoa pessoa;
	private EsporteDataModel edm;
	private EsporteDAO espDAO;
	private GrupoBO grupoBO;
	
	@PostConstruct
	public void init(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		Map<String, Object> mapa = contexto.getExternalContext().getSessionMap();
		mapa.remove("grupoBean");
		
		grupoBO = new GrupoBO();
		espDAO = new EsporteDAOImpl(em);
		grupo = new Grupo();
		
		listEsporte = espDAO.buscarTodosEsportes();		
		edm = new EsporteDataModel(listEsporte); 
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
	}

	/**
	 * Realização a criação do grupo
	 * @return retorno para a página do grupo criado
	 * @author Graziele Vasconcelos
	 */
	public String concluirCriacaoGrupo() {  
		return grupoBO.criacaoGrupo(grupo, espSelecionados, pessoa);
	}
	
	/**
	 * Realizado a inserção da imagem do grupo
	 * @param event
	 * @author Graziele Vasconcelos
	 */
	public void realizarUpload(FileUploadEvent event) {
		grupoBO.uploadFoto(grupo, event);
	}

	public List<Privacidade> getPrivs() {
		return privs;
	}

	public void setPrivs(List<Privacidade> privs) {
		this.privs = privs;
	}

	public Esporte[] getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(Esporte[] espSelecionados) {
		this.espSelecionados = espSelecionados;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public EsporteDataModel getEdm() {
		return edm;
	}

	public void setEdm(EsporteDataModel edm) {
		this.edm = edm;
	}

	public List<Esporte> getListEsporte() {
		return listEsporte;
	}

	public void setListEsporte(List<Esporte> listEsporte) {
		this.listEsporte = listEsporte;
	}

}
