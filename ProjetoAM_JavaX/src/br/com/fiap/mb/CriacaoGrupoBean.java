package br.com.fiap.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.datamodel.PessoaDataModel;
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
	private Esporte[] espSelecionados;
	private Pessoa[] pesSelecionados;
	private Grupo grupo;
	private List<Grupo> grupos ;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Esporte> esportes;
	private EsporteDataModel edm;
	private PessoaDataModel pdm;
	private List<Esporte> listEsporte;
	private static Logger logger = Logger.getLogger(CriacaoGrupoBean.class.getName());
	private boolean skip; 
	
	@PostConstruct
	public void init(){
		PessoaDAO pesDAO = new PessoaDAOImpl(em);
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		listEsporte = new ArrayList<Esporte>();
		grupo = new Grupo();

		pessoas = pesDAO.buscarTodasPessoas();
		esportes = espDAO.buscarTodosEsportes();		
		
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
		edm = new EsporteDataModel(esportes); 
		pdm = new PessoaDataModel(pessoas);
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
	}

	public String btnCriarGrupo(){
		String retorno;
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		grupos = new ArrayList<Grupo>();
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		
		grupo.setAdm(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		//Arrays.asList(getEspSelecionados())
		for (Esporte esporte : getEspSelecionados()) {
			listEsporte.add(esporte);
		}
		
		grupo.setEsportes(listEsporte);
		
		try{
			grupo = gDAO.insertEntity(grupo);
			pessoa.getGruposParticipantes().add(grupo);
			pessoa.getGruposModerado().add(grupo);
			//grupos.add(grupo);
			//pessoa.setGrupos(grupos);
			pDAO.update(pessoa);
			fm.setSummary("Cadastro Realizado com Sucesso");
			fc.addMessage("", fm);
			retorno = "grupo";
		} catch(Exception e){
			e.printStackTrace();
			fm.setSummary("Erro na Realização do Cadastro");
			fc.addMessage("", fm);
			retorno = "";
		}
		return retorno;
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

	 public String onFlowProcess(FlowEvent event) {  
	        logger.info("Current wizard step:" + event.getOldStep());  
	        logger.info("Next step:" + event.getNewStep());  
	          
	        if(skip) {  
	            skip = false;   //reset in case user goes back  
	            return "confirm";  
	        }  
	        else {  
	            return event.getNewStep();  
	        }  
	    }  
	
	public List<Privacidade> getPrivs() {
		return privs;
	}

	public void setPrivs(List<Privacidade> privs) {
		this.privs = privs;
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

	public List<Esporte> getEsportes() {
		return esportes;
	}

	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}

	public EsporteDataModel getEdm() {
		return edm;
	}

	public void setEdm(EsporteDataModel edm) {
		this.edm = edm;
	}

	public Esporte[] getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(Esporte[] espSelecionados) {
		this.espSelecionados = espSelecionados;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Esporte> getListEsporte() {
		return listEsporte;
	}

	public void setListEsporte(List<Esporte> listEsporte) {
		this.listEsporte = listEsporte;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa[] getPesSelecionados() {
		return pesSelecionados;
	}

	public void setPesSelecionados(Pessoa[] pesSelecionados) {
		this.pesSelecionados = pesSelecionados;
	}

	public PessoaDataModel getPdm() {
		return pdm;
	}

	public void setPdm(PessoaDataModel pdm) {
		this.pdm = pdm;
	}
	 

}
