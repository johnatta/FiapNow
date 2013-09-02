package br.com.fiap.mb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;

@ManagedBean
@ViewScoped
public class GrupoBean implements Serializable {
	EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
	private static final long serialVersionUID = 1L;
	private Esporte esporte;
	private Grupo grupo;
	private List<Privacidade> privs;
	private List<Esporte> espSelecionados; 
	private StreamedContent foto;
	private DualListModel<Esporte> listaPicker;
	private int codAdm;
	private GrupoDAO gDAO;
	private Grupo pgGrupo;
	private BigDecimal numMembros;
	private ComentarioGrupo cmtGrupo;
	private int codGrupo;
	
	@PostConstruct
	public void init(){
		grupo = new Grupo();
		esporte = new Esporte();
		espSelecionados = new ArrayList<Esporte>();
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
		setListaPicker(new DualListModel<Esporte>(listaEsporte(), espSelecionados));
	}
	
	@PostConstruct
	public void meusGrupos(){
		gDAO = new GrupoDAOImpl(em);
		pgGrupo = new Grupo();
		System.out.println("antes " + codGrupo);
		System.out.println("depois " + codGrupo);
		pgGrupo = gDAO.searchByID(codGrupo);
		numMembros = gDAO.buscarNumeroMembros(pgGrupo.getCodGrupo());
		//cmtGrupo = new ComentarioGrupo();
		//ComentarioGrupoDAO cmtGrupoDAO = new ComentarioGrupoDAOImpl(em);
		//gDAO.
		//
	}
	
	@PostConstruct
	public List<Esporte> listaEsporte(){
		List<Esporte> esportes = new ArrayList<Esporte>();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		esportes = espDAO.buscarTodosEsportes();
		return esportes;
	}
	
	public String btnCriarGrupo(){
		String retorno = "";
		GruposBean gpsMB = new GruposBean();
		grupo.setAdm(gpsMB.getPessoa());
		grupo.setEsportes(espSelecionados);		
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		
		try{
			gDAO.insert(getGrupo());
			fm.setSummary("Cadastro Realizado com Sucesso");
			fc.addMessage("", fm);
			retorno = "grupo";
		} catch(Exception e){
			e.printStackTrace();
			fm.setSummary("Erro na Realização do Cadastro");
			fc.addMessage("", fm);
		}
		return retorno;
	}
	
	public void realizarUpload(FileUploadEvent event) {
		String arq = event.getFile().getFileName();
		//System.out.println("Nome do arquivo:" + arq);
		//System.out.println("Tamanho do arquivo:" + event.getFile().getSize());
		
		try {
			grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public List<Privacidade> getPrivs() {
		return privs;
	}
	public void setPrivs(List<Privacidade> privs) {
		this.privs = privs;
	}
	public StreamedContent getFoto() {
		FacesContext context = FacesContext.getCurrentInstance();
		DefaultStreamedContent content = new DefaultStreamedContent();
		content.setContentType("image/jpg");
		try{
			if (context.getRenderResponse() || grupo.getImgGrupo() == null){
				content.setStream(context.getExternalContext().getResourceAsStream("/resources/img/semFoto.jpg"));
			}else{
				content.setStream(new ByteArrayInputStream(grupo.getImgGrupo()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public List<Esporte> getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(List<Esporte> espSelecionados) {
		this.espSelecionados = espSelecionados;
	}

	public DualListModel<Esporte> getListaPicker() {
		return listaPicker;
	}

	public void setListaPicker(DualListModel<Esporte> listaPicker) {
		this.listaPicker = listaPicker;
	}

	public int getCodAdm() {
		return codAdm;
	}

	public void setCodAdm(int codAdm) {
		this.codAdm = codAdm;
	}

	public GrupoDAO getgDAO() {
		return gDAO;
	}

	public void setgDAO(GrupoDAO gDAO) {
		this.gDAO = gDAO;
	}

	public Grupo getPgGrupo() {
		return pgGrupo;
	}

	public void setPgGrupo(Grupo pgGrupo) {
		this.pgGrupo = pgGrupo;
	}

	public BigDecimal getNumMembros() {
		return numMembros;
	}

	public void setNumMembros(BigDecimal numMembros) {
		this.numMembros = numMembros;
	}

	public ComentarioGrupo getCmtGrupo() {
		return cmtGrupo;
	}

	public void setCmtGrupo(ComentarioGrupo cmtGrupo) {
		this.cmtGrupo = cmtGrupo;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}
	
	
	
}
