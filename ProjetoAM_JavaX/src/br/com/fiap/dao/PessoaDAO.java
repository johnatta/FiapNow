package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Pessoa;

public interface PessoaDAO extends DAO<Pessoa, Integer>{
	
	Pessoa buscarInformacoes(int codPessoa);
	
	public List<Pessoa> buscarMembrosPorEvento(int codEvento);
	
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento);
	
}
