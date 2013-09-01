package br.com.fiap.dao;

import br.com.fiap.entity.Usuario;

public interface UsuarioDAO extends DAO<Usuario, Integer>{

	public Usuario buscarEmailESenha(String email, String senha);
	
	public Usuario buscarPorEmail(String email);

}
