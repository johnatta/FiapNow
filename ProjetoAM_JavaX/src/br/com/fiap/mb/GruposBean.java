package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.Grupo;

@ManagedBean
@RequestScoped
public class GruposBean implements Serializable {
	
	private List<Grupo> grupos;
	private Grupo selectedGrupo;
	private EntityManager em;
	private GrupoDAO grupoDAO;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		grupoDAO = new GrupoDAOImpl(em);
		grupos = grupoDAO.buscaGruposDoUsuario(3);
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public Grupo getSelectedGrupo() {
		return selectedGrupo;
	}
	public void setSelectedGrupo(Grupo selectedGrupo) {
		this.selectedGrupo = selectedGrupo;
	}

}
