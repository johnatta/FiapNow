package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class EventosBean implements Serializable {
	
	private List<Evento> eventos;
	private List<Evento> meusEventos;
	private Evento selectedEvento;
	private EntityManager em;
	private EventoDAO eventoDAO;
	private Pessoa pessoa;
	private String filtro;

	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		eventoDAO = new EventoDAOImpl(em);
		eventos = eventoDAO.buscarEventos();
		
		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		meusEventos = eventoDAO.buscarEventosDaPessoa(pessoa);
	}
	
	public void filtrar(ActionEvent ae){
		eventos = eventoDAO.buscarEventosPorNome(filtro);
		meusEventos = eventoDAO.buscarMeusEventosPorNome(pessoa, filtro);
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public Evento getSelectedEvento() {
		return selectedEvento;
	}
	public void setSelectedEvento(Evento selectedEvento) {
		this.selectedEvento = selectedEvento;
	}
	public List<Evento> getMeusEventos() {
		return meusEventos;
	}
	public void setMeusEventos(List<Evento> meusEventos) {
		this.meusEventos = meusEventos;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
