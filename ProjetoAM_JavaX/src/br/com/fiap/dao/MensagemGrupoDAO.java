package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

public interface MensagemGrupoDAO extends DAO<MensagemGrupo, Integer> {
	
	public List<MensagemGrupo> buscarMensagensDaPessoa(Pessoa pessoa);
	
	public List<MensagemGrupo> buscarMensagensNaoLidasDaPessoa(Pessoa pessoa);

}
