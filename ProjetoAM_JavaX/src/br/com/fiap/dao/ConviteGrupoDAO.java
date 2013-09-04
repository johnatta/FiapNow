package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.Pessoa;

public interface ConviteGrupoDAO extends DAO<ConviteGrupo, Integer> {

	List<ConviteGrupo> buscarConviteGrupoPorPessoa(Pessoa pessoa);

}
