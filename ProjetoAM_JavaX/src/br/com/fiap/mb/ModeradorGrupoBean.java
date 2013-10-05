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
import br.com.fiap.datamodel.PessoaDataModel;
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
	private List<Pessoa> membrosGrp;
	private ModeradorGrupo moderadorGrupo;
	private Pessoa[] moderadorSelecionados;
	private Pessoa[] moderadorSelecionadosExc;
	private PessoaDataModel mdm;
	private PessoaDataModel mdmExc;

	public void infoGrupo(){
		moderadores = modDAO.buscarModeradoresDoGrupo(codGrupo);
		grupo = gruDAO.searchByID(codGrupo);
		membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
		mdm = new PessoaDataModel(membrosGrp);
		mdmExc = new PessoaDataModel(moderadores);
	}

	@PostConstruct
	public void onInit() {
		gruDAO = new GrupoDAOImpl(em);
		modDAO = new ModeradorGrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		pessoa = new Pessoa();
	}

	public void excluirModeradorDoGrupo(){
		for (Pessoa moderador : getModeradorSelecionadosExc()){
			moderadorGrupo =  modDAO.buscarModeradorGrupo(grupo.getCodGrupo(), moderador.getCodPessoa());
			moderadorGrupo.setGrupo(grupo);
			moderadorGrupo.setPessoa(moderador);
			modDAO.remove(moderadorGrupo);
			for (int i = 0; i < moderador.getGruposParticipantes().size() ; i++) {
				if(moderador.getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
					moderador.getGruposParticipantes().remove(i);
					pDAO.update(pessoa);
				}
			}
		}		

	}

	public void addModerador(){
		for (Pessoa moderador : getModeradorSelecionados()){
			moderadorGrupo.setGrupo(gruDAO.buscarInfoGrupo(codGrupo));
			moderadorGrupo.setPessoa(moderador);
			modDAO.insert(moderadorGrupo);
		}

	}

	public void desabilitarModerador(int codPessoa){
		moderadorGrupo =  modDAO.buscarModeradorGrupo(grupo.getCodGrupo(), codPessoa);
		modDAO.remove(moderadorGrupo);
		moderadores = modDAO.buscarModeradoresDoGrupo(grupo.getCodGrupo());

		pessoa = pDAO.searchByID(codPessoa);
		pessoa.getGruposParticipantes().add(grupo);
		pDAO.update(pessoa);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getModeradores() {
		return moderadores;
	}

	public void setModeradores(List<Pessoa> moderadores) {
		this.moderadores = moderadores;
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

	public List<Pessoa> getMembrosGrp() {
		return membrosGrp;
	}

	public void setMembrosGrp(List<Pessoa> membrosGrp) {
		this.membrosGrp = membrosGrp;
	}

	public ModeradorGrupo getModeradorGrupo() {
		return moderadorGrupo;
	}

	public void setModeradorGrupo(ModeradorGrupo moderadorGrupo) {
		this.moderadorGrupo = moderadorGrupo;
	}

	public Pessoa[] getModeradorSelecionados() {
		return moderadorSelecionados;
	}

	public void setModeradorSelecionados(Pessoa[] moderadorSelecionados) {
		this.moderadorSelecionados = moderadorSelecionados;
	}

	public Pessoa[] getModeradorSelecionadosExc() {
		return moderadorSelecionadosExc;
	}

	public void setModeradorSelecionadosExc(Pessoa[] moderadorSelecionadosExc) {
		this.moderadorSelecionadosExc = moderadorSelecionadosExc;
	}

	public PessoaDataModel getMdm() {
		return mdm;
	}

	public void setMdm(PessoaDataModel mdm) {
		this.mdm = mdm;
	}

	public PessoaDataModel getMdmExc() {
		return mdmExc;
	}

	public void setMdmExc(PessoaDataModel mdmExc) {
		this.mdmExc = mdmExc;
	}


}
