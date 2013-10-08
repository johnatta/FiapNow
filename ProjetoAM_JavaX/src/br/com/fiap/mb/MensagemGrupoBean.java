package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@SessionScoped
public class MensagemGrupoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private boolean primeiraVez;
	private int codGrupo;
	private MensagemGrupo mensagem;
	private List<Pessoa> membrosGrp;
	private Grupo grupo;
	private GrupoDAO gruDAO;
	private MensagemGrupoDAO msgDAO;

	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public void infoGrupo(){
		if(primeiraVez){
			primeiraVez = false;
			grupo = gruDAO.searchByID(codGrupo);
			membrosGrp = gruDAO.buscarMembrosDoGrupoComModerador(codGrupo);
			mensagem = new MensagemGrupo();
		}
	}

	@PostConstruct
	public void onInit() {
		primeiraVez = true;
		gruDAO = new GrupoDAOImpl(em);
		msgDAO = new MensagemGrupoDAOImpl(em);
	}

	/**
	 * Realiza o envio da mensagem para todos os membros do grupo
	 * @author Graziele Vasconcelos 
	 */
	public void enviarMsg(){
		boolean envio = false;
		for (Pessoa membro : membrosGrp){
			mensagem.setPessoa(membro);
			mensagem.setGrupo(grupo);
			mensagem.setConfirmacao(Confirmacao.NAO);
			msgDAO.insert(mensagem);
			envio = true;
		}		
		if(envio){
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Mensagens enviada com Sucesso!");
			fc.addMessage("messages", fm);
		}else{
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Erro no envio!");
			fc.addMessage("messages", fm);
		}
	}

	/**
	 * Direciona para a página grupo da sessão.
	 * @return página grupo da sessão
	 * @author Graziele Vasconcelos
	 */
	public String paginaGrupo(){
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		map.remove("mensagemGrupoBean");		
		return "grupo.xhtml";
	}


	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}

	public List<Pessoa> getMembrosGrp() {
		return membrosGrp;
	}

	public void setMembrosGrp(List<Pessoa> membrosGrp) {
		this.membrosGrp = membrosGrp;
	}

	public MensagemGrupo getMensagem() {
		return mensagem;
	}

	public void setMensagem(MensagemGrupo mensagem) {
		this.mensagem = mensagem;
	}



}
