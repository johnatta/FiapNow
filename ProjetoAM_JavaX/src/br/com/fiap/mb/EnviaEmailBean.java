package br.com.fiap.mb;

import java.io.Serializable;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import br.com.fiap.I18N.UtilsNLS;

@ManagedBean
@RequestScoped
public class EnviaEmailBean implements Serializable {
	
	private String para;
	private String titulo;
	private String corpo;
	private boolean enviado;
	
	@PostConstruct
	public void onInit(){
		
		corpo = "Olá," +
				"\n\n" +
				"Encontrei um jeito de não perdermos mais os nossos encontros para " +
				"praticarmos esportes! Faça sua incrição no site Encontro Esportivo agora " +
				"mesmo, basta clicar no link abaixo:" +
				"\n\n" +
				"Encontro Esportivo - O Maior site para encontros esportivos!" +
				"\n\n" +
				"Abraços," +
				"\n\n" +
				"Encontro Esportivo";
		
		titulo = "Venha pro Encontro Esportivo!";
		
		enviado = false;
		
	}
	
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public boolean isEnviado() {
		return enviado;
	}
	public void setEnviado(boolean enviado) {
		this.enviado = enviado;
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
	* Envia o email de convite.
	*
	* @exception Exception caso haja algum erro no envio do email
	* @author Ariel Molina 
	*/
	public void enviaEmail(){
		
		HtmlEmail email = new HtmlEmail(); 
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("javaxsolutions@gmail.com", "solutions147"));
		email.setSSLOnConnect(true);
		
		try {
			email.addTo(para);
			email.setFrom("javaxsolutions@gmail.com","Encontro Esportivo");
			email.setSubject(titulo);  

			// adiciona uma imagem ao corpo da mensagem e retorna seu id 
			URL url = new URL("http://localhost:8080/ProjetoAM_JavaX/faces/resources/images/logoEE.png"); 
			String cid = email.embed(url, "Encontro Esportivo logo");   
			// configura a mensagem para o formato HTML 
			
			String header = "<html> " +
					"<div style='width:100%; background-color:#ff9f0f;'> " +
					"<img src=\"cid:"+cid+"\" width='227' height='25'> " +
					"</div>" +
					"<br />" +
					"<div style='font: 18px  verdana, arial, helvetica; color: #233e42; '>";
			
			String footer = "</div>	</html>";
			
			String corpoHtml = "Olá," +
							"<br /><br />" +
							"Encontrei um jeito de não perdermos mais os nossos encontros para " +
							"praticarmos esportes! Faça sua incrição no site Encontro Esportivo agora " +
							"mesmo, basta clicar no link abaixo:" +
							"<br />" +
							"<br />" +
							"<a href='http://localhost:8080/ProjetoAM_JavaX/faces/criacao_perfil.xhtml' style='color:blue;'>" +
							"Encontro Esportivo - O Maior site para encontros esportivos!" +
							"</a>" +
							"<br /><br />" +
							"Abraços," +
							"<br /><br />" +
							"Encontro Esportivo";
			
			email.setHtmlMsg(header + corpoHtml + footer);

			//envia o e-mail 
			email.send();
			
			enviado = true;
			
		} catch (Exception e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			
			String mensagem =
					UtilsNLS.getMessageResourceString("nls.mensagem", "email_fail",
					null, fc.getViewRoot().getLocale());
			
			FacesMessage fm = new FacesMessage(mensagem);
			fc.addMessage("", fm);
			
			e.printStackTrace();
		}
		
	}
	
}
