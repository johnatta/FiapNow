package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Grupo;

public interface GrupoDAO extends DAO<Grupo, Integer> {
	List<Grupo> buscaGruposPorUsuario(int codPessoa);
	
	Grupo buscaInfoBasicas(int codGrupo);
	
	List<Grupo> buscaGruposDoUsuario(int codPessoa);
}
