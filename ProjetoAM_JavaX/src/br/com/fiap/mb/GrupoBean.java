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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

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
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioGrupoRC;

@ManagedBean
@SessionScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private boolean primeiraVez;
	private boolean flagAdm = false; 
	private boolean flagModerador = false; 
	private boolean flagMembro = false; 
	private boolean flagUser = false;
	private boolean flagUserFechado = false; 
	private BigDecimal numMembros;
	private int comentarioGrupoExcluido;
	private int codGrupo;
	private List<Pessoa> membrosGrp;
	private List<Pessoa> moderadores;
	private List<Pessoa> membrosGrpRow;
	private List<Pessoa> moderadoresRow;
	private List<Evento> proximosEventos;
	private List<Evento> historicoEventos;
	private List<ComentarioGrupoRC> listaComentarios;
	private List<Privacidade> privs;
	private List<Esporte> listEsporte;
	private Esporte[] espSelecionados;
	private ComentarioGrupo comentarioGrupo;
	private ComentarioGrupo comentarioPostado;
	private Pessoa pessoa;
	private Grupo grupo;
	private Grupo edicaoGrupo; 
	private EsporteDataModel edm;
	private ComentarioGrupoDAO comentarioGrupoDAO;
	private ModeradorGrupoDAO modDAO ;
	private PessoaDAO pDAO;
	private GrupoDAO gruDAO;

	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public void buscaGrupo(){
		if(primeiraVez){
			primeiraVez = false;
			if(codGrupo == 0 ){
				CriacaoGrupoBean criacaoGrupoBean = (CriacaoGrupoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("criacaoGrupoBean");
				codGrupo = criacaoGrupoBean.getGrupo().getCodGrupo();
			}

			grupo = gruDAO.searchByID(codGrupo);
			numMembros = gruDAO.buscarNumeroMembros(codGrupo);
			moderadoresRow = modDAO.buscarModeradoresDoGrupoRowNum(codGrupo);
			membrosGrpRow = gruDAO.buscarMembrosDoGrupoRow(codGrupo);
			membrosGrp = gruDAO.buscarMembrosDoGrupo(codGrupo);
			moderadores = modDAO.buscarModeradoresDoGrupo(codGrupo);
			listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);
			proximosEventos = gruDAO.buscarProximosEventos(codGrupo);
			historicoEventos = gruDAO.buscarHistoricoEvento(codGrupo);

			/**
			 * Realizando comparaçõs para ter o conhecimento se o usuário é:
			 * Administrador, Moderador, Membro do Grupo e não membro.
			 * Também verifica se o grupo é fechado(apenas membros pode ver o conteúdo).
			 * 
			 * @author Graziele Vasconcelos
			 */
			
			for(Grupo g : pessoa.getGruposParticipantes()){				
				if(g.getCodGrupo() == codGrupo){
					flagMembro = true;
				}else{
					if(grupo.getPrivacidade() == Privacidade.Fechado){
						flagUserFechado = true;
					}else{
						flagUser = true;
					}
				}
			}		

			for(Pessoa moderadorGrupo : moderadores){
				if(pessoa.getCodPessoa() == moderadorGrupo.getCodPessoa()){
					flagModerador = true;
				}	
			}

			if(pessoa.getCodPessoa() == grupo.getAdm().getCodPessoa()){
				flagAdm = true;
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

		this.privs = Arrays.asList(edicaoGrupo.getPrivacidade().values());
		edm = new EsporteDataModel(espDAO.buscarTodosEsportes()); 

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();

		primeiraVez = true;
	}

	/**
	 * Formata a data para dd/mm/yyyy HH:mm
	 * @param dataComentario
	 * @return data formatada dd/mm/yyyy HH:mm
	 * @author Graziele Vasconcelos
	 */
	public String dataFormatada(Calendar dataComentario){
		Date data = dataComentario.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		String dataFormatada = sdf.format(data);
		return dataFormatada; 
	}
	
	/**
	 * Formata a data para dd/mm/yyyy
	 * @param dataComentario
	 * @return data formatada dd/mm/yyyy
	 * @author Graziele Vasconcelos
	 */
	public String dataFormatadaDDMMYYYY(Calendar dataComentario){
		Date data = dataComentario.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		String dataFormatada = sdf.format(data);
		return dataFormatada; 
	}

	/**
	 * Realiza o desmembramento do membro para o grupo
	 * @return página grupos da aplicação
	 * @author Graziele Vasconcelos
	 */
	public String sairGrupo(){
		for (int i = 0; i < pessoa.getGruposParticipantes().size() ; i++) {
			if(pessoa.getGruposParticipantes().get(i).getCodGrupo() == grupo.getCodGrupo()){
				pessoa.getGruposParticipantes().remove(i);
			}
		}
		pDAO.update(pessoa);
		return "grupos.xhtml";
	}

	/**
	 * Realiza o envio do comentário para o grupo à qual o usuário se encontra na página
	 * @author Graziele Vasconcelos
	 */
	public void btnEnviarComentario(){
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		comentarioPostado = new ComentarioGrupo();

		comentarioGrupo.setCodGrupo(grupo);
		comentarioGrupo.setCodPessoa(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		comentarioGrupo.setDataHora(Calendar.getInstance());
		comentarioPostado = comentarioGrupoDAO.insertEntity(comentarioGrupo);
		comentarioGrupo.setComentario("");
		listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);

		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Comentario cadastrado");
		fc.addMessage("", fm);
	}

	/**
	 * Realiza a exclusão do comentário. Se for Administrador ou Moderador está apto a excluir 
	 * qualquer que seja e se for usuário apaga apenas o comentário que o mesmo fez.
	 * @author Graziele Vasconcelos
	 */
	public void excluirComentario(){
		System.out.println();
		//comentarioGrupo = comentarioGrupoDAO.searchByID(comentarioGrupoExcluido);
		//comentarioGrupoDAO.remove(comentarioGrupo);
		//listaComentarios = gruDAO.buscarComentariosPeloGrupo(codGrupo);
	}

	/**
	 * Visualização de todos os membros do grupo
	 * @return página para a visualização dos membros.
	 * @author Graziele Vasconcelos
	 */
	public String visualizarTodosMembros(){
		return "todos_membros_grupo.xhtml";
	}

	/**
	 * Visualização de todos os moderadores do grupo
	 * @return paágina para a visualização dos moderadores.
	 * @author Graziele Vasconcelos
	 */
	public String visualizarTodosModeradores(){
		return "todos_moderadores_grupo.xhtml";
	}	

	/**
	 * Administrador direcionado para a uma página de confirmação da exclusão do grupo. 
	 * @return página para a confirmação da exclusão do grupo.
	 * @author Graziele Vasconcelos
	 */
	public String btnExclusao(){
		return "exclusao_grupo.xhtml";
	}

	/**
	 * Realiza a exclusão do grupo.
	 * @return página grupos da aplicação.
	 * @author Graziele Vasconcelos
	 */
	public String excluirGrupo(){
		gruDAO.removeById(grupo.getCodGrupo());
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		fm.setSummary("Grupo excluído com sucesso.");
		fc.addMessage("", fm);
		return "grupos.xhtml";
	}

	/**
	 * Direciona para a página grupo da sessão.
	 * @return página grupo da sessão
	 * @author Graziele Vasconcelos
	 */
	public String paginaGrupo(){
		return "grupo.xhtml";
	}

	/**
	 * Direciona para a página de edição do grupo
	 * @return página para edição do grupo
	 * @author Graziele Vasconcelos
	 */
	public String edicaoGrupo(){
		edicaoGrupo = gruDAO.searchByID(grupo.getCodGrupo());
		return "edicao_grupo.xhtml";
	}
	
	/**
	 * Salva as informações editadas do grupo
	 * @return páginas do os grupos da aplicação
	 * @author Graziele Vasconcelos
	 */
	public String salvarEdicao(){
		for (Esporte esporte : getEspSelecionados()) {
			listEsporte.add(esporte);
		}
		if (getEspSelecionados().length != 0){
			edicaoGrupo.setEsportes(listEsporte);
		}
		return "grupos.xhtml";
	}


	/**
	 * Verifica se o usuário da sessão é administrador caso for é possível a visualização do botão editar grupo
	 * senão não renderizado na página
	 * @return resposta em boolean para a renderização do botão edição
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderEditGroup(){
		if(flagAdm)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se o usuário da sessão é administrador caso for é possível a visualização do botão exclusão grupo
	 * senão não renderizado na página
	 * @return resposta em boolean para a renderização do botão exclusão
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderRemoveGroup(){
		if(flagAdm)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se o usuário da sessão é membro ou moderador caso for é possível a visualização do botão sair do grupo
	 * senão não é renderizado na página
	 * @return resposta em boolean para a renderização do botão sair do grupo
	 * @author Graziele Vasconcelos
	 */	
	public boolean btnRenderSairGroup(){
		if(flagMembro || flagModerador)
			return true;
		else
			return false;
	}
	
	/**
	 * Verifica se o grupo é fechado caso seja não renderiza o conteúdo do form grupo
	 * @return resposta em boolean para a renderização do formulário Grupo
	 * @author Graziele Vasconcelos
	 */	
	public boolean btnFormGrupo(){
		if(!flagUserFechado)
			return true;
		else
			return false;
	}
	/**
	 * Verifica se o usuário é moderador ou membro ou administrador se for verdade é renderizado
	 * caixa de texto para inserir comentário
	 * @return resposta em boolean para a renderização da caixa de texto para inserção do comentário
	 * @author Graziele Vasconcelos
	 */	
	public boolean btnRenderFormComents(){
		if(flagMembro || flagModerador || flagAdm)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se o grupo é fechado caso seja não renderiza o conteúdo do form de visualização de comentários
	 * @return resposta em boolean para a renderização dos comentários do grupo
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderFormVisuComents(){
		if(!flagUserFechado) 
			return true;
		else
			return false;
	}

	/**
	 * Verifica se o usuário é administrador ou moderador para ter o privilégio da exclusão do comentário 
	 * @return respota em boolean para a renderiza do botão de exclusão comentário.
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderDeleteComent(){
		if(flagAdm || flagModerador)
			return true;
		else
			return false;
	}	
	
	/**
	 * Verifica se o usuário da sessão é administrador caso for é possível a visualização do botão 
	 * editar descrição do grupo.
	 * @return resposta em boolean para a renderização do botão editar descrição.
	 * @author Graziele Vasconcelos
	 */	
	public boolean btnRenderEditDesc(){
		if(flagAdm)
			return true;
		else
			return false;
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

	public List<Esporte> getListEsporte() {
		return listEsporte;
	}

	public void setListEsporte(List<Esporte> listEsporte) {
		this.listEsporte = listEsporte;
	}

	public boolean isFlagUserFechado() {
		return flagUserFechado;
	}

	public void setFlagUserFechado(boolean flagUserFechado) {
		this.flagUserFechado = flagUserFechado;
	}

}
