package br.com.fiap.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.mb.LoginBean;

@ManagedBean
@ViewScoped
public class MobileBean implements Serializable {
	
	private EntityManager em;
	private ConviteGrupoDAO conviteGrupoDAO;
	private ConviteEventoDAO conviteEventoDAO;
	private GrupoDAO grupoDAO;
	private EventoDAO eventoDAO;
	private List<ConviteGrupo> convitesGrupo;
	private List<ConviteEvento> convitesEvento;
	private Pessoa pessoa;
	private ConviteGrupo cnvGrupoSelecionado;
	private ConviteEvento cnvEventoSelecionado;
	
	/**
	 * Método executado no momento da instanciação do ManagedBean, iniciando todas as variáveis necessárias
	 *
	 * @author Ariel Molina 
	 */
	@PostConstruct
	public void onInit() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		conviteGrupoDAO = new ConviteGrupoDAOImpl(em);
		conviteEventoDAO = new ConviteEventoDAOImpl(em);
		grupoDAO = new GrupoDAOImpl(em);
		eventoDAO = new EventoDAOImpl(em);
		
		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		convitesGrupo = conviteGrupoDAO.buscarConviteGrupoPorPessoa(pessoa);
		convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);
		
	}
	
	public List<ConviteGrupo> getConvitesGrupo() {
		return convitesGrupo;
	}
	public void setConvitesGrupo(List<ConviteGrupo> convitesGrupo) {
		this.convitesGrupo = convitesGrupo;
	}
	public ConviteGrupo getCnvGrupoSelecionado() {
		return cnvGrupoSelecionado;
	}
	public void setCnvGrupoSelecionado(ConviteGrupo cnvGrupoSelecionado) {
		this.cnvGrupoSelecionado = cnvGrupoSelecionado;
	}
	public List<ConviteEvento> getConvitesEvento() {
		return convitesEvento;
	}
	public void setConvitesEvento(List<ConviteEvento> convitesEvento) {
		this.convitesEvento = convitesEvento;
	}
	public ConviteEvento getCnvEventoSelecionado() {
		return cnvEventoSelecionado;
	}
	public void setCnvEventoSelecionado(ConviteEvento cnvEventoSelecionado) {
		this.cnvEventoSelecionado = cnvEventoSelecionado;
	}

	/**
	 * Aceita o convite de um Grupo
	 *
	 * @return retorna para a página dos convites de Grupo
	 * @author Ariel Molina 
	 */
	public String aceitarCnvGrupo(){
		//Adiciono a Pessoa ao Grupo, update no Grupo removo o Convite
		cnvGrupoSelecionado.getGrupo().getMembros().add(pessoa);
		grupoDAO.update(cnvGrupoSelecionado.getGrupo());
		conviteGrupoDAO.remove(cnvGrupoSelecionado);
		convitesGrupo = conviteGrupoDAO.buscarConviteGrupoPorPessoa(pessoa);
		return "pm:convitesGrupo";
	}
	
	/**
	 * Recusa o convite de um Grupo
	 *
	 * @return retorna para a página dos convites de Grupo
	 * @author Ariel Molina 
	 */
	public String recusarCnvGrupo(){
		//Apenas removo o convite
		conviteGrupoDAO.remove(cnvGrupoSelecionado);
		convitesGrupo = conviteGrupoDAO.buscarConviteGrupoPorPessoa(pessoa);
		return "pm:convitesGrupo";
	}
	
	/**
	 * Aceita o convite de um Evento
	 *
	 * @return retorna para a página dos convites de Evento
	 * @author Ariel Molina 
	 */
	public String aceitarCnvEvento(){
		//Adiciono a Pessoa ao Grupo, update no Grupo removo o Convite
		cnvEventoSelecionado.getEvento().getMembros().add(pessoa);
		eventoDAO.update(cnvEventoSelecionado.getEvento());
		conviteEventoDAO.remove(cnvEventoSelecionado);
		convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);
		return "pm:convitesEvento";
	}
	
	/**
	 * Aceita o convite de um Evento
	 *
	 * @return retorna para a página dos convites de Evento
	 * @author Ariel Molina 
	 */
	public String recusarCnvEvento(){
		//Apenas removo o convite
		conviteEventoDAO.remove(cnvEventoSelecionado);
		convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);
		return "pm:convitesEvento";
	}

}
