package br.com.fiap.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.rc.ComentarioGrupoRC;

public interface GrupoDAO extends DAO<Grupo, Integer> {
	List<Grupo> consultaMeusGrupos(int codPessoa);
	
	List<Grupo> buscarGrupos();
	
	List<Grupo> buscarGruposAbertos();
	
	List<Grupo> buscaGruposDaPessoa(Pessoa pessoa);
	
	List<Pessoa> buscarUsuariosParaAdicionarAoGrupo(int codGrupo);
	
	Grupo buscarInfoGrupo (int codGrupo);
	
	BigDecimal buscarNumeroMembros(int codGrupo);

	List<Grupo> buscarGruposAbertosPorNome(String nome);

	List<Grupo> buscarMeusGruposPorNome(Pessoa pessoa, String nome);
	
	Grupo buscarGrupoCadastrado(int codAdm);
	
	List<Pessoa> buscarMembrosDoGrupo(int codGrupo);
	
	List<ComentarioGrupoRC> buscarComentariosPeloGrupo(int codGrupo);
	
	List<ComentarioGrupoRC> buscarComentariosPorPessoa(int codPessoa);
	
	List<Evento> buscarProximosEventos(int codGrupo);
	
	List<Evento> buscarHistoricoEvento(int codGrupo);
} 
