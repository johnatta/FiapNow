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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;

@ManagedBean
@ViewScoped
public class CriacaoGrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private DualListModel<Esporte> listaPicker;
	private Esporte esporte;
	private List<Privacidade> privs;
	private Esporte[] espSelecionados;
	private Grupo grupo;
	private EsporteSelecionado ep;
	private Pessoa pessoa;
	private List<Esporte> esps;
	private List<Esporte> esportes;
	private EsporteDataModel edm;
	
	
	@PostConstruct
	public void init(){
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		//esportes = new ArrayList<Esporte>();
		esportes = espDAO.buscarTodosEsportes();		
		ep = new EsporteSelecionado();
		
		grupo = new Grupo();
		esporte = new Esporte();
		esps = new ArrayList<Esporte>();
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
		
		edm = new EsporteDataModel(esportes); 
		//setListaPicker(new DualListModel<Esporte>(esportes, espSelecionados));

		//listaPicker = new DualListModel<Esporte>(esportes, espSelecionados);
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
	}

	public String btnCriarGrupo(){
		
		String retorno;
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		//for(Esporte e : listaPicker.getTarget()){
		//	esps.add(e);
		//}
		//grupo.setEsportes(listaPicker.getTarget());
		grupo.setEsportes(Arrays.asList(getEspSelecionados()));
		grupo.setAdm(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		
//		for(Esporte esporte : getEspSelecionados()) {
//			int codEsporte = esporte.getCodEsporte();
//			String nome = esporte.getNome();
//			System.out.println("Esporte{" +
//					"codEsporte:[" + codEsporte + "]" +
//					"nome:[" + nome + "]" +
//					"}");
//		}
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		List<Grupo> grupos = new ArrayList<Grupo>();
		try{
			gDAO.insert(grupo);
			grupos = pessoa.getGrupos();
			grupos.add(grupo);
			fm.setSummary("Cadastro Realizado com Sucesso");
			fc.addMessage("", fm);
			retorno = "index";
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
			FacesMessage fm = new FacesMessage("Upload Concluído com Sucesso!");
			fc.addMessage("messages", fm);
			grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (IOException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Erro no Processo de Upload!");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
			e.printStackTrace();
		}
	}

	public DualListModel<Esporte> getListaPicker() {
		return listaPicker;
	}

	public void setListaPicker(DualListModel<Esporte> listaPicker) {
		this.listaPicker = listaPicker;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
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

	public List<Esporte> getEsps() {
		return esps;
	}

	public void setEsps(List<Esporte> esps) {
		this.esps = esps;
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

	public EsporteSelecionado getEp() {
		return ep;
	}

	public void setEp(EsporteSelecionado ep) {
		this.ep = ep;
	}

	public Esporte[] getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(Esporte[] espSelecionados) {
		this.espSelecionados = espSelecionados;
	}

}
