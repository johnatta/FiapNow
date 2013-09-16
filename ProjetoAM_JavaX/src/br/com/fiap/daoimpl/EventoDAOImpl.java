package br.com.fiap.daoimpl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

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

	@Override
	public Evento buscarPeloCodigo(int codEvento) {
		TypedQuery<Evento> query = em.createQuery("from Evento where codEvento = :cod", Evento.class);
		query.setParameter("cod", codEvento);
		return query.getSingleResult();
	}

	@Override
	public List<ComentarioEventoRC> buscarComentariosPeloEvento(int codEvento) {
		String queryStr = "SELECT NEW br.com.fiap.rc.ComentarioEventoRC(p.codPessoa, p.apelido, p.imgPerfil, c.comentario, c.dtHora) " +
			      "FROM ComentarioEvento c JOIN c.codPessoa p " +
			      "WHERE c.codEvento.codEvento = :cod";
		TypedQuery<ComentarioEventoRC> query = em.createQuery(queryStr, ComentarioEventoRC.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}
	
	@Override
	public List<Pessoa> buscarMembrosPorEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa where exists " +
				"(select codPessoa from am_pessoa_evento where codEvento = :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}
	
	@Override
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	@Override
	public List<Esporte> buscarCategoria() {
		TypedQuery<Esporte> query = em.createQuery("from AM_ESPORTE", Esporte.class);
		return query.getResultList();
	}

	@Override
	public Evento adcionarPessoaEvento(int codEvento) {
		TypedQuery<Evento> query = em.createQuery("select PES.cod_pessoa, PES.apelido, PES.imagem_perfil " +
				"from AM_PESSOA PES where pes.cod_pessoa not in" +
				"( select cod_pessoa from am_pessoa_evento where cod_evento= :cod);",Evento.class);
		query.setParameter("cod", codEvento);
		return query.getSingleResult();
	}

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

	@Override
	public Pessoa buscarPessoaEvento(int codPessoa) {
		TypedQuery<Pessoa> query = em.createQuery("select eve.cod_evento, eve.nome, eve.imagem_evento," +
				"(select count(*) from am_pessoa_evento pe where pe.cod_evento = eve.cod_evento) " +
				"as numMembro from am_evento eve where eve.cod_evento in " +
				"(select cod_evento from am_pessoa_evento where cod_pessoa = :cod ) order by numMembro;",Pessoa.class);
		query.setParameter("cod", codPessoa);
		return query.getSingleResult();
	}
	
	 @Override
	 public List<Evento> buscarProximosEventos(int codGrupo) {
	  @SuppressWarnings("unchecked")
	  Calendar dtAtual = Calendar.getInstance();
	  TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("SELECT * FROM AM_EVENTO WHERE cod_evento IN (SELECT cod_evento FROM AM_EVENTO_GRUPO  WHERE cod_evento = ? ) AND data_evento > = ? ", Evento.class);
	  query.setParameter(1, codGrupo);
	  query.setParameter(2, dtAtual);
	  return query.getResultList();
	 }
	

	@Override //select nao ok
	public List<Evento> historicoDeEventos(int codGrupo) {
		  @SuppressWarnings("unchecked")
		  Calendar dtAtual = Calendar.getInstance();
		  TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("SELECT * FROM AM_EVENTO WHERE cod_evento IN (SELECT cod_evento FROM AM_EVENTO_GRUPO  WHERE cod_evento = ? ) AND data_evento < = ? ", Evento.class);
		  query.setParameter(1, codGrupo);
		  query.setParameter(2, dtAtual);
		  return query.getResultList();
	
}

	@Override
	public List<Evento> buscarEventosDaPessoa(Pessoa pessoa) {
		TypedQuery<Evento> q = (TypedQuery<Evento>) em.createNativeQuery("select * from am_evento eve where eve.cod_evento in (select pe.cod_evento from am_pessoa_evento pe where pe.cod_pessoa = ?)", Evento.class);
		q.setParameter(1, pessoa.getCodPessoa());
		List<Evento> eventos = q.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		return eventos;
	}

	@Override
	public List<Evento> buscarEventosAbertosPorNome(String nome) {
		TypedQuery<Evento> query = em.createQuery("from Evento eve where upper(eve.nome) like upper(:nome) and privacidade = :priv",Evento.class);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("priv", Privacidade.Aberto);
		List<Evento> eventos = query.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		return eventos;
	}

	@Override
	public List<Evento> buscarMeusEventosPorNome(Pessoa pessoa, String nome) {
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("select * from am_evento eve where eve.cod_evento in (select pe.cod_evento from am_pessoa_evento pe where pe.cod_pessoa = ?) " +
				" and upper(eve.nome) like upper(?)", Evento.class);
		query.setParameter(1, pessoa.getCodPessoa());
		query.setParameter(2, "%"+nome+"%");
		List<Evento> eventos = query.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		return eventos;
	}

	@Override
	public List<Evento> buscarEventosAbertos() {
		TypedQuery<Evento> query = em.createQuery("from Evento eve where privacidade = :priv",Evento.class);
		query.setParameter("priv", Privacidade.Aberto);
		List<Evento> eventos = query.getResultList();
		
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		
		return eventos;
	}

}
