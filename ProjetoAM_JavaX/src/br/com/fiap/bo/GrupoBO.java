package br.com.fiap.bo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
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
}
