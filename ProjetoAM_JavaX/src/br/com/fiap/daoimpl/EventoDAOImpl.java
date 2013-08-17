package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EventoDAO;
import br.com.fiap.entity.ComentarioEvento;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;

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

	//Still doesn't work - Ariel
	@Override
	public List<ComentarioEvento> buscarComentariosPeloEvento(int codEvento) {
		TypedQuery<ComentarioEvento> query = em.createQuery("", ComentarioEvento.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}
	
	//Still doesn't work - Ariel
	@Override
	public List<Pessoa> buscarMembrosPorEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa where codPessoa in " +
				"(select codPessoa from Evento where codEvento = :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}
	
	//Still doesn't work - Ariel
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
	public List<Evento> buscarEventosCadastrados() {
		TypedQuery<Evento> query = em.createQuery("select eve.cod_evento, eve.nome, eve.imagem_evento," +
				"(select count(*) from am_pessoa_evento pe where pe.cod_evento = eve.cod_evento) " +
				"as numMembros from am_evento eve order by numMembros;",Evento.class);
		return query.getResultList();
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
	
}
