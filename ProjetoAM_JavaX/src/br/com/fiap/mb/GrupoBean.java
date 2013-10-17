package br.com.fiap.mb;

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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.bean.EditarGrupoBean;
import br.com.fiap.bean.MembroGrupoBean;
import br.com.fiap.bean.MensagemGrupoBean;
import br.com.fiap.bean.ModeradorGrupoBean;
import br.com.fiap.bo.GrupoBO;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;

@ManagedBean
@SessionScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private boolean primeiraVezRenderGrupo;
	private static int primeiraVezBuscaGrupo;
	private static int codigoGrupo;
	private boolean flagAdm = false; 
	private boolean flagModerador = false; 
	private boolean flagMembro = false; 
	private boolean flagUser = false;
	private boolean flagUserFechado = false; 
	private boolean comentario = false;
	private BigDecimal numMembros;
	private int comentarioGrupoExcluido;
	private int codGrupo;
	private List<Evento> proximosEventos;
	private List<Evento> historicoEventos;
	private List<Esporte> listEsporte;
	private Esporte[] espSelecionados;
	private ComentarioGrupo comentarioGrupo;
	private Pessoa pessoa;
	private Grupo grupo;
	private EsporteDataModel edm;
	private ComentarioGrupoDAO comentarioGrupoDAO;
	private PessoaDAO pDAO;
	private GrupoDAO gruDAO;
	private ModeradorGrupoBean moderadorGrupo;
	private MembroGrupoBean membroGrupo;
	private MensagemGrupoBean mensagemGrupo; 
	private EditarGrupoBean editGrupo;
	private GrupoBO grupoBO;

	@PostConstruct
	public void onInit(){
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		comentarioGrupoDAO = new ComentarioGrupoDAOImpl(em);
		gruDAO = new GrupoDAOImpl(em);
		pDAO = new PessoaDAOImpl(em);
		grupoBO = new GrupoBO();
		comentarioGrupo = new ComentarioGrupo();
		listEsporte = new ArrayList<Esporte>();
		edm = new EsporteDataModel(espDAO.buscarTodosEsportes()); 

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();

		if(primeiraVezBuscaGrupo > 0 ){
			codGrupo = codigoGrupo; 
			primeiraVezBuscaGrupo = 0;
			codigoGrupo = 0;
			preRenderGrupo();
		}
	}

	/**
	 * Efetua a renderização do conteúdo que deve estar pre-renderizado por meio do codGrupo que é 
	 * passado por f:event
	 * 
	 * @author Graziele Vasconcelos
	 */
	public void buscaGrupo(){
		if(grupo != null && codGrupo != grupo.getCodGrupo()){
			primeiraVezBuscaGrupo++;
			codigoGrupo = codGrupo;
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, Object> map = context.getExternalContext().getSessionMap();
			map.remove("grupoBean");
		}else{
			if(!primeiraVezRenderGrupo || comentario){
				preRenderGrupo(); 
			}else{
			}
		}
	}

	public void preRenderGrupo() {
		primeiraVezRenderGrupo = true;

		if(codGrupo == 0 ){
			CriacaoGrupoBean criacaoGrupoBean = (CriacaoGrupoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("criacaoGrupoBean");
			codGrupo = criacaoGrupoBean.getGrupo().getCodGrupo();
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, Object> map = context.getExternalContext().getSessionMap();
			map.remove("criacaoGrupoBean");
		}

		grupo = gruDAO.searchByID(codGrupo);
		numMembros = gruDAO.buscarNumeroMembros(codGrupo);
		proximosEventos = gruDAO.buscarProximosEventos(codGrupo);
		historicoEventos = gruDAO.buscarHistoricoEvento(codGrupo);

		proximosEventosNull();
		historicoEventoNull();
		membrosGrupoNull();
		moderadoresGrupoNull();

		/**
		 * Realizando comparaçõs para ter o conhecimento se o usuário é:
		 * Administrador, Moderador, Membro do Grupo e não membro.
		 * Também verifica se o grupo é fechado(apenas membros pode ver o conteúdo).
		 * 
		 * @author Graziele Vasconcelos
		 */

		if(pessoa.getCodPessoa() == grupo.getAdm().getCodPessoa()){
			flagAdm = true;
		}else{

			for(Grupo g : pessoa.getGruposParticipantes()){				
				if(g.getCodGrupo() == codGrupo){
					flagMembro = true;

					for(Pessoa moderadorGrupo : grupo.getModeradores()){
						if(pessoa.getCodPessoa() == moderadorGrupo.getCodPessoa()){
							flagModerador = true;
							break;
						}	
					}
				}else{
					if(grupo.getPrivacidade() == Privacidade.Fechado){
						flagUserFechado = true;
					}else{
						flagUser = true;
					}
				}
			}
		}
	}

	/**
	 * Caso não haja moderadores, cria uma instância de Pessoa e popula com mensagem pertinente
	 * no caso "sem registro"
	 * @author Graziele Vasconcelos
	 */
	public void moderadoresGrupoNull() {
		grupoBO.verificaConteudoModerador(grupo);
	}

	/**
	 * Caso não haja membros, cria uma instância de Pessoa e popula com mensagem pertinente
	 * no caso "sem registro"
	 * @author Graziele Vasconcelos
	 */
	public void membrosGrupoNull() {
		grupoBO.verificaConteudoMembro(grupo);
	}

	/**
	 * Caso não haja próximos eventos, cria uma instância do mesmo e popula com mensagem pertinente
	 * no caso "sem registro"
	 * @author Graziele Vasconcelos
	 */
	public void proximosEventosNull() {
		grupoBO.verificaProximosEventos(proximosEventos);
	}
	/**
	 * Caso não haja histórico de eventos, cria uma instância do mesmo e popula com mensagem pertinente
	 * no caso "sem registro"
	 * @author Graziele Vasconcelos
	 */
	public void historicoEventoNull() {
		grupoBO.verificaHistoricoEventos(historicoEventos);
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

	public String participarGrupo(){
		return grupoBO.participarDoGrupo(grupo, pessoa);
	}
	/**
	 * Realiza o envio do comentário para o grupo à qual o usuário se encontra na página
	 * @author Graziele Vasconcelos
	 */
	public void btnEnviarComentario(){
		this.grupo = grupoBO.enviarComentarioGrupo(grupo, pessoa, comentarioGrupo);
	}

	/**
	 * Realiza a exclusão do comentário. Se for Administrador ou Moderador está apto a excluir 
	 * qualquer que seja e se for usuário apaga apenas o comentário que o mesmo fez.
	 * @author Graziele Vasconcelos
	 */
	public void excluirComentario(int codComentario){
		try {
			comentarioGrupo = comentarioGrupoDAO.searchByID(codComentario);
			grupo.getComentarios().remove(comentarioGrupo);
			comentarioGrupoDAO.remove(comentarioGrupo);
			comentarioGrupo = new ComentarioGrupo();
			gruDAO.update(grupo);
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Comentário excluido com sucesso.");
			fc.addMessage("", fm);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("O comentário não foi excluido, por favor tente mais tarde.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
		}
	}

	/**
	 * Visualização de todos os membros do grupo
	 * @return página para a visualização dos membros. VISAO GERENCIADOR
	 * @author Graziele Vasconcelos
	 */
	public String visualizarTodosMembros(){
		membroGrupo = new MembroGrupoBean(grupo);
		return "todos_membros_grupo.xhtml";
	}

	/**
	 * Visualização de todos os moderadores do grupo
	 * @return paágina para a visualização dos moderadores.  VISAO GERENCIADOR
	 * @author Graziele Vasconcelos
	 */
	public String visualizarTodosModeradores(){
		return "todos_moderadores_grupo.xhtml";
	}	
	/**
	 * Visualização de todos os membros do grupo
	 * @return página para a visualização dos membros.
	 * @author Graziele Vasconcelos
	 */
	public String TodosMembros(){
		return "grupo_todos_membros.xhtml";
	}

	/**
	 * Visualização de todos os moderadores do grupo
	 * @return paágina para a visualização dos moderadores.
	 * @author Graziele Vasconcelos
	 */
	public String TodosModeradores(){
		return "grupo_todos_moderadores.xhtml";
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
		return grupoBO.excluirGrupo(grupo);
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
		editGrupo = new EditarGrupoBean(grupo);
		return "edicao_grupo.xhtml";
	}

	/**
	 * Direciona para a página de mensagem grupo
	 * @return página para mensagem grupo
	 * @author Graziele Vasconcelos
	 */	
	public String msgGrupo(){
		mensagemGrupo = new MensagemGrupoBean(grupo);
		return "mensagem_grupo.xhtml";
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
	 * Verifica se o usuário é administrador ou moderador para ter o privilégio de acessar a página de
	 * mensagem grupo e enviar 
	 * @return respota em boolean para renderizar botão para acesso da página mensagem grupo
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderPgMsg(){
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

	/**
	 * Verifica se o usuário da sessão é administrador caso for é possível a visualização do botão
	 * gerenciar membros
	 * @return resposta em boolean para a renderização do botão gerenciar membros.
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderGerenciarMembers(){
		if(flagAdm)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se o grupo é aberto
	 * @return resposta em boolean para a renderização do botão visualizar todos os membros.
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderAllMembros(){
		if(!flagUserFechado)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se o usuário da sessão é administrador caso for é possível a visualização do botão
	 * gerenciar moderadores
	 * @return resposta em boolean para a renderização do botão gerenciar moderadores.
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderGerenciarModeradores(){
		if(flagAdm)
			return true;
		else
			return false;
	}

	/**
	 * Verifica se o grupo é aberto
	 * @return resposta em boolean para a renderização do botão visualizar todos os moderadores.
	 * @author Graziele Vasconcelos
	 */
	public boolean btnRenderAllModeradores(){
		if(!flagUserFechado)
			return true;
		else
			return false;
	}

	public String gerenciarModerador(){
		moderadorGrupo = new ModeradorGrupoBean(grupo);
		return "todos_moderadores_grupo.xhtml";
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

	public ComentarioGrupo getComentarioGrupo() {
		return comentarioGrupo;
	}

	public void setComentarioGrupo(ComentarioGrupo comentarioGrupo) {
		this.comentarioGrupo = comentarioGrupo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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

	public boolean isComentario() {
		return comentario;
	}

	public void setComentario(boolean comentario) {
		this.comentario = comentario;
	}

	public ModeradorGrupoBean getModeradorGrupo() {
		return moderadorGrupo;
	}

	public MembroGrupoBean getMembroGrupo() {
		return membroGrupo;
	}

	public void setMembroGrupo(MembroGrupoBean membroGrupo) {
		this.membroGrupo = membroGrupo;
	}

	public MensagemGrupoBean getMensagemGrupo() {
		return mensagemGrupo;
	}

	public void setMensagemGrupo(MensagemGrupoBean mensagemGrupo) {
		this.mensagemGrupo = mensagemGrupo;
	}

	public void setModeradorGrupo(ModeradorGrupoBean moderadorGrupo) {
		this.moderadorGrupo = moderadorGrupo;
	}

	public EditarGrupoBean getEditGrupo() {
		return editGrupo;
	}

	public void setEditGrupo(EditarGrupoBean editGrupo) {
		this.editGrupo = editGrupo;
	}

}
