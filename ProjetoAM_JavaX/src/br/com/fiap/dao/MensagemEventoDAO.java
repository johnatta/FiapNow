package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.Pessoa;

public interface MensagemEventoDAO extends DAO<MensagemEvento, Integer> {
	
	public List<MensagemEvento> buscarMensagensLidasDaPessoa(Pessoa pessoa);
	
	public List<MensagemEvento> buscarMensagensNaoLidasDaPessoa(Pessoa pessoa);

}
