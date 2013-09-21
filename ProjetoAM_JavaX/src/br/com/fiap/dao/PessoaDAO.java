package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

public interface PessoaDAO extends DAO<Pessoa, Integer>{
	
	Pessoa buscarInformacoes(int codPessoa);
	List<Pessoa> buscarMembrosDoGrupoRow(int codGrupo);
	Pessoa buscarPorUsuario(Usuario usuario);
	List<Pessoa> buscarTodasPessoas();
}
