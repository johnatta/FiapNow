package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.context.RequestContext;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.datamodel.MensagemEventoDataModel;
import br.com.fiap.datamodel.MensagemGrupoDataModel;
import br.com.fiap.entity.Confirmacao;
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
	
	/**
	 * Método executado no momento da instanciação do ManagedBean, iniciando todas as variáveis necessárias
	 *
	 * @author Ariel Molina 
	 */
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
		
		msgsGrupo = msgGrupoDAO.buscarMensagensDaPessoa(pessoa);
		msgGrupoModel = new MensagemGrupoDataModel(msgsGrupo);
		
		msgsEvento = msgEventoDAO.buscarMensagensDaPessoa(pessoa);
		msgEventoModel = new MensagemEventoDataModel(msgsEvento);
		
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
	
	/**
	* Exibe na tela a MensagemGrupo selecionada e a marca como Lida
	*
	* @author Ariel Molina 
	*/
	public void visualizarMensagemGrupo(MensagemGrupo msgGrupo){
		
		if(msgGrupo.getConfirmacao() == Confirmacao.NAO){
			msgGrupo.setConfirmacao(Confirmacao.SIM);
			msgGrupoDAO.update(msgGrupo);
		}
		
		msgsGrupo = msgGrupoDAO.buscarMensagensDaPessoa(pessoa);
		msgGrupoModel = new MensagemGrupoDataModel(msgsGrupo);
		
		selectedMsgGrupo = msgGrupo;
		RequestContext.getCurrentInstance().execute("mensagemDialogGrupo.show()");
		
	}
	
	/**
	* Exibe na tela a MensagemEvento selecionada e a marca como Lida
	*
	* @author Ariel Molina 
	*/
	public void visualizarMensagemEvento(MensagemEvento msgEvento){
		
		if(msgEvento.getConfirmacao() == Confirmacao.NAO){
			msgEvento.setConfirmacao(Confirmacao.SIM);
			msgEventoDAO.update(msgEvento);
		}
		
		msgsEvento = msgEventoDAO.buscarMensagensDaPessoa(pessoa);
		msgEventoModel = new MensagemEventoDataModel(msgsEvento);
		
		selectedMsgEvento = msgEvento;
		RequestContext.getCurrentInstance().execute("mensagemDialogEvento.show()");
	}
	
	/**
	* Exclui as MensagemGrupo selecionadas
	*
	* @author Ariel Molina 
	*/
	public void excluirMsgsGrupo(){
		
		activeTab = 0;
		
		for(MensagemGrupo msg : selectedMsgsGrupo){
			msgGrupoDAO.remove(msg);
		}
		
		selectedMsgsGrupo = null;
		
		msgsGrupo = msgGrupoDAO.buscarMensagensDaPessoa(pessoa);
		msgGrupoModel = new MensagemGrupoDataModel(msgsGrupo);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "message_deleted",
				null, fc.getViewRoot().getLocale());
		
		FacesMessage fm = new FacesMessage(mensagem);
		
		fc.addMessage("", fm);
		
	}
	
	/**
	* Exclui as MensagemEvento selecionadas.
	*
	* @author Ariel Molina 
	*/
	public void excluirMsgsEvento(){
		
		activeTab = 1;
		
		for(MensagemEvento msg : selectedMsgsEvento){
			msgEventoDAO.remove(msg);
		}
		
		selectedMsgsEvento = null;
		
		msgsEvento = msgEventoDAO.buscarMensagensDaPessoa(pessoa);
		msgEventoModel = new MensagemEventoDataModel(msgsEvento);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "message_deleted",
				null, fc.getViewRoot().getLocale());
		
		FacesMessage fm = new FacesMessage(mensagem);
		
		fc.addMessage("", fm);
		
	}
	
}
