package br.com.fiap.mb;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.daoimpl.UsuarioDAOImpl;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private EntityManager em;
	private String imgEventos;
	private String imgGrupos;
	private String imgMensagens;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	private Pessoa pessoa;
	private int eventsInvites;
	private int eventsRequests;
	private int groupsInvites;
	private int groupsRequests;
	private int unreadMessages;
	private Locale locale;
	private ConviteEventoDAO convEveDAO;
	private PedidoEventoDAO pedEveDAO;
	private ConviteGrupoDAO convGruDAO;
	private PedidoGrupoDAO pedGruDAO;
	private MensagemEventoDAO msgEveDAO;
	private MensagemGrupoDAO msgGruDAO;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		convEveDAO = new ConviteEventoDAOImpl(em);
		pedEveDAO = new PedidoEventoDAOImpl(em);
		convGruDAO = new ConviteGrupoDAOImpl(em);
		pedGruDAO = new PedidoGrupoDAOImpl(em);
		msgEveDAO = new MensagemEventoDAOImpl(em);
		msgGruDAO = new MensagemGrupoDAOImpl(em);
	}
	
	/**
	 * Procura pelas notificações do usuário
	 *
	 * @author Ariel Molina 
	 */
	public void getNews(){
		eventsInvites = convEveDAO.buscarConviteEventoPorPessoa(pessoa).size();
		eventsRequests = pedEveDAO.buscarPedidosDeEventoPraPessoa(pessoa).size();
		groupsInvites = convGruDAO.buscarConviteGrupoPorPessoa(pessoa).size();
		groupsRequests = pedGruDAO.buscarPedidoGrupoPraPessoa(pessoa).size();
		unreadMessages = msgEveDAO.buscarMensagensNaoLidasDaPessoa(pessoa).size() +
							msgGruDAO.buscarMensagensNaoLidasDaPessoa(pessoa).size();
		
		imgEventos = (eventsInvites > 0 || eventsRequests > 0 ? "eventosBranco.png" : "eventos.png");
		
		imgGrupos = (groupsInvites > 0 || groupsRequests > 0 ? "gruposBranco.png" : "grupos.png");
		
		imgMensagens = (unreadMessages > 0 ? "mensagensBranco.png" : "mensagens.png");
		
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getImgEventos() {
		return imgEventos;
	}
	public void setImgEventos(String imgEventos) {
		this.imgEventos = imgEventos;
	}
	public String getImgGrupos() {
		return imgGrupos;
	}
	public void setImgGrupos(String imgGrupos) {
		this.imgGrupos = imgGrupos;
	}
	public String getImgMensagens() {
		return imgMensagens;
	}
	public void setImgMensagens(String imgMensagens) {
		this.imgMensagens = imgMensagens;
	}
	public int getEventsInvites() {
		return eventsInvites;
	}
	public void setEventsInvites(int eventsInvites) {
		this.eventsInvites = eventsInvites;
	}
	public int getEventsRequests() {
		return eventsRequests;
	}
	public void setEventsRequests(int eventsRequests) {
		this.eventsRequests = eventsRequests;
	}
	public int getGroupsInvites() {
		return groupsInvites;
	}
	public void setGroupsInvites(int groupsInvites) {
		this.groupsInvites = groupsInvites;
	}
	public int getGroupsRequests() {
		return groupsRequests;
	}
	public void setGroupsRequests(int groupsRequests) {
		this.groupsRequests = groupsRequests;
	}
	public int getUnreadMessages() {
		return unreadMessages;
	}
	public void setUnreadMessages(int unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	/**
	* Realiza a validação do campo que está chamando, verificando se possui o formato de email.
	*
	* @param context Contexto da página
	* @param component Componente que chama o método
	* @param value Valor do componente
	* @author Ariel Molina 
	*/
	public void validaEmail(FacesContext context, UIComponent component, Object value) {
		String valor = value.toString();
		if (!valor.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			((UIInput)component).setValid(false);
			
			String mensagem =
					UtilsNLS.getMessageResourceString("nls.mensagem", "invalid_email",
					null, context.getViewRoot().getLocale());
			
			FacesMessage fm = new FacesMessage(mensagem);
			
			context.addMessage(component.getClientId(context), fm);
		}
	}
	
	/**
	* Realizar o login no sistema
	*
	* @return retorna vazio caso login seja inválido, e para a home caso seja válido
	* @author Ariel Molina 
	*/
	public String login(){
		
		String returnPage = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "invalid_login",
				null, fc.getViewRoot().getLocale());
		
		FacesMessage fm = new FacesMessage(mensagem);
		
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario user = usuarioDAO.buscarEmailESenha(usuario, senha);
		
		if(user != null){
			PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
			pessoa = pessoaDAO.buscarPorUsuario(user);
			returnPage = "home?faces-redirect=true";
		} else {
			fc.addMessage("", fm);
		}
		
		return returnPage;
	}
	
	/**
	* Verifica se o email já está cadastrado e retorna para a página correspondente
	*
	* @return retorna vazio caso o email já esteja cadastrado, e para o cadastro de perfil caso não esteja
	* @author Ariel Molina 
	*/
	public String cadastrar(){
		
		String returnPage = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "used_email",
				null, fc.getViewRoot().getLocale());
		
		FacesMessage fm = new FacesMessage(mensagem);
		
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario user = usuarioDAO.buscarPorEmail(email);
		
		if(user == null){
			returnPage = "home";
		} else {
			fc.addMessage("", fm);
		}
		
		return returnPage;
		
	}
	
	/**
	* Realiza o logout do site e finaliza a sessão
	* 
	* @return retorna para a página index
	* @author Ariel Molina 
	*/
	public String logout(){

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		map.remove("loginBean");
		
		return "index.xhtml?faces-redirect=true";
		
	}
	
	/**
	* Muda a linguagem da sessão para inglês
	*
	* @author Ariel Molina 
	*/
	public void changeEnglish() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view = context.getViewRoot();
		locale = Locale.ENGLISH;
		view.setLocale(locale);
	}

	/**
	* Muda a linguagem da sessão para português brasileiro
	*
	* @author Ariel Molina 
	*/
	public void changePortuguese() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view = context.getViewRoot();
		locale = new Locale("pt", "BR");
		view.setLocale(locale);
	}

	
	/**
	* Realizar o login no sistema mobile
	*
	* @return retorna vazio caso login seja inválido, e para a home caso seja válido
	* @author Ariel Molina 
	*/
	public String loginMobile(){
		
		String returnPage = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "invalid_login",
				null, fc.getViewRoot().getLocale());
		
		FacesMessage fm = new FacesMessage(mensagem);
		
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario user = usuarioDAO.buscarEmailESenha(usuario, senha);
		
		if(user != null){
			PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
			pessoa = pessoaDAO.buscarPorUsuario(user);
			returnPage = "homeMobile?faces-redirect=true";
		} else {
			fc.addMessage("", fm);
		}
		
		return returnPage;
	}
	
}
