package br.com.fiap.phaseListener;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.entity.Pessoa;
import br.com.fiap.mb.LoginBean;

public class LoginPhaseListener implements Serializable, PhaseListener {
	
	@Override
	public void afterPhase(PhaseEvent event) {

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		//Obt�m o Contexto do Evento
		FacesContext context = event.getFacesContext();

		//Obt�m o nome da P�gina Requisitada
		String paginaOrigem = context.getViewRoot().getViewId();

		//Obter a Pessoa da sess�o
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");

		//Obter o objeto de navega��o atrav�s do contexto
		NavigationHandler handler = 
				context.getApplication().getNavigationHandler();

		//Recebendo o Browser do usu�rio
		String browser = (String) context.getExternalContext().getRequestHeaderMap().get("User-Agent");
		
		if (!browser.contains("OPWV-SDK")) {
			
			//Caso tente entrar em outra p�gina e n�o exista sess�o, vai pra index
			if( !paginaOrigem.equals("/index.xhtml") && (sessao == null || sessao.getPessoa() == null) ){
				handler.handleNavigation(context, null, "index");
				//Caso esteja na index e selecionar a internacionaliza��o
			} else if( paginaOrigem.equals("/index.xhtml") && (sessao != null && sessao.getPessoa() == null) ) {
				handler.handleNavigation(context, null, "index");
				//Caso tente ir pra index e a pessoa da sess�o esteja ativa
			} else if( paginaOrigem.equals("/index.xhtml") && (sessao != null && sessao.getPessoa() != null) ){
				handler.handleNavigation(context, null, "home");
				//Caso esteja criando um perfil e esteja em sess�o
			} else if(paginaOrigem.equals("/criacao_perfil.xhtml") && (sessao != null && sessao.getPessoa() != null)){
				handler.handleNavigation(context, null, "home");
			} else {
				handler.handleNavigation(context, null, paginaOrigem);
			}
			
		} else {
			
			//Caso tente entrar em outra p�gina e n�o exista sess�o, vai pra index
			if( !paginaOrigem.equals("/indexMobile.xhtml") && (sessao == null || sessao.getPessoa() == null) ){
				handler.handleNavigation(context, null, "indexMobile");
			}
			
		}

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
