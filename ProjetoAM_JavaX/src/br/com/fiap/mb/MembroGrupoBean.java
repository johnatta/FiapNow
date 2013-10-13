package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
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

public class MembroGrupoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private Pessoa[] membrosSelecionadosExc;
	private Pessoa[] membrosSelecionadosAdd;
	private List<Pessoa> pessoas;
	private Grupo grupo;
	private PessoaDataModel pdm;
	private PessoaDataModel pdmExc;
	private ConviteGrupo convite;
	private GrupoDAO gruDAO;
	private ConviteGrupoDAO conviteDAO;

	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public MembroGrupoBean(Grupo grupo){
		gruDAO = new GrupoDAOImpl(em);
		conviteDAO = new ConviteGrupoDAOImpl(em);
		convite = new ConviteGrupo();
		this.grupo = grupo;
		pessoas = gruDAO.buscarPessoasParaAdicionarAoGrupo(grupo.getCodGrupo());
		pdm = new PessoaDataModel(pessoas);
		pdmExc = new PessoaDataModel(grupo.getMembros());
	}

	/**
	 * Realiza a exclusão de uma lista de membro do grupo
	 * @author Graziele Vasconcelos
	 */
	public void excluirMembro(){
		for (Pessoa membro : getMembrosSelecionadosExc()){
			grupo.getMembros().remove(membro);
			gruDAO.update(grupo);
		}
		pdmExc = new PessoaDataModel(grupo.getMembros());
	}
	/**
	 * Realiza o convite para o usuário aderir ao grupo
	 * @author Graziele Vasconcelos
	 */
	public void addMembroGrupo(){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(getMembrosSelecionadosAdd().length != 0){
			for (Pessoa membro : getMembrosSelecionadosAdd()){
				convite.setDescricao("Nós do grupo "+grupo.getNomeGrupo() + " convidamos você, " + membro.getNome() + " para participar do nosso grupo.");
				convite.setGrupo(grupo);
				convite.setPessoa(membro);
				conviteDAO.insert(convite);
			} 

			pdm = new PessoaDataModel(pessoas);

			FacesMessage fm = new FacesMessage();
			fm.setSummary("Convite enviado.");
			fc.addMessage("", fm);
		}
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
