package com.fiap.nac20.Seguranca;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private String usuario;
	private String senha;
	
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
	public String logout(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		map.remove("formularioBean");
		
		return "login";
	}
	public String logar(){
		
		String retorno;
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usu�rio e/ou Senha Inv�lidos!");
		
		if(usuario.equals("Jefferson") && senha.equals("Senha1") || 
				usuario.equals("Admin") && senha.equals("senhaAdm") ||
				usuario.equals("Admin1") && senha.equals("fiapA")) {
			
			retorno = "consultarProduto";
		} else {
			fc.addMessage("", mensagem);
			retorno = "";
		}
		return retorno;
	}
	
}