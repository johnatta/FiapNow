package br.com.fiap.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
	private int codGrupo;
	private List<Pessoa> moderadores; 
	private List<Pessoa> membrosGrp;
	private Pessoa[] moderadorSelecionados;
	private Pessoa[] moderadorSelecionadosExc;
	private ModeradorGrupo moderadorGrupo;
	private Grupo grupo;
	private Pessoa pessoa;
	private PessoaDataModel mdm;
	private PessoaDataModel mdmExc;
	private PessoaDAO pDAO ;
	private GrupoDAO gruDAO;
	private ModeradorGrupoDAO modDAO;

	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public void infoGrupo(){
		grupo = gruDAO.searchByID(codGrupo);
		moderadores = modDAO.buscarModeradoresDoGrupo(codGrupo);
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

	/**
	 * Realiza a remoção do moderador daquele grupo
	 * @author Graziele Vasconcelos 
	 */
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

	/**
	 * Realiza o privilégio de moderador para membros selecionados
	 * @author Graziele Vasconcelos
	 */
	public void addModerador(){
		for (Pessoa moderador : getModeradorSelecionados()){
			moderadorGrupo.setGrupo(gruDAO.buscarInfoGrupo(codGrupo));
			moderadorGrupo.setPessoa(moderador);
			modDAO.insert(moderadorGrupo);
		}
	}
	
	/**
	 * Desabilita o moderador passando-o para apenas membro do grupo
	 * @param codPessoa
	 * @author Graziele Vasconcelos
	 */
	public void desabilitarModerador(int codPessoa){
		moderadorGrupo =  modDAO.buscarModeradorGrupo(grupo.getCodGrupo(), codPessoa);
		modDAO.remove(moderadorGrupo);
		//moderadores = modDAO.buscarModeradoresDoGrupo(grupo.getCodGrupo());
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
