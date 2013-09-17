package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.context.RequestContext;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.datamodel.MensagemEventoDataModel;
import br.com.fiap.datamodel.MensagemGrupoDataModel;
import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class MensagensBean implements Serializable {
	
	private Pessoa pessoa;
	private EntityManager em;
	private MensagemEventoDAO msgEventoDAO;
	private MensagemGrupoDAO msgGrupoDAO;
	private List<MensagemEvento> msgsEvento;
	private List<MensagemGrupo> msgsGrupo;
	private MensagemGrupoDataModel msgGrupoModel;
	private MensagemEventoDataModel msgEventoModel;
	private MensagemGrupo selectedMsgGrupo;
	private MensagemGrupo[] selectedMsgsGrupo;
	private MensagemEvento selectedMsgEvento;
	private MensagemEvento[] selectedMsgsEvento;
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
		
		msgsEvento = msgEventoDAO.buscarMensagensDaPessoa(pessoa);
		msgEventoModel = new MensagemEventoDataModel(msgsEvento);
		
		msgsGrupo = msgGrupoDAO.buscarMensagensDaPessoa(pessoa);
		msgGrupoModel = new MensagemGrupoDataModel(msgsGrupo);
		
	}

	public int getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}
	public MensagemGrupoDataModel getMsgGrupoModel() {
		return msgGrupoModel;
	}
	public void setMsgGrupoModel(MensagemGrupoDataModel msgGrupoModel) {
		this.msgGrupoModel = msgGrupoModel;
	}
	public MensagemEventoDataModel getMsgEventoModel() {
		return msgEventoModel;
	}
	public void setMsgEventoModel(MensagemEventoDataModel msgEventoModel) {
		this.msgEventoModel = msgEventoModel;
	}
	public MensagemGrupo getSelectedMsgGrupo() {
		return selectedMsgGrupo;
	}
	public void setSelectedMsgGrupo(MensagemGrupo selectedMsgGrupo) {
		this.selectedMsgGrupo = selectedMsgGrupo;
	}
	public MensagemGrupo[] getSelectedMsgsGrupo() {
		return selectedMsgsGrupo;
	}
	public void setSelectedMsgsGrupo(MensagemGrupo[] selectedMsgsGrupo) {
		this.selectedMsgsGrupo = selectedMsgsGrupo;
	}
	public MensagemEvento getSelectedMsgEvento() {
		return selectedMsgEvento;
	}
	public void setSelectedMsgEvento(MensagemEvento selectedMsgEvento) {
		this.selectedMsgEvento = selectedMsgEvento;
	}
	public MensagemEvento[] getSelectedMsgsEvento() {
		return selectedMsgsEvento;
	}
	public void setSelectedMsgsEvento(MensagemEvento[] selectedMsgsEvento) {
		this.selectedMsgsEvento = selectedMsgsEvento;
	}
	

	public void visualizarMensagemGrupo(MensagemGrupo msgGrupo){
		selectedMsgGrupo = msgGrupo;
		RequestContext.getCurrentInstance().execute("mensagemDialogGrupo.show()");
	}
	
	public void visualizarMensagemEvento(MensagemEvento msgEvento){
		selectedMsgEvento = msgEvento;
		RequestContext.getCurrentInstance().execute("mensagemDialogEvento.show()");
	}
	
	public void excluirMsgsGrupo(){
		
		activeTab = 0;
		
		for(MensagemGrupo msg : selectedMsgsGrupo){
			msgGrupoDAO.remove(msg);
		}
		
		selectedMsgsGrupo = null;
		
		msgsGrupo = msgGrupoDAO.buscarMensagensDaPessoa(pessoa);
		msgGrupoModel = new MensagemGrupoDataModel(msgsGrupo);
		
	}
	
	
	public void excluirMsgsEvento(){
		
		activeTab = 1;
		
		for(MensagemEvento msg : selectedMsgsEvento){
			msgEventoDAO.remove(msg);
		}
		
		selectedMsgsEvento = null;
		
		msgsEvento = msgEventoDAO.buscarMensagensDaPessoa(pessoa);
		msgEventoModel = new MensagemEventoDataModel(msgsEvento);
		
	}
	
}
