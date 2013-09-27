package br.com.fiap.mb;

import java.io.Serializable;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

@ManagedBean
@ViewScoped
public class EnviaEmailBean implements Serializable {
	
	private String corpo;
	
	@PostConstruct
	public void onInit(){
		corpo = "Olá," +
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
	}
	
	public String getCorpo() {
		return corpo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	
	public void enviaEmail(){
		
		HtmlEmail email = new HtmlEmail(); 
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator("javaxsolutions@gmail.com", "solutions147"));
		email.setSSLOnConnect(true);
		
		
		try {
			email.addTo("arl_sk8@hotmail.com", "Ariel Molina");
			email.setFrom("javaxsolutions@gmail.com","Encontro Esportivo");
			email.setSubject("Venha pro Encontro Esportivo!");  

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
			
			email.setHtmlMsg(header + corpo + footer);

			// configure uma mensagem alternativa caso o servidor não suporte HTML 
			//email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");   
			//envia o e-mail 
			email.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
