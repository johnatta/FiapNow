package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
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
@RequestScoped
public class ModeradorGrupoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private int codGrupo;
	private List<Pessoa> moderadores; 
	private List<Pessoa> membrosGrp;
	private Pessoa[] moderadorSelecionados;
	private Pessoa[] modSelecionadosExc;
	private Grupo grupo;
	private Pessoa pessoa;
	private PessoaDataModel mdm;
	private PessoaDataModel mdmExc;
	private GrupoDAO gruDAO;

	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public void infoGrupo(){
		grupo = gruDAO.searchByID(codGrupo);
		membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
		moderadores = grupo.getModeradores();
		mdm = new PessoaDataModel(membrosGrp);
		mdmExc = new PessoaDataModel(moderadores);
	}

	@PostConstruct
	public void onInit() {
		gruDAO = new GrupoDAOImpl(em);
		pessoa = new Pessoa();
	}

	/**
	 * Realiza a remoção do moderador daquele grupo
	 * @author Graziele Vasconcelos 
	 */
	public void excluirModeradorDoGrupo(){
		for (Pessoa moderador : modSelecionadosExc){
			for (int i = 0; i < grupo.getModeradores().size() ; i++) {
				if(grupo.getModeradores().get(i).getCodPessoa() == moderador.getCodPessoa()){
					grupo.getModeradores().remove(i);
					gruDAO.update(grupo);
				}
			}
		}		

	}

	/**
	 * Realiza o privilégio de moderador para membros selecionados
	 * @author Graziele Vasconcelos
	 */
	public void addModerador(){
		for (Pessoa moderador : moderadorSelecionados){
			grupo.getModeradores().add(moderador);
			gruDAO.update(grupo);
		}
	}
	
	/**
	 * Desabilita o moderador passando-o para apenas membro do grupo
	 * @param codPessoa
	 * @author Graziele Vasconcelos
	 */
	public void desabilitarModerador(int codPessoa){
		for (Pessoa moderador : modSelecionadosExc){
			for (int i = 0; i < grupo.getMembros().size() ; i++) {
				if(grupo.getMembros().get(i).getCodPessoa() == moderador.getCodPessoa()){
					grupo.getMembros().remove(i);
					gruDAO.update(grupo);
				}
			}
		}	
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

	public Pessoa[] getModeradorSelecionados() {
		return moderadorSelecionados;
	}

	public void setModeradorSelecionados(Pessoa[] moderadorSelecionados) {
		this.moderadorSelecionados = moderadorSelecionados;
	}

	public Pessoa[] getModSelecionadosExc() {
		return modSelecionadosExc;
	}

	public void setModSelecionadosExc(Pessoa[] modSelecionadosExc) {
		this.modSelecionadosExc = modSelecionadosExc;
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
