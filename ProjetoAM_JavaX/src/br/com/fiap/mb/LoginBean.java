package br.com.fiap.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.UsuarioBO;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private String usuario;
	private String senha;
	private String email;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String login(){
		
		String returnPage = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usuário e/ou Senha Inválidos!");
		
		UsuarioBO usuarioBO = new UsuarioBO();
		
		if(usuarioBO.validaLogin(usuario, senha)){
			returnPage = "home";
		} else {
			fc.addMessage("", mensagem);
		}
		
		return returnPage;
	}
	
	public String cadastrar(){
		return "cadastroUsuario";
	}
	
}
