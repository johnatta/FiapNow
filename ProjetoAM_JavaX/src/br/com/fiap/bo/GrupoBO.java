package br.com.fiap.bo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;

public class GrupoBO implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public GrupoBO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Realiza��o a cria��o do grupo
	 * @return retorno para a p�gina do grupo criado
	 * @author Graziele Vasconcelos
	 */
	public String criacaoGrupo(Grupo grupo, Esporte[] espSelecionados, Pessoa pessoa) {  
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		PessoaDAO pesDAO = new PessoaDAOImpl(em);
		List<Esporte> listEsporte = new ArrayList<Esporte>();

		if(grupo.getDescricao() != null || grupo.getPrivacidade() != null 
				|| grupo.getNomeGrupo() != null || espSelecionados.length != 0){

			grupo.setAdm(pesDAO.buscarInformacoes(pessoa.getCodPessoa()));

			for (Esporte esporte : espSelecionados) {
				listEsporte.add(esporte);
			}

			grupo.setEsportes(listEsporte);
			grupo = gDAO.insertEntity(grupo);
			grupo.getCodGrupo();

			List<Pessoa> adm = new ArrayList<Pessoa>();
			adm.add(pessoa);
			grupo.setMembros(adm);

			gDAO.update(grupo);
			fm.setSummary("Grupo cadastrado com sucesso");
			fc.addMessage("", fm);
			return "grupo.xhtml";
		}else{
			fm = new FacesMessage("Campo obrigat�rio n�o preenchido. Favor preencher.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("", fm);
			return "";
		}
	}
	/**
	 * Realizado a inser��o da imagem do grupo
	 * @param event
	 * @author Graziele Vasconcelos
	 */	
	public void uploadFoto(Grupo grupo, FileUploadEvent event){
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
			FacesMessage fm = new FacesMessage("Upload Conclu�do com Sucesso!");
			fc.addMessage("messages", fm);
		} catch (IOException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Erro no Processo de Upload!");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
			e.printStackTrace();
		}
	}

	/**
	 * Caso n�o haja moderadores, cria uma inst�ncia de Pessoa e popula com mensagem pertinente
	 * no caso "sem registro"
	 * @author Graziele Vasconcelos
	 */
	public void verificaConteudoModerador(Grupo grupo){
		if(grupo.getModeradores().size() == 0 ){
			FacesContext fc = FacesContext.getCurrentInstance();
			Pessoa pessoaNull = new Pessoa();
			String mensagem =
					UtilsNLS.getMessageResourceString("nls.mensagem", "no_records",	null, fc.getViewRoot().getLocale());
			pessoaNull.setNome("");
			pessoaNull.setApelido(mensagem);
			grupo.getModeradores().add(pessoaNull);
		}
	}

	/**
	 * Caso n�o haja membros, cria uma inst�ncia de Pessoa e popula com mensagem pertinente
	 * no caso "sem registro"
	 * @author Graziele Vasconcelos
	 */
	public void verificaConteudoMembro(Grupo grupo){
		if(grupo.getMembros().size() == 0 ){
			FacesContext fc = FacesContext.getCurrentInstance();
			Pessoa pessoaNull = new Pessoa();
			String mensagem =
					UtilsNLS.getMessageResourceString("nls.mensagem", "no_records",	null, fc.getViewRoot().getLocale());
			pessoaNull.setNome("");
			pessoaNull.setApelido(mensagem);
			grupo.getMembros().add(pessoaNull);
		}
	}

	public void verificaProximosEventos(List<Evento> proximosEventos){
		if(proximosEventos.size() == 0){
			FacesContext fc = FacesContext.getCurrentInstance();
			Evento eventoNull = new Evento();
			String mensagem =
					UtilsNLS.getMessageResourceString("nls.mensagem", "no_records",	null, fc.getViewRoot().getLocale());
			eventoNull.setNome(mensagem);
			eventoNull.setDtEvento(Calendar.getInstance());
			proximosEventos.add(eventoNull);
		}
	}

	public void verificaHistoricoEventos(List<Evento> historicoEventos){
		FacesContext fc = FacesContext.getCurrentInstance();
		if(historicoEventos.size() <= 0){
			Evento eventoNull = new Evento();
			String mensagem =
					UtilsNLS.getMessageResourceString("nls.mensagem", "no_records",	null, fc.getViewRoot().getLocale());
			eventoNull.setNome(mensagem);
			eventoNull.setDtEvento(Calendar.getInstance());
			historicoEventos.add(eventoNull);
		}
	}

	public String participarDoGrupo(Grupo grupo, Pessoa pessoa){
		PedidoGrupoDAO pedidoDAO = new PedidoGrupoDAOImpl(em);
		PedidoGrupo pedidoGrupo = new PedidoGrupo();
		try {
			pedidoGrupo.setDescricao("Eu, "+pessoa.getNome()+", desejo participar do seu grupo");
			pedidoGrupo.setPessoa(pessoa);
			pedidoGrupo.setGrupo(grupo);
			pedidoDAO.insert(pedidoGrupo);
			FacesMessage fm = new FacesMessage("Pedido enviado!");
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("messages", fm);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Pedido n�o enviado, por favor tente mais tarde.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
		}
		return "";
	}

	public String excluirGrupo(Grupo grupo){
		GrupoDAO gruDAO = new GrupoDAOImpl(em);
		try {
			gruDAO.removeById(grupo.getCodGrupo());
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Grupo exclu�do com sucesso.");
			fc.addMessage("", fm);
			return "grupos.xhtml";

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("O grupo n�o exclu�do, por favor tente mais tarde.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
			return "";
		}
	}

	public Grupo enviarComentarioGrupo(Grupo grupo, Pessoa pessoa, ComentarioGrupo comentarioGrupo){
		ComentarioGrupoDAO comentarioGrupoDAO = new ComentarioGrupoDAOImpl(em);
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		GrupoDAO gruDAO = new GrupoDAOImpl(em);
		try {
			comentarioGrupo.setGrupo(grupo);
			comentarioGrupo.setPessoa(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
			comentarioGrupo.setDataHora(Calendar.getInstance());
			comentarioGrupoDAO.insert(comentarioGrupo);
			comentarioGrupoDAO.update(comentarioGrupo);
			comentarioGrupo = new ComentarioGrupo();
			gruDAO.update(grupo);
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Coment�rio enviado com sucesso.");
			fc.addMessage("", fm);
			return grupo;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Coment�rio n�o enviado, por favor tente mais tarde.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
			return grupo;
		}
	}

	public void excluirComentarioGrupo(Grupo grupo, Pessoa pessoa, int codComentario){
		ComentarioGrupo comentarioGrupo = new ComentarioGrupo();
		ComentarioGrupoDAO comentarioGrupoDAO = new ComentarioGrupoDAOImpl(em);
		GrupoDAO gruDAO = new GrupoDAOImpl(em);
		try {
			comentarioGrupo = comentarioGrupoDAO.searchByID(codComentario);
			grupo.getComentarios().remove(comentarioGrupo);
			comentarioGrupoDAO.remove(comentarioGrupo);
			comentarioGrupo = new ComentarioGrupo();
			gruDAO.update(grupo);
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Coment�rio enviado com sucesso.");
			fc.addMessage("", fm);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Coment�rio n�o enviado, por favor tente mais tarde.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);
		}
	}

	public Grupo excluirMembroGrupo(Grupo grupo, Pessoa[] membrosSelecionadosExc){
		GrupoDAO gruDAO = new GrupoDAOImpl(em);
		for (Pessoa membro : membrosSelecionadosExc){
			grupo.getMembros().remove(membro);
			gruDAO.update(grupo);
		}
		return grupo; 
	}

	public void enviarMsgParaMembrosGrupo(List<Pessoa> membrosGrp, Grupo grupo, MensagemGrupo mensagem){
		MensagemGrupoDAO msgDAO = new MensagemGrupoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		try {
			String descricao = mensagem.getDescricao();
			String titulo = mensagem.getTitulo();
			for (Pessoa membro : membrosGrp){
				mensagem.setPessoa(membro);
				mensagem.setGrupo(grupo);
				mensagem.setConfirmacao(Confirmacao.NAO);
				mensagem.setDescricao(descricao);
				mensagem.setTitulo(titulo);
				msgDAO.insert(mensagem);
				//linha inserida recente, testar pois no momento que foi alterado o banco estava sendo reiniciado
				pessoaDAO.update(membro);
				mensagem = new MensagemGrupo();
			}		
			
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage();
			fm.setSummary("Mensagem enviada com sucesso.");
			fc.addMessage("", fm);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage fm = new FacesMessage("Mensagem n�o enviada, por favor tente mais tarde.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("messages", fm);		
		}
	}
}
