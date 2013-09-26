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
		//Obtém o Contexto do Evento
		FacesContext context = event.getFacesContext();

		//Obtém o nome da Página Requisitada
		String paginaOrigem = context.getViewRoot().getViewId();

		//Obter a Pessoa da sessão
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");

		//Obter o objeto de navegação através do contexto
		NavigationHandler handler = 
				context.getApplication().getNavigationHandler();

		//Caso tente entrar em outra página e não exista sessão, vai pra index
		if( !paginaOrigem.equals("/index.xhtml") && (sessao == null || sessao.getPessoa() == null) ){
			handler.handleNavigation(context, null, "index");
		//Caso esteja na index e selecionar a internacionalização
		} else if( paginaOrigem.equals("/index.xhtml") && (sessao != null && sessao.getPessoa() == null) ) {
			handler.handleNavigation(context, null, "index");
		//Caso tente ir pra index e a pessoa da sessão esteja ativa
		} else if( paginaOrigem.equals("/index.xhtml") && (sessao != null && sessao.getPessoa() != null) ){
			handler.handleNavigation(context, null, "home");
		//Caso esteja criando um perfil e esteja em sessão
		} else if(paginaOrigem.equals("/criacao_perfil.xhtml") && (sessao != null && sessao.getPessoa() != null)){
			handler.handleNavigation(context, null, "home");
		} else {
			handler.handleNavigation(context, null, paginaOrigem);
		}

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
