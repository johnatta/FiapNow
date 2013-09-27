package br.com.fiap.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.datamodel.PessoaDataModel;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@SessionScoped
public class CriarEventoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private Evento evento;
	private Grupo grupo;
	private Esporte esporte;
	private List<Esporte> esportes;
	private Esporte esporteSelecionado;
	private EsporteDAO espDAO;
	private Pessoa pessoa;
	private PessoaDAO pesDAO;
	private EsporteDataModel edm;
	private Date cal;
	private Endereco endereco;
	private PessoaDataModel pdm;
	private PessoaDataModel mdm;
	private Pessoa[] membrosSelecionados;
	private Pessoa[] moderadorSelecionados;
	
	@PostConstruct
	public void onInit(){

		espDAO = new EsporteDAOImpl(em);
		esportes = espDAO.buscarTodosEsportes();
		evento = new Evento();
		endereco = new Endereco();
		
		evento.setDtEvento(Calendar.getInstance());

		cal = new Date(evento.getDtEvento().getTimeInMillis());

		edm = new EsporteDataModel(esportes);

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();

	}

	public String btnConvidar(){
		System.out.println(getEsporteSelecionado());	
		evento.setCodEsporte(getEsporteSelecionado());


		//evento.setCodEndereco(codEndereco);
		return "convite_evento";
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Date getCal() {
		return cal;
	}

	public void setCal(Date cal) {
		this.cal = cal;
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

	public EsporteDataModel getEdm() {
		return edm;
	}

	public void setEdm(EsporteDataModel edm) {
		this.edm = edm;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public Esporte getEsporteSelecionado() {
		return esporteSelecionado;
	}

	public void setEsporteSelecionado(Esporte esporteSelecionado) {
		this.esporteSelecionado = esporteSelecionado;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaDataModel getPdm() {
		return pdm;
	}

	public void setPdm(PessoaDataModel pdm) {
		this.pdm = pdm;
	}

	public PessoaDataModel getMdm() {
		return mdm;
	}

	public void setMdm(PessoaDataModel mdm) {
		this.mdm = mdm;
	}

	public Pessoa[] getMembrosSelecionados() {
		return membrosSelecionados;
	}

	public void setMembrosSelecionados(Pessoa[] membrosSelecionados) {
		this.membrosSelecionados = membrosSelecionados;
	}

	public Pessoa[] getModeradorSelecionados() {
		return moderadorSelecionados;
	}

	public void setModeradorSelecionados(Pessoa[] moderadorSelecionados) {
		this.moderadorSelecionados = moderadorSelecionados;
	}

}
