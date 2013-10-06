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
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.PessoaDataModel;
import br.com.fiap.entity.ConviteGrupo;
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
	private Pessoa[] membrosSelecionadosExc;
	private Pessoa[] membrosSelecionadosAdd;
	private PessoaDataModel pdm;
	private PessoaDataModel pdmExc;
	private List<Pessoa> pessoas;
	private Grupo grupo;
	private int codGrupo;
	private boolean primeiraVez = true;
	private ConviteGrupo convite;
	private ConviteGrupoDAO conviteDAO;

	public void infoGrupo(){
		if(primeiraVez){
			primeiraVez = false;
			membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
			pessoas = gruDAO.buscarPessoasParaAdicionarAoGrupo(codGrupo);
			grupo = gruDAO.searchByID(codGrupo);
			pdm = new PessoaDataModel(pessoas);
			pdmExc = new PessoaDataModel(membrosGrp);
		}
	}

	@PostConstruct
	public void onInit() {
		gruDAO = new GrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		conviteDAO = new ConviteGrupoDAOImpl(em);
		pessoa = new Pessoa();
		convite = new ConviteGrupo();
	}

	public void excluirMembro(){
		for (Pessoa membro : getMembrosSelecionadosExc()){
			for (int i = 0; i < membro.getGruposParticipantes().size() ; i++) {
				if(membro.getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
					membro.getGruposParticipantes().remove(i);
					pDAO.update(membro);
				}
			}
		}	
		membrosGrp = gruDAO.buscarMembrosDoGrupo(grupo.getCodGrupo());
	}

	public void addMembroGrupo(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(getMembrosSelecionadosAdd().length != 0){
			for (Pessoa membro : getMembrosSelecionadosAdd()){
				convite.setDescricao("N�s do grupo "+grupo.getNomeGrupo() + " convidamos voc�, " + membro.getNome() + " para participar do nosso grupo.");
				convite.setGrupo(grupo);
				convite.setPessoa(membro);
				conviteDAO.insert(convite);
				//membro.getGruposParticipantes().add(grupo);
				//pDAO.update(membro);
			} 
			//membrosGrp = gruDAO.buscarMembrosDoGrupo(grupo.getCodGrupo());
			membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
			pdm = new PessoaDataModel(pessoas);
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Convite enviado.");
			fc.addMessage("", fm);
		}
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

	public Pessoa[] getMembrosSelecionadosExc() {
		return membrosSelecionadosExc;
	}

	public void setMembrosSelecionadosExc(Pessoa[] membrosSelecionadosExc) {
		this.membrosSelecionadosExc = membrosSelecionadosExc;
	}

	public Pessoa[] getMembrosSelecionadosAdd() {
		return membrosSelecionadosAdd;
	}

	public void setMembrosSelecionadosAdd(Pessoa[] membrosSelecionadosAdd) {
		this.membrosSelecionadosAdd = membrosSelecionadosAdd;
	}

	public PessoaDataModel getPdmExc() {
		return pdmExc;
	}

	public void setPdmExc(PessoaDataModel pdmExc) {
		this.pdmExc = pdmExc;
	}

	public ConviteGrupo getConvite() {
		return convite;
	}

	public void setConvite(ConviteGrupo convite) {
		this.convite = convite;
	}

}