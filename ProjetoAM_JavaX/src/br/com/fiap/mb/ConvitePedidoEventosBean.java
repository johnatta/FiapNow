package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
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
	private List<ConviteEvento> convites;
	private List<PedidoEvento> pedidos;
	private Pessoa pessoa;
	
	@PostConstruct
	public void onInit() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		conviteDAO = new ConviteEventoDAOImpl(em);
		pedidoDAO = new PedidoEventoDAOImpl(em);
		
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
	
	public void aceitar(ActionEvent ae){
		
	}
	
	public void recusar(ActionEvent ae){
		
	}

}
