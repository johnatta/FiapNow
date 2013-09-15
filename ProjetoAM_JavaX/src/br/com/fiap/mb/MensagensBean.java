package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@ViewScoped
public class MensagensBean implements Serializable {
	
	private Pessoa pessoa;
	private EntityManager em;
	private MensagemEventoDAO msgEventoDAO;
	private MensagemGrupoDAO msgGrupoDAO;
	private List<MensagemEvento> msgsEventoLidas;
	private List<MensagemEvento> msgsEventoNaoLidas;
	private List<MensagemGrupo> msgsGrupoLidas;
	private List<MensagemGrupo> msgsGrupoNaoLidas;
	private int activeTab;
	
	@PostConstruct
	public void onInit(){
		activeTab = 0;
		
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		msgEventoDAO = new MensagemEventoDAOImpl(em);
		msgGrupoDAO = new MensagemGrupoDAOImpl(em);
		
		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		
		msgsEventoLidas = msgEventoDAO.buscarMensagensLidasDaPessoa(pessoa);
		msgsEventoNaoLidas = msgEventoDAO.buscarMensagensNaoLidasDaPessoa(pessoa);
		
		msgsGrupoLidas = msgGrupoDAO.buscarMensagensLidasDaPessoa(pessoa);
		msgsGrupoNaoLidas = msgGrupoDAO.buscarMensagensNaoLidasDaPessoa(pessoa);
	}

	public List<MensagemEvento> getMsgsEventoLidas() {
		return msgsEventoLidas;
	}

	public void setMsgsEventoLidas(List<MensagemEvento> msgsEventoLidas) {
		this.msgsEventoLidas = msgsEventoLidas;
	}

	public List<MensagemEvento> getMsgsEventoNaoLidas() {
		return msgsEventoNaoLidas;
	}

	public void setMsgsEventoNaoLidas(List<MensagemEvento> msgsEventoNaoLidas) {
		this.msgsEventoNaoLidas = msgsEventoNaoLidas;
	}

	public List<MensagemGrupo> getMsgsGrupoLidas() {
		return msgsGrupoLidas;
	}

	public void setMsgsGrupoLidas(List<MensagemGrupo> msgsGrupoLidas) {
		this.msgsGrupoLidas = msgsGrupoLidas;
	}

	public List<MensagemGrupo> getMsgsGrupoNaoLidas() {
		return msgsGrupoNaoLidas;
	}

	public void setMsgsGrupoNaoLidas(List<MensagemGrupo> msgsGrupoNaoLidas) {
		this.msgsGrupoNaoLidas = msgsGrupoNaoLidas;
	}

	public int getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}
	
}
