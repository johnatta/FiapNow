package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@SessionScoped
public class GruposBean implements Serializable {
	
	private List<Grupo> grupos;
	private List<Grupo> meusGrupos;
	private Grupo selectedGrupo;
	private EntityManager em;
	private GrupoDAO grupoDAO;
	private String filtro;
	private Pessoa pessoa;

	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		grupoDAO = new GrupoDAOImpl(em);
		grupos = grupoDAO.buscarGruposAbertos();
		
		//Obter a Pessoa da sess�o
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		meusGrupos = grupoDAO.buscaGruposDaPessoa(pessoa);
	}
	
	public void filtrar(ActionEvent ae){
		grupos = grupoDAO.buscarGruposAbertosPorNome(filtro);
		meusGrupos = grupoDAO.buscarMeusGruposPorNome(pessoa, filtro);
	}
	
	public String visualizarGrupo(){
		return "grupo.xhtml";
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
	public List<Grupo> getMeusGrupos() {
		return meusGrupos;
	}
	public void setMeusGrupos(List<Grupo> meusGrupos) {
		this.meusGrupos = meusGrupos;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}

