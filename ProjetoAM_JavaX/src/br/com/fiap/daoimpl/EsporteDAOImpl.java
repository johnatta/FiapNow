package br.com.fiap.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.entity.Esporte;
import br.com.fiap.pesquisa.ItemGrafico;

public class EsporteDAOImpl extends DAOImpl<Esporte, Integer> implements EsporteDAO {
	public EsporteDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Esporte> buscarTodosEsportes() {
		TypedQuery<Esporte> query = em.createQuery("from Esporte", Esporte.class);
		return query.getResultList();
	}

	@Override
	public Esporte buscarPorNome(String nome) {
		TypedQuery<Esporte> query = em.createQuery("from Esporte e where upper(e.nome) like upper(:nome)",Esporte.class);
		query.setParameter("nome", "%"+nome+"%");
		return query.getSingleResult();
	}

	@Override
	public List<ItemGrafico> buscarPopularidade() {
		TypedQuery<Esporte> query = em.createQuery("from Esporte", Esporte.class);
		List<Esporte> esportes = query.getResultList();
		
		List<ItemGrafico> graficos = new ArrayList<ItemGrafico>();
		ItemGrafico graf;
		
		for (Esporte esp : esportes){
			Query queryQtdEvento = em.createNativeQuery("select count(*) from am_evento eve where eve.cod_evento_esporte = :cod");
			queryQtdEvento.setParameter("cod", esp.getCodEsporte());
			BigDecimal qtdEvento = (BigDecimal) queryQtdEvento.getSingleResult();
			
			Query queryQtdGrupo = em.createNativeQuery("select count(*) from am_grupo_esporte ge where ge.cod_esporte = :cod");
			queryQtdGrupo.setParameter("cod", esp.getCodEsporte());
			BigDecimal qtdGrupo = (BigDecimal) queryQtdGrupo.getSingleResult();
			
			Query queryQtdPessoa = em.createNativeQuery("select count(*) from am_pessoa_esporte pe where pe.cod_esporte = :cod");
			queryQtdPessoa.setParameter("cod", esp.getCodEsporte());
			BigDecimal qtdPessoa = (BigDecimal) queryQtdPessoa.getSingleResult();
			
			int result = qtdEvento.intValue() + qtdGrupo.intValue() + qtdPessoa.intValue();
			
			graf = new ItemGrafico(esp.getNome(), result);
			graficos.add(graf);
		}
		
		return graficos;
	}
}
