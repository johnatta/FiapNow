package br.com.fiap.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.PessoaDataModel;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@SessionScoped
public class MembroGrupoBean {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private PessoaDAO pDAO ;
	private GrupoDAO gruDAO;
	private Pessoa pessoa;
	private List<Pessoa> membrosGrp;
	private Pessoa[] membrosSelecionados;
	private PessoaDataModel pdm;
	private List<Pessoa> pessoas;
	private Grupo grupo;
	private int codGrupo;
	private boolean primeiraVez = true;
	
	public void infoGrupo(){
		if(primeiraVez){
			primeiraVez = false;
			membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
			pessoas = pDAO.buscarTodasPessoas();
			grupo = gruDAO.searchByID(codGrupo);
			pdm = new PessoaDataModel(pessoas);
		}
	}
	
	@PostConstruct
	public void onInit() {
		gruDAO = new GrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		pessoa = new Pessoa();
	}
	
	public void excluirMembro(int codPessoa){
		pessoa = pDAO.searchByID(codPessoa);
		for (int i = 0; i < pessoa.getGruposParticipantes().size() ; i++) {
			if(pessoa.getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
				pessoa.getGruposParticipantes().remove(i);
			}
		}
		pDAO.update(pessoa);
		membrosGrp = gruDAO.buscarMembrosDoGrupo(grupo.getCodGrupo());
	}
	
	public void relacionarMembros() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if(getMembrosSelecionados().length != 0){
			for (Pessoa membro : getMembrosSelecionados()){
				membro.getGruposParticipantes().add(grupo);
				pDAO.update(membro);
				membrosGrp = gruDAO.buscarMembrosDoGrupo(grupo.getCodGrupo());
			} 
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Membros relacionados, faça o próximo passo.");
			fc.addMessage("", fm);
		}
	} 
	
	public String addMembroGrupo(){
		return "adicionar_membro_grupo.xhtml" ;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getMembrosGrp() {
		return membrosGrp;
	}

	public void setMembrosGrp(List<Pessoa> membrosGrp) {
		this.membrosGrp = membrosGrp;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}

	public Pessoa[] getMembrosSelecionados() {
		return membrosSelecionados;
	}

	public void setMembrosSelecionados(Pessoa[] membrosSelecionados) {
		this.membrosSelecionados = membrosSelecionados;
	}

	public PessoaDataModel getPdm() {
		return pdm;
	}

	public void setPdm(PessoaDataModel pdm) {
		this.pdm = pdm;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	
}
