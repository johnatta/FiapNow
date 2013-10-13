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
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class ConvitePedidoGruposBean implements Serializable {
	
	private EntityManager em;
	private ConviteGrupoDAO conviteDAO;
	private PedidoGrupoDAO pedidoDAO;
	private PessoaDAO pessoaDAO;
	private GrupoDAO grupoDAO;
	private List<ConviteGrupo> convites;
	private List<PedidoGrupo> pedidos;
	private Pessoa pessoa;
	private int activeTab;
	
	@PostConstruct
	public void onInit() {
		activeTab = 0;
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		conviteDAO = new ConviteGrupoDAOImpl(em);
		pedidoDAO = new PedidoGrupoDAOImpl(em);
		pessoaDAO = new PessoaDAOImpl(em);
		grupoDAO = new GrupoDAOImpl(em);
		
		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		convites = conviteDAO.buscarConviteGrupoPorPessoa(pessoa);
		pedidos = pedidoDAO.buscarPedidoGrupoPraPessoa(pessoa);
	}
	
	public List<ConviteGrupo> getConvites() {
		return convites;
	}
	public void setConvites(List<ConviteGrupo> convites) {
		this.convites = convites;
	}
	public List<PedidoGrupo> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoGrupo> pedidos) {
		this.pedidos = pedidos;
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
	* Aceita o convite de um Grupo.
	*
	* @param convite Convite que será aceito
	* @author Ariel Molina 
	*/
	public void aceitarConvite(ConviteGrupo convite){
		//Adiciono a Pessoa ao Grupo, realizo update no Grupo e removo o convite
		convite.getGrupo().getMembros().add(pessoa);
		grupoDAO.update(convite.getGrupo());
		conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteGrupoPorPessoa(pessoa);
		activeTab = 0;
	}
	
	/**
	* Recusa o convite de um Grupo.
	*
	* @param convite Convite que será recusado
	* @author Ariel Molina 
	*/
	public void recusarConvite(ConviteGrupo convite){
		//Apenas removo o convite
		conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteGrupoPorPessoa(pessoa);
		activeTab = 0;
	}
	
	/**
	* Aceita o pedido para entrar em um Grupo.
	*
	* @param pedido Pedido que será aceito
	* @author Ariel Molina 
	*/
	public void aceitarPedido(PedidoGrupo pedido){
		//Adiciono a pessoa do Pedido ao Grupo, realizo o update no Grupo e deleto o pedido
		pedido.getGrupo().getMembros().add(pedido.getPessoa());
		grupoDAO.update(pedido.getGrupo());
		pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidoGrupoPraPessoa(pessoa);
		activeTab = 1;
	}
	
	/**
	* Recusa o pedido para entrar em um Grupo.
	*
	* @param pedido Pedido que será recusado
	* @author Ariel Molina 
	*/
	public void recusarPedido(PedidoGrupo pedido){
		//Apenas removo o pedido
		pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidoGrupoPraPessoa(pessoa);
		activeTab = 1;
	}

}
