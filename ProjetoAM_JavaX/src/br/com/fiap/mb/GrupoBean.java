package br.com.fiap.mb;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.rc.ComentarioGrupoRC;

@ManagedBean
@SessionScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private BigDecimal numMembros;
	private List<Pessoa> membrosGrp;
	private List<Pessoa> moderadores;
	private int codGrupo;
	private Grupo grupo;
	private ComentarioGrupo cmtGrupo;
	private GrupoDAO gruDAO;
	private PessoaDAO pDAO;
	private ModeradorGrupoDAO modDAO ;
	private ComentarioGrupo comentarioGrupo;
	private ComentarioGrupoDAO comentarioGrupoDAO;
	private ComentarioGrupo comentarioPostado;
	private Pessoa pessoa;
	private boolean primeiraVez;
	private List<ComentarioGrupoRC> listaComentarios;
	private Date cal;


	public void buscaGrupo(){
		if (primeiraVez){
			primeiraVez = false;
		
			if(codGrupo == 0 ){
				CriacaoGrupoBean criacaoGrupoBean = (CriacaoGrupoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("criacaoGrupoBean");
				codGrupo = criacaoGrupoBean.getGrupo().getCodGrupo();
			}
			
			grupo = gruDAO.buscarInfoGrupo(codGrupo);
			numMembros = gruDAO.buscarNumeroMembros(codGrupo);
			moderadores = modDAO.buscarModeradoresDoGrupoRowNum(codGrupo);
			membrosGrp = pDAO.buscarMembrosDoGrupoRow(codGrupo);
			listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);
		}
	}
	
	@PostConstruct
	public void onInit(){
		comentarioGrupoDAO = new ComentarioGrupoDAOImpl(em);
		gruDAO = new GrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		modDAO = new ModeradorGrupoDAOImpl(em);
		comentarioGrupo = new ComentarioGrupo();
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		primeiraVez = true;
	}
	
	public String dataFormatada(Calendar dataComentario){
		//Calendar dataC = comentarioGrupo.getDataHora().getInstance();
		//Date data = dataC.getTime();
		//Date data = dataComentario.getInstance().getTime();
		//System.out.println(data.getTime());
		Date data = dataComentario.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		String dataFormatada = sdf.format(data);
		return dataFormatada; 
	}
	
	public void btnEnviarComentario(){
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		comentarioPostado = new ComentarioGrupo();

		comentarioGrupo.setCodGrupo(grupo);
		comentarioGrupo.setCodPessoa(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		comentarioGrupo.setDataHora(Calendar.getInstance());
		comentarioPostado = comentarioGrupoDAO.insertEntity(comentarioGrupo);
		
		listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Comentario cadastrado");
		fc.addMessage("", fm);
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

	public List<Pessoa> getMembrosGrp() {
		return membrosGrp;
	}

	public void setMembrosGrp(List<Pessoa> membrosGrp) {
		this.membrosGrp = membrosGrp;
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
	public List<Pessoa> getModeradores() {
		return moderadores;
	}
	public void setModeradores(List<Pessoa> moderadores) {
		this.moderadores = moderadores;
	}

	public ComentarioGrupo getComentarioGrupo() {
		return comentarioGrupo;
	}

	public void setComentarioGrupo(ComentarioGrupo comentarioGrupo) {
		this.comentarioGrupo = comentarioGrupo;
	}

	public ComentarioGrupo getComentarioPostado() {
		return comentarioPostado;
	}

	public void setComentarioPostado(ComentarioGrupo comentarioPostado) {
		this.comentarioPostado = comentarioPostado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<ComentarioGrupoRC> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<ComentarioGrupoRC> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}
	
}
