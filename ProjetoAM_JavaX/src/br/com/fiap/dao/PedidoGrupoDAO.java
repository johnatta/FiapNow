package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;

public interface PedidoGrupoDAO extends DAO<PedidoGrupo, Integer> {

	List<PedidoGrupo> buscarPedidoGrupoPraPessoa(Pessoa pessoa);

}
