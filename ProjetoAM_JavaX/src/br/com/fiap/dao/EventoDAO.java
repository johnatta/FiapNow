package br.com.fiap.dao;

import java.util.Calendar;
import java.util.List;

import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.rc.ComentarioEventoRC;

public interface EventoDAO extends DAO<Evento, Integer>{
	
	public Evento buscarPeloCodigo(int codEvento);
	
	public List<Pessoa> buscarMembrosPorEvento(int codEvento);
	
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento);
	
	public List<ComentarioEventoRC> buscarComentariosPeloEvento(int codEvento);
	
	public List<Esporte> buscarCategoria();
	
	public Evento adcionarPessoaEvento(int codEvento);
	
	public List<Evento> buscarEventos();
	
	public Pessoa buscarPessoaEvento(int codPessoa);
	
	public List <Evento> buscarProximosEventos(int codGrupo);
	
	public List <Evento> historicoDeEventos (int codGrupo);
	
	public List<Evento> buscarEventosDaPessoa(Pessoa pessoa);
	
}
