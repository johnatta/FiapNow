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
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@ViewScoped
public class ConvitePedidoGruposBean implements Serializable {
	
	private EntityManager em;
	private ConviteGrupoDAO conviteDAO;
	private PedidoGrupoDAO pedidoDAO;
	private List<ConviteGrupo> convites;
	private List<PedidoGrupo> pedidos;
	private Pessoa pessoa;
	
	@PostConstruct
	public void onInit() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		conviteDAO = new ConviteGrupoDAOImpl(em);
		pedidoDAO = new PedidoGrupoDAOImpl(em);
		
		//Obter a Pessoa da sess�o
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
	
	public void aceitarConvite(ConviteEvento convite){
		//Removo o convite e insiro um AM_PESSOA_EVENTO para aquela pessoa e aquele Evento
		//conviteDAO.remove(conviteSelecionado);
		convites = conviteDAO.buscarConviteGrupoPorPessoa(pessoa);
	}
	
	public void recusarConvite(ConviteEvento convite){
		//Apenas removo o convite
		//conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteGrupoPorPessoa(pessoa);
	}
	
	public void aceitarPedido(PedidoEvento pedido){
		//Removo o pedido e insiro um AM_PESSOA_EVENTO para aquela pessoa que pediu e aquele Evento
		//pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidoGrupoPraPessoa(pessoa);
	}
	
	public void recusarPedido(PedidoEvento pedido){
		//Apenas removo o pedido
		//pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidoGrupoPraPessoa(pessoa);
	}

}