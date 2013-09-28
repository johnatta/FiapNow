package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

public class MensagemEventoDAOImpl extends DAOImpl<MensagemEvento, Integer> implements MensagemEventoDAO {

	public MensagemEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	public List<MensagemEvento> buscarMensagensDaPessoa(Pessoa pessoa){
		TypedQuery<MensagemEvento> query = em.createQuery(" from MensagemEvento me where me.pessoa = :pes", MensagemEvento.class);
		query.setParameter("pes", pessoa);
		List<MensagemEvento> mensagens = query.getResultList();
		
		for(MensagemEvento msg : mensagens){
			if(msg.getConfirmacao() == Confirmacao.SIM){
				msg.setIcon("ui-icon-mail-open");
			} else {
				msg.setIcon("ui-icon-mail-closed");
			}
		}
		
		return mensagens;
	}
	
	public List<MensagemEvento> buscarMensagensNaoLidasDaPessoa(Pessoa pessoa){
		TypedQuery<MensagemEvento> query = em.createQuery(" from MensagemEvento me where me.pessoa = :pes and me.confirmacao = :conf", MensagemEvento.class);
		query.setParameter("pes", pessoa);
		query.setParameter("conf", Confirmacao.NAO);
		return query.getResultList();
	}

}