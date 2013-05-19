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
	
	public void validaDado(FacesContext context, UIComponent component, Object value) {
		String valor = value.toString();
		if (!valor.contains("a")) { //* qualquer validação lógica
			((UIInput)component).setValid(false);
			FacesMessage message = new FacesMessage("Erro de Consistência Lógica");
			context.addMessage(component.getClientId(context), message);
		}
	}
	
	public String logout(){
		
		String retorno;
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		map.remove("formularioBean");
		
		retorno = "login";
		
		return retorno;
	}
	public String logar(){
		
		String retorno;
		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage("Usuário e/ou Senha Inválidos!");
		
		if(usuario.equals("Jefferson") && senha.equals("Senha1") || 
				usuario.equals("Admin") && senha.equals("senhaAdm") ||
				usuario.equals("Admin1") && senha.equals("fiapA")) {
			
			retorno = "consultaProdutos";
		} else {
			fc.addMessage("", mensagem);
			retorno = "";
		}
		return retorno;
	}
	
}