package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Esporte;

public interface EsporteDAO extends DAO<Esporte, Integer> {
	
	List<Esporte> buscarTodosEsportes();
	
}
