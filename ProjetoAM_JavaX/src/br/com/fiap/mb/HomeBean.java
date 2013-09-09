package br.com.fiap.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import org.primefaces.model.chart.PieChartModel;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.pesquisa.ItemGrafico;

@ManagedBean
@RequestScoped
public class HomeBean implements Serializable {
	
	private PieChartModel estatisticaEsportes;
	private EntityManager em;
	private EsporteDAO esporteDAO;
	
	public PieChartModel getestatisticaEsportes() {
		return estatisticaEsportes;
	}
	
	@PostConstruct
	public void inicializarEstatisticas() {
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		estatisticaEsportes = new PieChartModel();
		esporteDAO = new EsporteDAOImpl(em);
		
		List<ItemGrafico> esportes = esporteDAO.buscarPopularidade();
		
		for(ItemGrafico ig : esportes) {
			estatisticaEsportes.set(ig.getNome(), ig.getQtd());
		}
	}

}