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
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

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
	private Pessoa pessoa;
	private PessoaDAO pesDAO;
	
	@PostConstruct
	public void onInit(){
		espDAO = new EsporteDAOImpl(em);
		esps= espDAO.buscarTodosEsportes();
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		
	}
	
	public String btnConvidar(){
		
		
		return "";
	}
	
	public String btnCriarEvento(){
		String retorno = null;
		pesDAO = new PessoaDAOImpl(em);
		pessoa = pesDAO.buscarInformacoes(pessoa.getCodPessoa());
		
		return retorno;
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
