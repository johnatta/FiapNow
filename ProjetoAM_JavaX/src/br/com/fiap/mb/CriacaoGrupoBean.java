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
	private List<Esporte> espSelecionados;
	private Grupo grupo;
	private Pessoa pessoa;

	@PostConstruct
	public void init(){
		grupo = new Grupo();
		esporte = new Esporte();
		espSelecionados = new ArrayList<Esporte>();
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
		setListaPicker(new DualListModel<Esporte>(listaEsporte(), espSelecionados));

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
	}

	@PostConstruct
	public List<Esporte> listaEsporte(){
		List<Esporte> esportes = new ArrayList<Esporte>();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		esportes = espDAO.buscarTodosEsportes();
		return esportes;
	}

	public String btnCriarGrupo(){
		String retorno;
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		grupo.setAdm(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		grupo.setEsportes(espSelecionados);		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();

		try{
			gDAO.insert(grupo);
			fm.setSummary("Cadastro Realizado com Sucesso");
			fc.addMessage("", fm);
			retorno = "grupo";
		} catch(Exception e){
			e.printStackTrace();
			fm.setSummary("Erro na Realiza��o do Cadastro");
			fc.addMessage("", fm);
			retorno = "";
		}
		return retorno;
	}

	public void realizarUpload(FileUploadEvent event) {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Upload Conclu�do com Sucesso!");
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

	public List<Esporte> getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(List<Esporte> espSelecionados) {
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

}