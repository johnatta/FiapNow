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
	
	/**
	 * M�todo executado antes de iniciar a renderiza��o da p�gina
	 *
	 * @param event Fase em que est� executando o m�todo
	 * @author Ariel Molina 
	 */
	@Override
	public void afterPhase(PhaseEvent event) {

	}

	/**
	 * M�todo executado antes de iniciar a renderiza��o da p�gina, redirecionando para outras p�ginas
	 * caso estejam inv�lidas
	 *
	 * @param event Fase em que est� executando o m�todo
	 * @author Ariel Molina 
	 */
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
		
		if (browser.contains("avantgo") || browser.contains("bolt") || browser.contains("docomo") || browser.contains("up.browser") || browser.contains("vodafone") 
				|| browser.contains("j-phone") || browser.contains("ddipocket") || browser.contains("pdxgw") || browser.contains("astel") || browser.contains("android")
				|| browser.contains("eudoraweb") || browser.contains("ppc") || browser.contains("minimo") || browser.contains("plink") || browser.contains("plucker") 
				|| browser.contains("netfront") || browser.contains("wm5 pie") || browser.contains("xiino") || browser.contains("tablet") || browser.contains("ipad")
				|| browser.contains("iphone") || browser.contains("itunes") || browser.contains("vendorid") || browser.contains("wap") || browser.contains("bb10") 
				|| browser.contains("blackberry") || browser.contains("opera mini") || browser.contains("cricket") || browser.contains("iemobile") 
				|| browser.contains("wosbrowser") || browser.contains("windows phone") || browser.contains("htc") || browser.contains("xv6850") 
				|| browser.contains("kindle") || browser.contains("teleca") || browser.contains("mib/") || browser.contains("portalmmm") || browser.contains("nintendo") 
				|| browser.contains("mobile") || browser.contains("nokia") || browser.contains("symbian") || browser.contains("opera mobi") || browser.contains("fennec") 
				|| browser.contains("tear") || browser.contains("midori") || browser.contains("prism") || browser.contains("smartphone") || browser.contains("webos") 
				|| browser.contains("palm") || browser.contains("blazer") || browser.contains("palmsource") || browser.contains("mobileexplorer") 
				|| browser.contains("regking") || browser.contains("epoc") || browser.contains("samsung-gt-s3653w") || browser.contains("samsung-gt-s5620")
				|| browser.contains("samsung-s8003") || browser.contains("bada") || browser.contains("samsung-sphm800") || browser.contains("sec-sghe600")
				|| browser.contains("sec-sgh600") || browser.contains("sec-sghd410") || browser.contains("j2me") || browser.contains("reqwirelessweb/3.2 s55")
				|| browser.contains("sonyericssonk800i") || browser.contains("sonyericssonk608i") || browser.contains("semc-browser") 
				|| browser.contains("sonyericssont200") || browser.contains("sonyericssonp800") || browser.contains("sonyericssonp900") 
				|| browser.contains("sonyericssont610")	|| browser.contains("playstation") || browser.contains("ucweb") || browser.contains("wp7")
				|| browser.contains("OPWV-SDK") || browser.contains("Android") ){
			
			//Caso tente entrar em outra p�gina e n�o exista sess�o, vai pra index
			if( !paginaOrigem.equals("/indexMobile.xhtml") && (sessao == null || sessao.getPessoa() == null) ){
				handler.handleNavigation(context, null, "indexMobile");
			}
			
		} else {
			
			
			//Caso tente entrar em outra p�gina e n�o exista sess�o, vai pra index
			if( !paginaOrigem.equals("/index.xhtml") && !paginaOrigem.equals("/criacao_perfil.xhtml") && (sessao == null || sessao.getPessoa() == null) ){
				handler.handleNavigation(context, null, "index");
				//Caso selecione a criacao_perfil e esteja logado ou n�o tenha vindo da index
			} else if( paginaOrigem.equals("/criacao_perfil.xhtml") && (sessao == null || sessao.getPessoa() != null) ){
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
			
			
		}

	}

	/**
	 * M�todo para receber o Id da Fase que est� em execu��o
	 *
	 * @return fase da pagina��o
	 * @author Ariel Molina 
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
