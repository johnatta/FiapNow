package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;

@ManagedBean
@RequestScoped
public class EventosBean implements Serializable {
	
	private List<Evento> eventos;
	private Evento selectedEvento;
	private EntityManager em;
	private EventoDAO eventoDAO;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		eventoDAO = new EventoDAOImpl(em);
		eventos = eventoDAO.buscarEventosDoUsuario(3);
	}
	
}
