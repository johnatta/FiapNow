package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

public interface PessoaDAO extends DAO<Pessoa, Integer>{
	
	Pessoa buscarInformacoes(Pessoa pessoa);
	List<Pessoa> buscarMembrosDoGrupo(int codGrupo);
	Pessoa buscarPorUsuario(Usuario usuario);
	
}
