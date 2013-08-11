package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.ComentarioEvento;
import br.com.fiap.entity.Evento;

public interface EventoDAO extends DAO<Evento, Integer>{
	
	public Evento buscarPeloCodigo(int codEvento);
	
	public List<ComentarioEvento> buscarComentariosPeloEvento(int codEvento);

}
