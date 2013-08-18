package br.com.fiap.dao;

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
	
	public List<Evento> buscarEventosCadastrados();
	
	public Pessoa buscarPessoaEvento(int codPessoa);
	
}
