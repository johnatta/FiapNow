package br.com.fiap.mb;

import java.io.IOException;
import java.io.Serializable;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.datamodel.PessoaDataModel;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;

@ManagedBean
@SessionScoped
public class CriacaoGrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private List<Privacidade> privs;
	private Esporte[] espSelecionados;
	private Grupo grupo;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Esporte> esportes;
	private EsporteDataModel edm;
	private List<Esporte> listEsporte;
	private boolean infoBasicaGrupo = true;

	@PostConstruct
	public void init(){
		PessoaDAO pesDAO = new PessoaDAOImpl(em);
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		listEsporte = new ArrayList<Esporte>();
		grupo = new Grupo();

		pessoas = pesDAO.buscarTodasPessoas();
		esportes = espDAO.buscarTodosEsportes();		

		this.privs = Arrays.asList(grupo.getPrivacidade().values());

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
	}

	public String concluirCriacaoGrupo() {  
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		if(getGrupo().getDescricao() != null || getGrupo().getPrivacidade() != null 
				|| getGrupo().getNomeGrupo() != null || getEspSelecionados() != null){

			grupo.setAdm(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
			for (Esporte esporte : getEspSelecionados()) {
				listEsporte.add(esporte);
			}
			grupo.setEsportes(listEsporte);
			grupo = gDAO.insertEntity(grupo);
			grupo.getAdm().getGruposParticipantes().add(grupo);
			pDAO.update(grupo.getAdm());
			fm.setSummary("Grupo cadastrado com sucesso");
			fc.addMessage("", fm);
			return "grupo.xhtml";
		}else{
			fm = new FacesMessage("Campo obrigatório não preenchido. Favor preencher.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("", fm);
			return "";
		}
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
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

	public List<Esporte> getListEsporte() {
		return listEsporte;
	}

	public void setListEsporte(List<Esporte> listEsporte) {
		this.listEsporte = listEsporte;
	}

	public boolean isInfoBasicaGrupo() {
		return infoBasicaGrupo;
	}

	public void setInfoBasicaGrupo(boolean infoBasicaGrupo) {
		this.infoBasicaGrupo = infoBasicaGrupo;
	}


}
