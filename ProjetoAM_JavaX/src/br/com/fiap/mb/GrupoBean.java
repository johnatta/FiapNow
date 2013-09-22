package br.com.fiap.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private BigDecimal numMembros;
	private List<Pessoa> modsGp;
	private List<Pessoa> membrosGrp;
	private List<Pessoa> moderadorGrp;
	//Utilizado para o f:setPropertyActionListener passar o codigo do grupo criado por ele
	private int codGrupo;
	private Grupo grupo;
	private ComentarioGrupo cmtGrupo;
	private GrupoDAO gruDAO;
	private PessoaDAO pDAO;
	private ModeradorGrupoDAO modGpDAO;
	private ModeradorGrupoDAO moderadorGpDAO;
	
	public void buscaGrupo(){
		if(codGrupo == 0 ){
			CriacaoGrupoBean criacaoGrupoBean = (CriacaoGrupoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("criacaoGrupoBean");
			codGrupo = criacaoGrupoBean.getGrupo().getCodGrupo();
		}
		gruDAO = new GrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		modGpDAO = new ModeradorGrupoDAOImpl(em);
		moderadorGpDAO = new ModeradorGrupoDAOImpl(em);
		
		modsGp = moderadorGpDAO.buscarModeradoresDoGrupo(codGrupo);
		grupo = gruDAO.buscarInfoGrupo(codGrupo);
		numMembros = gruDAO.buscarNumeroMembros(codGrupo);
		moderadorGrp = modGpDAO.buscarModeradoresDoGrupoRowNum(codGrupo);
		membrosGrp = pDAO.buscarMembrosDoGrupoRow(codGrupo);
	}
	public String visualizarTodosMembros(){
		String retorno = "todos_membros_grupo";
		return retorno;
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

	public BigDecimal getNumMembros() {
		return numMembros;
	}

	public void setNumMembros(BigDecimal numMembros) {
		this.numMembros = numMembros;
	}

	public List<Pessoa> getModsGp() {
		return modsGp;
	}

	public void setModsGp(List<Pessoa> modsGp) {
		this.modsGp = modsGp;
	}

	public List<Pessoa> getMembrosGrp() {
		return membrosGrp;
	}

	public void setMembrosGrp(List<Pessoa> membrosGrp) {
		this.membrosGrp = membrosGrp;
	}

	
	public List<Pessoa> getModeradorGrp() {
		return moderadorGrp;
	}
	public void setModeradorGrp(List<Pessoa> moderadorGrp) {
		this.moderadorGrp = moderadorGrp;
	}
	public ComentarioGrupo getCmtGrupo() {
		return cmtGrupo;
	}

	public void setCmtGrupo(ComentarioGrupo cmtGrupo) {
		this.cmtGrupo = cmtGrupo;
	}

	public StreamedContent getFoto() {
		FacesContext context = FacesContext.getCurrentInstance();
		DefaultStreamedContent content = new DefaultStreamedContent();
		content.setContentType("image/jpg");
		try{
			if (context.getRenderResponse() || grupo.getImgGrupo() == null){
				content.setStream(context.getExternalContext().getResourceAsStream("/resources/img/semFoto.jpg"));
			}else{
				content.setStream(new ByteArrayInputStream(grupo.getImgGrupo()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
	

}
