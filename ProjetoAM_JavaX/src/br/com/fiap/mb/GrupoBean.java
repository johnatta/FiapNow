package br.com.fiap.mb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import org.apache.commons.io.IOUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioGrupoRC;

@ManagedBean
@SessionScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private BigDecimal numMembros;
	private List<Pessoa> membrosGrp;
	private List<Pessoa> moderadores;
	private List<Pessoa> membrosGrpRow;
	private List<Pessoa> moderadoresRow;
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
	private List<Evento> proximosEventos;
	private List<Evento> historicoEventos;
	boolean flagAdm = false; 
	boolean flagModerador = false; 
	boolean flagMembro = false; 
	boolean flagUser = false; 
	private int comentarioGrupoExcluido;
	private Grupo edicaoGrupo; 
	private List<Privacidade> privs;
	private EsporteDataModel edm;
	private Esporte[] espSelecionados;
	private List<Esporte> esportes;
	private List<Esporte> listEsporte;
	
	public void buscaGrupo(){
		if (primeiraVez){
			primeiraVez = false;

			if(codGrupo == 0 ){
				CriacaoGrupoBean criacaoGrupoBean = (CriacaoGrupoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("criacaoGrupoBean");
				codGrupo = criacaoGrupoBean.getGrupo().getCodGrupo();
			}

			grupo = gruDAO.buscarInfoGrupo(codGrupo);
			numMembros = gruDAO.buscarNumeroMembros(codGrupo);
			moderadoresRow = modDAO.buscarModeradoresDoGrupoRowNum(codGrupo);
			membrosGrpRow = gruDAO.buscarMembrosDoGrupoRow(codGrupo);
			membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
			moderadores = modDAO.buscarModeradoresDoGrupo(codGrupo);
			listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);
			proximosEventos = gruDAO.buscarProximosEventos(codGrupo);
			historicoEventos = gruDAO.buscarHistoricoEvento(codGrupo);

			
			for(Pessoa moderadorGrupo : moderadores){
				if(pessoa.getCodPessoa() == moderadorGrupo.getCodPessoa()){
					flagModerador = true;
				}	

			}

			if(pessoa.getCodPessoa() == grupo.getAdm().getCodPessoa()){
				flagAdm = true;
			}
			
			for(Grupo g : pessoa.getGruposParticipantes()){				
				if(g.getCodGrupo() == codGrupo){
					flagMembro = true;
				}else{
					flagUser = true;
				}
			}
		}
	}

	@PostConstruct
	public void onInit(){
		edicaoGrupo = new Grupo();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		comentarioGrupoDAO = new ComentarioGrupoDAOImpl(em);
		gruDAO = new GrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		modDAO = new ModeradorGrupoDAOImpl(em);
		comentarioGrupo = new ComentarioGrupo();
		listEsporte = new ArrayList<Esporte>();
		esportes = espDAO.buscarTodosEsportes();		
		
		this.privs = Arrays.asList(edicaoGrupo.getPrivacidade().values());
		edm = new EsporteDataModel(esportes); 

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();

		primeiraVez = true;
	}

	public String dataFormatada(Calendar dataComentario){
		Date data = dataComentario.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		String dataFormatada = sdf.format(data);
		return dataFormatada; 
	}

	public String sairGrupo(){
		for (int i = 0; i < pessoa.getGruposParticipantes().size() ; i++) {
			if(pessoa.getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
				pessoa.getGruposParticipantes().remove(i);
			}
		}
		pDAO.update(pessoa);
		return "grupos.xhtml";
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

	public void excluirComentario(){
		System.out.println();
		//comentarioGrupo = comentarioGrupoDAO.searchByID(comentarioGrupoExcluido);
		//comentarioGrupoDAO.remove(comentarioGrupo);
		//listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);
	}
	
	public String visualizarTodosMembros(){
		primeiraVez = true;
		return "todos_membros_grupo.xhtml";
	}

	public String visualizarTodosModeradores(){
		primeiraVez = true;
		return "todos_moderadores_grupo.xhtml";
	}	

	public String btnExclusao(){
		primeiraVez = true;
		return "exclusao_grupo.xhtml";
	}

	public String excluirGrupo(){
		gruDAO.removeById(grupo.getCodGrupo());
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Grupo excluído com sucesso.");
		fc.addMessage("", fm);
		return "grupos.xhtml";
	}

	public String paginaGrupo(){
		return "grupo.xhtml";
	}
	
	public String edicaoGrupo(){
		primeiraVez = true;
		edicaoGrupo = gruDAO.searchByID(grupo.getCodGrupo());
		return "edicao_grupo.xhtml";
	}
	
	public String salvarEdicao(){
		for (Esporte esporte : getEspSelecionados()) {
			listEsporte.add(esporte);
		}
		if (getEspSelecionados().length != 0){
			edicaoGrupo.setEsportes(listEsporte);
		}
		return "grupos.xhtml";
	}
	
	public void excluirMembro(int codPessoa){
		for (int i = 0; i < pDAO.searchByID(codPessoa).getGruposParticipantes().size() ; i++) {
			if(pDAO.searchByID(codPessoa).getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
				pDAO.searchByID(codPessoa).getGruposParticipantes().remove(i);
			}
		}
		pDAO.update(pessoa);
		membrosGrp = gruDAO.buscarMembrosDoGrupo(grupo.getCodGrupo());
	}
	
	public void excluirModerador(int codPessoa){
		
		modDAO.removeById(codPessoa);
		
		for (int i = 0; i < pDAO.searchByID(codPessoa).getGruposParticipantes().size() ; i++) {
			if(pDAO.searchByID(codPessoa).getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
				pDAO.searchByID(codPessoa).getGruposParticipantes().remove(i);
			}
		}
		pDAO.update(pessoa);
		moderadores = modDAO.buscarModeradoresDoGrupo(grupo.getCodGrupo());
	}
	
	public void realizarUpload(FileUploadEvent event) {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			edicaoGrupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
			FacesMessage fm = new FacesMessage("Upload Concluído com Sucesso!");
			fc.addMessage("messages", fm);
		} catch (IOException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Erro no Processo de Upload!");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
			e.printStackTrace();
		}
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

	public List<Pessoa> getMembrosGrpRow() {
		return membrosGrpRow;
	}

	public void setMembrosGrpRow(List<Pessoa> membrosGrpRow) {
		this.membrosGrpRow = membrosGrpRow;
	}

	public List<Pessoa> getModeradoresRow() {
		return moderadoresRow;
	}

	public void setModeradoresRow(List<Pessoa> moderadoresRow) {
		this.moderadoresRow = moderadoresRow;
	}

	public List<Evento> getProximosEventos() {
		return proximosEventos;
	}

	public void setProximosEventos(List<Evento> proximosEventos) {
		this.proximosEventos = proximosEventos;
	}

	public List<Evento> getHistoricoEventos() {
		return historicoEventos;
	}

	public void setHistoricoEventos(List<Evento> historicoEventos) {
		this.historicoEventos = historicoEventos;
	}

	public boolean isFlagAdm() {
		return flagAdm;
	}

	public void setFlagAdm(boolean flagAdm) {
		this.flagAdm = flagAdm;
	}

	public boolean isFlagModerador() {
		return flagModerador;
	}

	public void setFlagModerador(boolean flagModerador) {
		this.flagModerador = flagModerador;
	}

	public boolean isFlagMembro() {
		return flagMembro;
	}

	public void setFlagMembro(boolean flagMembro) {
		this.flagMembro = flagMembro;
	}

	public boolean isFlagUser() {
		return flagUser;
	}

	public void setFlagUser(boolean flagUser) {
		this.flagUser = flagUser;
	}

	public int getComentarioGrupoExcluido() {
		return comentarioGrupoExcluido;
	}

	public void setComentarioGrupoExcluido(int comentarioGrupoExcluido) {
		this.comentarioGrupoExcluido = comentarioGrupoExcluido;
	}

	public Grupo getEdicaoGrupo() {
		return edicaoGrupo;
	}

	public void setEdicaoGrupo(Grupo edicaoGrupo) {
		this.edicaoGrupo = edicaoGrupo;
	}

	public List<Privacidade> getPrivs() {
		return privs;
	}

	public void setPrivs(List<Privacidade> privs) {
		this.privs = privs;
	}

	public EsporteDataModel getEdm() {
		return edm;
	}

	public void setEdm(EsporteDataModel edm) {
		this.edm = edm;
	}

	public Esporte[] getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(Esporte[] espSelecionados) {
		this.espSelecionados = espSelecionados;
	}

	public List<Esporte> getEsportes() {
		return esportes;
	}

	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}

	public List<Esporte> getListEsporte() {
		return listEsporte;
	}

	public void setListEsporte(List<Esporte> listEsporte) {
		this.listEsporte = listEsporte;
	}

 	
}
