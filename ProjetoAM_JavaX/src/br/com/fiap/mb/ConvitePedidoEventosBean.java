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
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@ViewScoped
public class ConvitePedidoEventosBean implements Serializable {
	
	private EntityManager em;
	private ConviteEventoDAO conviteDAO;
	private PedidoEventoDAO pedidoDAO;
	private PessoaDAO pessoaDAO;
	private List<ConviteEvento> convites;
	private List<PedidoEvento> pedidos;
	private Pessoa pessoa;
	
	@PostConstruct
	public void onInit() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		conviteDAO = new ConviteEventoDAOImpl(em);
		pedidoDAO = new PedidoEventoDAOImpl(em);
		pessoaDAO = new PessoaDAOImpl(em); //Utilizado no aceite de convites
		
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
	
	public void aceitarConvite(ConviteEvento convite){
		//Adiciono o Evento aos eventos da Pessoa, updato a Pessoa e removo o convite
		pessoa.getEventos().add(convite.getEvento());
		pessoaDAO.update(pessoa);
		conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteEventoPorPessoa(pessoa);
	}
	
	public void recusarConvite(ConviteEvento convite){
		//Apenas removo o convite
		conviteDAO.remove(convite);
		convites = conviteDAO.buscarConviteEventoPorPessoa(pessoa);
	}
	
	public void aceitarPedido(PedidoEvento pedido){
		//Removo o pedido e insiro um AM_PESSOA_EVENTO para aquela pessoa que pediu e aquele Evento
		//pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
	}
	
	public void recusarPedido(PedidoEvento pedido){
		//Apenas removo o pedido
		//pedidoDAO.remove(pedido);
		pedidos = pedidoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
	}

}
