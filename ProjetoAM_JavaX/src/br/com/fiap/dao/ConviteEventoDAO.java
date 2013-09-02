package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.Pessoa;

public interface ConviteEventoDAO extends DAO<ConviteEvento, Integer> {
	
	public List<ConviteEvento> buscarConviteEventoPorPessoa(Pessoa pessoa);

}
