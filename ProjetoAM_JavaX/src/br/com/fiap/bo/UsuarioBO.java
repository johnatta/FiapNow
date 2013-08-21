package br.com.fiap.bo;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.daoimpl.UsuarioDAOImpl;
import br.com.fiap.entity.Usuario;

public class UsuarioBO {
	
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	
	public boolean validaLogin(String email, String senha){
		boolean ret = false;
		
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario;
		usuario = usuarioDAO.buscarEmailESenha(email, senha);
		
		if(usuario != null){
			ret = true;
		}
		
		return ret;
	}

}
