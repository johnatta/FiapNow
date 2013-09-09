package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Esporte;
import br.com.fiap.pesquisa.ItemGrafico;

public interface EsporteDAO extends DAO<Esporte, Integer> {
	
	List<Esporte> buscarTodosEsportes();
	
	Esporte buscarPorNome(String nome);
	
	List<ItemGrafico> buscarPopularidade();
	
}
