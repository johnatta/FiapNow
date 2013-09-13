package br.com.fiap.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class FeedBean implements Serializable {

	private List<String> esportes;
	private EntityManager em;
	private Pessoa pessoa;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		//Obter a Pessoa da sess�o
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		
		esportes = new ArrayList<String>();
		
		for(Esporte esp : pessoa.getEsportes()){
			esportes.add(esp.getNome());
		}
		
	}
	
	public String buscaFeed(String esporte){
		
		String feedRetorno = "";
		
		if(esporte.equals("Basquete")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15060,00.xml";
		} else if(esporte.equals("Corrida")){
			feedRetorno = "http://br.esporteinterativo.yahoo.com/atletismo/?format=rss";
		} else if(esporte.equals("Casual")){
			feedRetorno = "http://esportes.r7.com/mais-esportes/feed.xml";
		} else if(esporte.equals("Esportes Radicais")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15040,00.xml";
		} else if(esporte.equals("Futebol")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-9825,00.xml";
		} else if(esporte.equals("Futebol Americano")){
			feedRetorno = "http://esportes.terra.com.br/rss/Controller?channelid=7e57230cf9699310VgnVCM5000009ccceb0aRCRD&ctName=atomo-noticia&lg=pt-br";
		} else if(esporte.equals("T�nis")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15090,00.xml";
		} else if(esporte.equals("V�lei")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15080,00.xml";
		}
		
		return feedRetorno; 
	}

	public List<String> getEsportes() {
		return esportes;
	}
	public void setEsportes(List<String> esportes) {
		this.esportes = esportes;
	}

}
