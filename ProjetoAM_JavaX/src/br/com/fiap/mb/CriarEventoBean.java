package br.com.fiap.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;

@ManagedBean
@RequestScoped
public class CriarEventoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private Grupo grupo;
	private Esporte esporte;
	private List<Esporte> esps;
	private EsporteDAO espDAO;

	
	@PostConstruct
	public void onInit(){
		espDAO = new EsporteDAOImpl(em);
		esps= espDAO.buscarTodosEsportes();
	}
	
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public List<Esporte> getEsps() {
		return esps;
	}

	public void setEsps(List<Esporte> esps) {
		this.esps = esps;
	}
}
