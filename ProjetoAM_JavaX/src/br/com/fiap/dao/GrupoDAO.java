package br.com.fiap.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

public interface GrupoDAO extends DAO<Grupo, Integer> {
	List<Grupo> consultaMeusGrupos(int codPessoa);
	
	List<Grupo> buscarGrupos();
	
	List<Grupo> buscaGruposDoUsuario(int codPessoa);
	
	List<Pessoa> buscarUsuariosParaAdicionarAoGrupo(int codGrupo);
	
	Grupo buscarInfoGrupo (int codGrupo);
	
	BigDecimal buscarNumeroMembros(int codGrupo);
}
