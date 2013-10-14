package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class ConvitePedidoEventosBean implements Serializable {

	private EntityManager em;
	private ConviteEventoDAO conviteDAO;
	private PedidoEventoDAO pedidoDAO;
	private EventoDAO eventoDAO;
	private List<ConviteEvento> convites;
	private List<PedidoEvento> pedidos;
	private Pessoa pessoa;
	private int activeTab;

	/**
	 * Método executado no momento da instanciação do ManagedBean, iniciando todas as variáveis necessárias
	 *
	 * @author Ariel Molina 
	 */
	@PostConstruct
	public void onInit() {
		activeTab = 0;
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		conviteDAO = new ConviteEventoDAOImpl(em);
		pedidoDAO = new PedidoEventoDAOImpl(em);
		eventoDAO = new EventoDAOImpl(em);

		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		convites = conviteDAO.buscarConviteEventoPorPessoa(pessoa);
		pedidos = pedidoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
	}

	public List<ConviteEvento> getConvites() {
		return convites;
	}
	public void setConvites(List<ConviteEvento> convites) {
		this.convites = convites;
	}
	public List<PedidoEvento> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoEvento> eventos) {
		this.pedidos = eventos;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public int getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(int activeTab) {
		this.activeTab = activeTab;
	}

	/**
	 * Aceita o convite de um Evento.
	 *
	 * @param convite Convite que será aceito
	 * @author Ariel Molina 
	 */
	public void aceitarConvite(ConviteEvento convite){
		//Adiciono a Pessoa ao Evento, realizo update no Evento e removo o convite
		convite.getEvento().getMembros().add(pessoa);
		eventoDAO.update(convite.getEvento());
		conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteEventoPorPessoa(pessoa);
		activeTab = 0;
	}

	/**
	 * Recusa o convite de um Evento.
	 *
	 * @param convite Convite que será recusado
	 * @author Ariel Molina 
	 */
	public void recusarConvite(ConviteEvento convite){
		//Apenas removo o convite
		conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteEventoPorPessoa(pessoa);
		activeTab = 0;
	}

	/**
	 * Aceita o pedido para entrar em um Evento.
	 *
	 * @param pedido Pedido que será aceito
	 * @author Ariel Molina 
	 */
	public void aceitarPedido(PedidoEvento pedido){
		//Adiciono a pessoa do Pedido ao Evento, realizo o update no Evento e deleto o pedido
		pedido.getEvento().getMembros().add(pedido.getPessoa());
		eventoDAO.update(pedido.getEvento());
		pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
		activeTab = 1;
	}

	/**
	 * Recusa o pedido para entrar em um Evento.
	 *
	 * @param pedido Pedido que será recusado
	 * @author Ariel Molina 
	 */
	public void recusarPedido(PedidoEvento pedido){
		//Apenas removo o pedido
		pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
		activeTab = 1;
	}

}
