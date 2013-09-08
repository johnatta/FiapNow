package br.com.fiap.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@SessionScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private BigDecimal numMembros;
	private List<Pessoa> modsGp;
	private List<Pessoa> membrosGrp;
	private List<Pessoa> membrosGrpRow;
	//Utilizado para o f:setPropertyActionListener passar o codigo do grupo criado por ele
	private int codGrupo;
	private Pessoa pessoa;
	private Grupo grupo;
	private ComentarioGrupo cmtGrupo;
	//private ModeradorGrupo moderador;
	//private Pessoa membro;

	@PostConstruct
	public void meusGrupos(){
		GrupoDAO gruDAO = new GrupoDAOImpl(em);
		grupo = new Grupo();
		grupo = gruDAO.searchByID(2);
		/*
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		CriacaoGrupoBean idGrupo = (CriacaoGrupoBean)map.get("criacaoGrupoBean"); 
		grupo = gruDAO.searchByID(idGrupo.getGrupo().getCodGrupo());
		System.out.println("CODIGO GRUPO :  " + grupo.getCodGrupo());
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		grupo.setAdm(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		 */
		//ModeradorGrupoDAO modGpDAO = new ModeradorGrupoDAOImpl(em);
		//PessoaDAO pDAO = new PessoaDAOImpl(em);
		//membrosGrpRow = new ArrayList<Pessoa>(); 
		//modsGp = new ArrayList<Pessoa>();
		//moderador = new ModeradorGrupo();

		//membro = new Pessoa();
		//numMembros = gruDAO.buscarNumeroMembros(grupo.getCodGrupo());
		//modsGp = modGpDAO.buscarModeradoresDoGrupo(grupo.getCodGrupo());
		//membrosGrp = pDAO.buscarMembrosDoGrupo(getCodGrupo());
		//membrosGrpRow = modGpDAO.buscarModeradoresDoGrupoRowNum(grupo.getCodGrupo());
		//cmtGrupo = new ComentarioGrupo();
		//ComentarioGrupoDAO cmtGrupoDAO = new ComentarioGrupoDAOImpl(em);
		//gDAO.
	}

	/*	
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
	 */


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

	public List<Pessoa> getMembrosGrpRow() {
		return membrosGrpRow;
	}

	public void setMembrosGrpRow(List<Pessoa> membrosGrpRow) {
		this.membrosGrpRow = membrosGrpRow;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Grupo getPgGrupo() {
		return grupo;
	}

	public void setPgGrupo(Grupo pgGrupo) {
		this.grupo = pgGrupo;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


}
