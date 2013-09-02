package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.Pessoa;

public interface PedidoEventoDAO extends DAO<PedidoEvento, Integer> {
	
	public List<PedidoEvento> buscarPedidosDeEventoPraPessoa(Pessoa pessoa);

}
