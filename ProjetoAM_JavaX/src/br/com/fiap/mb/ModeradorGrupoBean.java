package br.com.fiap.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@SessionScoped
public class ModeradorGrupoBean {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private PessoaDAO pDAO ;
	private GrupoDAO gruDAO;
	private ModeradorGrupoDAO modDAO;
	private Pessoa pessoa;
	private List<Pessoa> moderadores; 
	private Grupo grupo;
	private int codGrupo;
	private boolean primeiraVez = true;
	
	public void infoGrupo(){
		if(primeiraVez){
			primeiraVez = false;
			moderadores = modDAO.buscarModeradoresDoGrupo(codGrupo);
			grupo = gruDAO.searchByID(codGrupo);
		}
	}
	
	@PostConstruct
	public void onInit() {
		gruDAO = new GrupoDAOImpl(em);
		modDAO = new ModeradorGrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		pessoa = new Pessoa();
	}

	public void excluirModerador(int codPessoa){
		ModeradorGrupo moderadorGrupo =  modDAO.buscarModeradorGrupo(grupo.getCodGrupo(), codPessoa);
		modDAO.remove(moderadorGrupo);
		moderadores = modDAO.buscarModeradoresDoGrupo(grupo.getCodGrupo());
	}
	
	public String addModerador(){
		return "" ;
	}

	public void desabilitarModerador(){
	}
	
}
