package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;

public class ModeradorGrupoDAOImpl extends DAOImpl<ModeradorGrupo, Integer> implements ModeradorGrupoDAO{

	public ModeradorGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

		public List<Pessoa> buscarModeradoresDoGrupo(int codGrupo) {
			@SuppressWarnings("unchecked")
			TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery ("SELECT * FROM AM_PESSOA  WHERE cod_pessoa IN (SELECT cod_pessoa  FROM AM_MODERADOR_GRUPO WHERE cod_moderador_grupo =  :codGrupo)", Pessoa.class);
			p.setParameter("codGrupo", codGrupo);
			return p.getResultList();
			
		}

}
