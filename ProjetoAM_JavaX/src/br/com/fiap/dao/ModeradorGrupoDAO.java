package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;

public interface ModeradorGrupoDAO extends DAO<ModeradorGrupo, Integer> {
	
	public	List <Pessoa> buscarModeradoresDoGrupo(int codGrupo);


}
