package br.com.fiap.mb;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.daoimpl.UsuarioDAOImpl;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private EntityManager em;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	private Pessoa pessoa;
	private Locale locale;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
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
			returnPage = "grupos";
		} else {
			fc.addMessage("", fm);
		}
		
		return returnPage;
	}
	
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
			returnPage = "grupos";
		} else {
			fc.addMessage("", fm);
		}
		
		return returnPage;
		
	}
	
	public String logout(){

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		map.remove("loginBean");
		
		return "index";
		
	}
	
	public void changeEnglish() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view = context.getViewRoot();
		locale = Locale.ENGLISH;
		view.setLocale(locale);
	}

	public void changePortuguese() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIViewRoot view = context.getViewRoot();
		locale = new Locale("pt", "BR");
		view.setLocale(locale);
	}
	
}
