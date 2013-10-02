package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.rc.ComentarioEventoRC;

public interface EventoDAO extends DAO<Evento, Integer>{
	
	public Evento buscarPeloCodigo(int codEvento);
	
	public List<Pessoa> buscarMembrosPorEvento(int codEvento);
	
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento);
	
	public List<ComentarioEventoRC> buscarComentariosPeloEvento(int codEvento);
	
	public List<Pessoa> buscarPessoasForaEvento(int codEvento);
	
	public List<Evento> buscarEventos();
	
	public List<Evento> buscarEventosAbertos();
	
	public List<Evento> buscarEventosDaPessoa(Pessoa pessoa);

	public List<Evento> buscarEventosAbertosPorNome(String nome);

	public List<Evento> buscarMeusEventosPorNome(Pessoa pessoa, String nome);
	
}
