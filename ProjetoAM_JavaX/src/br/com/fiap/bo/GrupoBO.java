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
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;

public class GrupoBO implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();

	public GrupoBO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Realização a criação do grupo
	 * @return retorno para a página do grupo criado
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
			fm = new FacesMessage("Campo obrigatório não preenchido. Favor preencher.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage("", fm);
			return "";
		}
	}
	/**
	 * Realizado a inserção da imagem do grupo
	 * @param event
	 * @author Graziele Vasconcelos
	 */	
		public void uploadFoto(Grupo grupo, FileUploadEvent event){
			try {
				FacesContext fc = FacesContext.getCurrentInstance();
				grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
				FacesMessage fm = new FacesMessage("Upload Concluído com Sucesso!");
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
		 * Caso não haja moderadores, cria uma instância de Pessoa e popula com mensagem pertinente
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
		 * Caso não haja membros, cria uma instância de Pessoa e popula com mensagem pertinente
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
				FacesMessage fm = new FacesMessage("Pedido não enviado, por favor tente mais tarde.");
				fm.setSeverity(FacesMessage.SEVERITY_ERROR);
				fc.addMessage("messages", fm);
			}
			return "";
		}
}
