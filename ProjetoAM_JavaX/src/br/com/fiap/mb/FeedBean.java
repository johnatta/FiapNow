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
	private int styleCount;
	private String styleSize;
	
	/**
	 * M�todo executado no momento da instancia��o do ManagedBean, iniciando todas as vari�veis necess�rias
	 *
	 * @author Ariel Molina 
	 */
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
		
		styleCount = 0;
		switch (pessoa.getEsportes().size()) {
		case 1:
			styleSize = " fat";
			break;
		case 2:
			styleSize = " medium";
			break;
		default :
			styleSize = " thin";
			break;
		}
		
	}
	
	public List<String> getEsportes() {
		return esportes;
	}
	public void setEsportes(List<String> esportes) {
		this.esportes = esportes;
	}
	
	/**
	* Retornar o site de feed de acordo com o esporte.
	*
	* @param esporte para o feed
	* @author Ariel Molina 
	*/
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
			feedRetorno = "http://nfldeboteco.com.br/feed/";
		} else if(esporte.equals("T�nis")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15090,00.xml";
		} else if(esporte.equals("V�lei")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15080,00.xml";
		}
		
		return feedRetorno; 
	}

	/**
	* Retorna o nome da class para o componente
	*
	* @return retorna o nome da class
	* @author Ariel Molina 
	*/
	public String stylize(){
		
		String estilo;
		
		if (styleCount % 2 == 0){
			estilo = "blueFeed" + styleSize;
		} else {
			estilo = "orangeFeed" + styleSize; 
		}
		
		styleCount++;
		
		return estilo;
	}
	
	/**
	* Remove as imagens dos feeds
	*
	* @param conteudo Conte�do do feed
	* @return retorna o conte�do do feed sem as imagens
	* @author Ariel Molina 
	*/
	public String removeImagens(String conteudo){
		if (conteudo.contains("<img")){
			int beginImg = conteudo.indexOf("<img");
			int endImg = conteudo.indexOf("/>") + 2;
			conteudo = conteudo.substring(0, beginImg) + conteudo.substring(endImg, conteudo.length());
		}
		return conteudo;
	}

}
