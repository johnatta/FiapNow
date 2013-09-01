package br.com.fiap.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.daoimpl.UsuarioDAOImpl;
import br.com.fiap.entity.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private EntityManager em;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
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
	
}
