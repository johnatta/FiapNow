package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

public class MensagemGrupoDAOImpl extends DAOImpl<MensagemGrupo, Integer> implements MensagemGrupoDAO{

	public MensagemGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	public List<MensagemGrupo> buscarMensagensDaPessoa(Pessoa pessoa){
		TypedQuery<MensagemGrupo> query = em.createQuery(" from MensagemGrupo me where me.pessoa = :pes order by me.codMensagem desc", MensagemGrupo.class);
		query.setParameter("pes", pessoa);
		List<MensagemGrupo> mensagens = query.getResultList();
		
		for(MensagemGrupo msg : mensagens){
			if(msg.getConfirmacao() == Confirmacao.SIM){
				msg.setIcon("ui-icon-mail-open");
			} else {
				msg.setIcon("ui-icon-mail-closed");
			}
		}
		
		return mensagens;
	}
	
	public List<MensagemGrupo> buscarMensagensNaoLidasDaPessoa(Pessoa pessoa){
		TypedQuery<MensagemGrupo> query = em.createQuery(" from MensagemGrupo me where me.pessoa = :pes and me.confirmacao = :conf", MensagemGrupo.class);
		query.setParameter("pes", pessoa);
		query.setParameter("conf", Confirmacao.NAO);
		return query.getResultList();
	}

}
