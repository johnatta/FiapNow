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

		System.out.println("Página de Origem: " + paginaOrigem);

		//Obter a Pessoa da sessão
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");

		//Obter o objeto de navegação através do contexto
		NavigationHandler handler = 
				context.getApplication().getNavigationHandler();

		if( !paginaOrigem.contains("index") && sessao == null  ){
			handler.handleNavigation(context, null, "index");
		} else {
			handler.handleNavigation(context, null, paginaOrigem);
		}

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
