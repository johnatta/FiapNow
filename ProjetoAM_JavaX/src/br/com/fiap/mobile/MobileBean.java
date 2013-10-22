package br.com.fiap.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.mb.LoginBean;

@ManagedBean
@SessionScoped
public class MobileBean implements Serializable {

	private EntityManager em;
	private ConviteGrupoDAO conviteGrupoDAO;
	private ConviteEventoDAO conviteEventoDAO;
	private GrupoDAO grupoDAO;
	private EventoDAO eventoDAO;
	private List<ConviteGrupo> convitesGrupo;
	private List<ConviteEvento> convitesEvento;
	private List<PedidoGrupo> pedidosGrupo;
	private List<PedidoEvento> pedidosEvento;
	private Pessoa pessoa;
	private ConviteGrupo cnvGrupoSelecionado;
	private ConviteEvento cnvEventoSelecionado;
	private PedidoEventoDAO pedEveDAO;
	private PedidoGrupoDAO pedGruDAO;
	private MensagemEventoDAO msgEveDAO;
	private MensagemGrupoDAO msgGruDAO;
	private int eventsRequests;
	private int groupsRequests;
	private int unreadMessages;

	/**
	 * M�todo executado no momento da instancia��o do ManagedBean, iniciando todas as vari�veis necess�rias
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
		pedEveDAO = new PedidoEventoDAOImpl(em);
		pedGruDAO = new PedidoGrupoDAOImpl(em);
		msgEveDAO = new MensagemEventoDAOImpl(em);
		msgGruDAO = new MensagemGrupoDAOImpl(em);

		//Obter a Pessoa da sess�o
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		convitesGrupo = conviteGrupoDAO.buscarConviteGrupoPorPessoa(pessoa);
		convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);

		pedidosGrupo = pedGruDAO.buscarPedidoGrupoPraPessoa(pessoa);
		pedidosEvento = pedEveDAO.buscarPedidosDeEventoPraPessoa(pessoa);

		unreadMessages = msgEveDAO.buscarMensagensNaoLidasDaPessoa(pessoa).size() +
				msgGruDAO.buscarMensagensNaoLidasDaPessoa(pessoa).size();	
		groupsRequests = pedGruDAO.buscarPedidoGrupoPraPessoa(pessoa).size();
		eventsRequests = pedEveDAO.buscarPedidosDeEventoPraPessoa(pessoa).size();	


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

	public int getEventsRequests() {
		return eventsRequests;
	}

	public void setEventsRequests(int eventsRequests) {
		this.eventsRequests = eventsRequests;
	}

	public int getGroupsRequests() {
		return groupsRequests;
	}

	public void setGroupsRequests(int groupsRequests) {
		this.groupsRequests = groupsRequests;
	}

	public int getUnreadMessages() {
		return unreadMessages;
	}

	public void setUnreadMessages(int unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	public List<PedidoGrupo> getPedidosGrupo() {
		return pedidosGrupo;
	}

	public void setPedidosGrupo(List<PedidoGrupo> pedidosGrupo) {
		this.pedidosGrupo = pedidosGrupo;
	}

	public List<PedidoEvento> getPedidosEvento() {
		return pedidosEvento;
	}

	public void setPedidosEvento(List<PedidoEvento> pedidosEvento) {
		this.pedidosEvento = pedidosEvento;
	}

	/**
	 * Aceita o convite de um Grupo
	 *
	 * @return retorna para a p�gina dos convites de Grupo
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
	 * @return retorna para a p�gina dos convites de Grupo
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
	 * @return retorna para a p�gina dos convites de Evento
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
	 * @return retorna para a p�gina dos convites de Evento
	 * @author Ariel Molina 
	 */
	public String recusarCnvEvento(){
		//Apenas removo o convite
		conviteEventoDAO.remove(cnvEventoSelecionado);
		convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);
		return "pm:convitesEvento";
	}

	/**
	 * Aceita o pedido para entrar em um Grupo.
	 *
	 * @param pedido Pedido que ser� aceito
	 * @author Graziele Vasconcelos
	 */
	public void aceitarPedidoGrupo(PedidoGrupo pedido){
		//Adiciono a pessoa do Pedido ao Grupo, realizo o update no Grupo e deleto o pedido
		pedido.getGrupo().getMembros().add(pedido.getPessoa());
		grupoDAO.update(pedido.getGrupo());
		pedGruDAO.remove(pedido);
		pedidosGrupo = pedGruDAO.buscarPedidoGrupoPraPessoa(pessoa);
	}

	/**
	 * Recusa o pedido para entrar em um Grupo.
	 *
	 * @param pedido Pedido que ser� recusado
	 * @author Graziele Vasconcelos
	 */
	public void recusarPedidoGrupo(PedidoGrupo pedido){
		//Apenas removo o pedido
		pedGruDAO.remove(pedido);
		pedidosGrupo = pedGruDAO.buscarPedidoGrupoPraPessoa(pessoa);
	}

	/**
	 * Aceita o pedido para entrar em um Evento.
	 *
	 * @param pedido Pedido que ser� aceito
	 * @author Graziele Vasconcelos 
	 */
	public void aceitarPedidoEvento(PedidoEvento pedido){
		//Adiciono a pessoa do Pedido ao Evento, realizo o update no Evento e deleto o pedido
		pedido.getEvento().getMembros().add(pedido.getPessoa());
		eventoDAO.update(pedido.getEvento());
		pedEveDAO.remove(pedido);
		pedidosEvento = pedEveDAO.buscarPedidosDeEventoPraPessoa(pessoa);
	}

	/**
	 * Recusa o pedido para entrar em um Evento.
	 *
	 * @param pedido Pedido que ser� recusado
	 * @author Graziele Vasconcelos 
	 */
	public void recusarPedidoEvento(PedidoEvento pedido){
		//Apenas removo o pedido
		pedEveDAO.remove(pedido);
		pedidosEvento = pedEveDAO.buscarPedidosDeEventoPraPessoa(pessoa);
	}

	/**
	 * Realiza o direcionamento para a p�gina de mensagens mobile
	 * @return p�gina mensagens mobile
	 * @author Graziele Vasconcelos
	 */
	public String pagMensagens(){
		return "mensagens_mobile.xhtml?faces-redirect=true";
	}
	/**
	 * Realiza o direcionamento para a p�gina de convites/pedidos Grupo mobile
	 * @return p�gina convite/pedidos grupo mobile
	 * @author Graziele Vasconcelos
	 */
	public String pagPedidoGrupo(){
		return "pedidos_grupos_mobile.xhtml?faces-redirect=true";
	}
	
	/**
	 * Realiza o direcionamento para a p�gina de convites/pedidos Evento mobile
	 * @return p�gina convite/pedidos evento mobile
	 * @author Graziele Vasconcelos
	 */
	public String pagPedidoEvento(){
		return "pedidos_eventos_mobile.xhtml?faces-redirect=true";
	}
}
