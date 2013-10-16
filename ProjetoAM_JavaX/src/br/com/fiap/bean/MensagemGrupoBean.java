package br.com.fiap.bean;

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
import br.com.fiap.bo.GrupoBO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

public class MensagemGrupoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private MensagemGrupo mensagem;
	private List<Pessoa> membrosGrp;
	private Grupo grupo;
	private GrupoDAO gruDAO;
	private MensagemGrupoDAO msgDAO;
	private GrupoBO grupoBO;
	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public MensagemGrupoBean(Grupo grupo){
		gruDAO = new GrupoDAOImpl(em);
		msgDAO = new MensagemGrupoDAOImpl(em);
		membrosGrp = gruDAO.buscarMembrosDoGrupoComModerador(grupo.getCodGrupo());
		mensagem = new MensagemGrupo();
	}

	/**
	 * Realiza o envio da mensagem para todos os membros do grupo
	 * @author Graziele Vasconcelos 
	 */
	public void enviarMsg(){
		grupoBO = new GrupoBO();
		grupoBO.enviarMsgParaMembrosGrupo(membrosGrp, grupo);
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
