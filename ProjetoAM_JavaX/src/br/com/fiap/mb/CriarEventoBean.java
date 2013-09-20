package br.com.fiap.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import br.com.fiap.datamodel.EsporteDataModel;
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
	private List<Esporte> esportes;
	private Esporte[] espSelecionados;
	private EsporteDAO espDAO;
	private Pessoa pessoa;
	private PessoaDAO pesDAO;
	private EsporteDataModel edm;
	private Date cal;
	
	
	public Date getCal() {
		return cal;
	}


	public void setCal(Date cal) {
		this.cal = cal;
	}


	@PostConstruct
	public void onInit(){
		
		espDAO = new EsporteDAOImpl(em);
		esportes = espDAO.buscarTodosEsportes();
		setEdm(new EsporteDataModel(esportes));
		evento = new Evento();
		
		evento.setDtEvento(Calendar.getInstance());
		
		cal = new Date(evento.getDtEvento().getTimeInMillis());
		
//		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//		formato.format(evento.getDtEvento().getTime().getTime());
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		
	}
	
	
	public String btnConvidar(){
		return "convite_evento";
	}
	
	public List<Esporte> getEsportes() {
		return esportes;
	}
	
	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
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
	
	public Esporte[] getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(Esporte[] espSelecionados) {
		this.espSelecionados = espSelecionados;
	}


	public EsporteDataModel getEdm() {
		return edm;
	}


	public void setEdm(EsporteDataModel edm) {
		this.edm = edm;
	}
	
}
