package br.com.fiap.daoimpl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EventoDAO;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioEventoRC;

public class EventoDAOImpl extends DAOImpl<Evento, Integer> implements EventoDAO{

	public EventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	/**
	* Busca um Evento pelo código
	*
	* @param codEvento Código do Evento
	* @return Evento encontrado
	* @author Ariel Molina 
	*/
	@Override
	public Evento buscarPeloCodigo(int codEvento) {
		TypedQuery<Evento> query = em.createQuery("from Evento where codEvento = :cod", Evento.class);
		query.setParameter("cod", codEvento);
		return query.getSingleResult();
	}

	/**
	* Busca os Comentários de um Evento
	*
	* @param codEvento Código do Evento
	* @return Comentários do Evento
	* @author Ariel Molina 
	*/
	@Override
	public List<ComentarioEventoRC> buscarComentariosPeloEvento(int codEvento) {
		String queryStr = "SELECT NEW br.com.fiap.rc.ComentarioEventoRC(p.codPessoa, p.apelido, p.imgPerfil, c.comentario, c.dtHora) " +
			      "FROM ComentarioEvento c JOIN c.codPessoa p " +
			      "WHERE c.codEvento.codEvento = :cod";
		TypedQuery<ComentarioEventoRC> query = em.createQuery(queryStr, ComentarioEventoRC.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}
	
	/**
	* Busca os Membros do Evento
	*
	* @param codEvento Código do Evento
	* @return Membros do Evento
	* @author Ariel Molina 
	*/
	@Override
	public List<Pessoa> buscarMembrosPorEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa where exists " +
				"(select codPessoa from am_pessoa_evento where codEvento = :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}
	
	/**
	* Busca os Moderadores do Evento
	*
	* @param codEvento Código do Evento
	* @return Moderadores do Evento
	* @author Ariel Molina 
	*/
	@Override
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	/**
	* Busca as Pessoas que não estão no Evento
	*
	* @param codEvento Código do Evento
	* @return Pessoas encontradas
	* @author Ariel Molina/Felipe Mauro 
	*/
	@Override
	public List<Pessoa> buscarPessoasForaEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("select PES.codPessoa, PES.apelido, PES.imgPerfil " +
				"from Pessoa PES where PES.codPessoa not in" +
				"( select codPessoa from Pessoa p inner join p.eventos e where e.codEvento= :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	/**
	* Busca os Eventos cadastrados
	*
	* @return Eventos encontrados
	* @author Ariel Molina 
	*/
	@Override
	public List<Evento> buscarEventos() {
		TypedQuery<Evento> query = em.createQuery("from Evento",Evento.class);
		List<Evento> eventos = query.getResultList();
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		return eventos;
	}

	/**
	* Busca os Eventos para a Pessoa
	*
	* @param pessoa Pessoa que será utilizada na busca
	* @return Eventos da Pessoa
	* @author Ariel Molina 
	*/
	@Override
	public List<Evento> buscarEventosDaPessoa(Pessoa pessoa) {
		TypedQuery<Evento> q = (TypedQuery<Evento>) em.createNativeQuery("select * from am_evento eve where eve.cod_evento in (select pe.cod_evento from am_pessoa_evento pe where pe.cod_pessoa = ?) and eve.data_evento >= ?", Evento.class);
		q.setParameter(1, pessoa.getCodPessoa());
		q.setParameter(2, Calendar.getInstance());
		List<Evento> eventos = q.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return eventos;
	}

	/**
	* Busca os Eventos Abertos pelo nome
	*
	* @param nome Nome completo ou parcial do Evento
	* @return Eventos abertos encontrados
	* @author Ariel Molina 
	*/
	@Override
	public List<Evento> buscarEventosAbertosPorNome(String nome) {
		TypedQuery<Evento> query = em.createQuery("from Evento eve where upper(eve.nome) like upper(:nome) and privacidade = :priv and eve.dtEvento >= :today",Evento.class);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("priv", Privacidade.Aberto);
		query.setParameter("today", Calendar.getInstance());
		List<Evento> eventos = query.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return eventos;
	}

	/**
	* Busca os Eventos da Pessoa pelo Nome
	*
	* @param pessoa Pessoa que será utilizada na busca
	* @param nome Nome completo ou parcial do Evento
	* @return Eventos encontrados na busca
	* @author Ariel Molina
	*/
	@Override
	public List<Evento> buscarMeusEventosPorNome(Pessoa pessoa, String nome) {
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("select * from am_evento eve where eve.cod_evento in (select pe.cod_evento from am_pessoa_evento pe where pe.cod_pessoa = ?) and  and eve.data_evento >= ?" +
				" and upper(eve.nome) like upper(?)", Evento.class);
		query.setParameter(1, pessoa.getCodPessoa());
		query.setParameter(2, "%"+nome+"%");
		query.setParameter(3, Calendar.getInstance());
		
		List<Evento> eventos = query.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return eventos;
	}

	/**
	* Busca os Eventos abertos
	*
	* @return Eventos abertos encontrados
	* @author Ariel Molina 
	*/
	@Override
	public List<Evento> buscarEventosAbertos() {
		TypedQuery<Evento> query = em.createQuery("from Evento eve where eve.privacidade = :priv and eve.dtEvento >= :today",Evento.class);
		query.setParameter("priv", Privacidade.Aberto);
		query.setParameter("today", Calendar.getInstance());
		List<Evento> eventos = query.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return eventos;
	}

}
