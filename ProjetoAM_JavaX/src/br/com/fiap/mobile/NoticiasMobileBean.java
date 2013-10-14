package br.com.fiap.mobile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.mb.LoginBean;


@ManagedBean
@SessionScoped
public class NoticiasMobileBean implements Serializable {
	
	private List<Esporte> esportes;
	private EntityManager em;
	private Pessoa pessoa;
	private Esporte esporteSelecionado;
	private Noticia noticiaSelecionada;
	
	/**
	 * Método executado no momento da instanciação do ManagedBean, iniciando todas as variáveis necessárias
	 *
	 * @author Ariel Molina 
	 */
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		
		esportes = pessoa.getEsportes();
		
		esporteSelecionado = new Esporte();
		noticiaSelecionada = new Noticia();
		
	}

	
	public List<Esporte> getEsportes() {
		return esportes;
	}
	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Esporte getEsporteSelecionado() {
		return esporteSelecionado;
	}
	public void setEsporteSelecionado(Esporte esporteSelecionado) {
		this.esporteSelecionado = esporteSelecionado;
	}
	public Noticia getNoticiaSelecionada() {
		return noticiaSelecionada;
	}
	public void setNoticiaSelecionada(Noticia noticiaSelecionada) {
		this.noticiaSelecionada = noticiaSelecionada;
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
		} else if(esporte.equals("Tênis")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15090,00.xml";
		} else if(esporte.equals("Vôlei")){
			feedRetorno = "http://globoesporte.globo.com/Esportes/Rss/0,,AS0-15080,00.xml";
		}
		
		return feedRetorno; 
	}
	
	/**
	* Remove as imagens dos feeds
	*
	* @param conteudo Conteúdo do feed
	* @return retorna o conteúdo do feed sem as imagens
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
