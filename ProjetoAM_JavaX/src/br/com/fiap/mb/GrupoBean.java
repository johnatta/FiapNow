package br.com.fiap.mb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
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
	private DualListModel<Esporte> listaPicker;
	private StreamedContent foto;
	private BigDecimal numMembros;
	private Esporte esporte;
	private List<Privacidade> privs;
	private List<Esporte> espSelecionados;
	private List<Pessoa> modsGp;
	private List<Pessoa> membrosGrp;
	private List<Pessoa> membrosGrpRow;
	//Utilizado para o f:setPropertyActionListener passar o codigo do grupo criado por ele
	private int codGrupo;
	private Grupo grupo;
	private Pessoa pessoa;
	private Grupo pgGrupo;
	private ComentarioGrupo cmtGrupo;
	//private ModeradorGrupo moderador;
	//private Pessoa membro;
	
	@PostConstruct
	public void init(){
		codGrupo = 0;
		grupo = new Grupo();
		esporte = new Esporte();
		espSelecionados = new ArrayList<Esporte>();
		this.privs = Arrays.asList(grupo.getPrivacidade().values());
		setListaPicker(new DualListModel<Esporte>(listaEsporte(), espSelecionados));
		
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		LoginBean sessao = (LoginBean)map.get("loginBean");
		pessoa = sessao.getPessoa();
		System.out.println("AUQI");
	}
	
	@PostConstruct
	public void meusGrupos(){
		ModeradorGrupoDAO modGpDAO = new ModeradorGrupoDAOImpl(em);
		//PessoaDAO pDAO = new PessoaDAOImpl(em);
		membrosGrpRow = new ArrayList<Pessoa>(); 
		//moderador = new ModeradorGrupo();
		modsGp = new ArrayList<Pessoa>();
		
		//membro = new Pessoa();
		GrupoDAO gruDAO = new GrupoDAOImpl(em);
		pgGrupo = new Grupo();
		
		pgGrupo = gruDAO.searchByID(codGrupo);
		numMembros = gruDAO.buscarNumeroMembros(pgGrupo.getCodGrupo());
		modsGp = modGpDAO.buscarModeradoresDoGrupo(pgGrupo.getCodGrupo());
		//membrosGrp = pDAO.buscarMembrosDoGrupo(getCodGrupo());
		membrosGrpRow = modGpDAO.buscarModeradoresDoGrupoRowNum(pgGrupo.getCodGrupo());
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
		GrupoDAO gDAO = new GrupoDAOImpl(em);
		//FacesContext context = FacesContext.getCurrentInstance();
		
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		
		grupo.setAdm(pDAO.buscarInformacoes(pessoa.getCodPessoa()));
		grupo.setEsportes(espSelecionados);		
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage();
		
		try{
			
			gDAO.insert(grupo);
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
//		String arq = event.getFile().getFileName();
//		System.out.println("Nome do arquivo:" + arq);
//		System.out.println("Tamanho do arquivo:" + event.getFile().getSize());
		
		
		try {
//			File file = new File("c:\\tmp\\", arq);
//			FileOutputStream fos = new FileOutputStream(file);
//			fos.write(event.getFile().getContents());
//			fos.close();
//			
//			FacesContext fc = FacesContext.getCurrentInstance();
//			
//			FacesMessage fm = new FacesMessage("Upload Concluído com Sucesso!");
//			
//			fc.addMessage("messages", fm);
			grupo.setImgGrupo(IOUtils.toByteArray(event.getFile().getInputstream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public DualListModel<Esporte> getListaPicker() {
		return listaPicker;
	}

	public void setListaPicker(DualListModel<Esporte> listaPicker) {
		this.listaPicker = listaPicker;
	}

	public Esporte getEsporte() {
		return esporte;
	}

	public void setEsporte(Esporte esporte) {
		this.esporte = esporte;
	}

	public List<Privacidade> getPrivs() {
		return privs;
	}

	public void setPrivs(List<Privacidade> privs) {
		this.privs = privs;
	}

	public List<Esporte> getEspSelecionados() {
		return espSelecionados;
	}

	public void setEspSelecionados(List<Esporte> espSelecionados) {
		this.espSelecionados = espSelecionados;
	}

	public List<Pessoa> getModsGp() {
		return modsGp;
	}

	public void setModsGp(List<Pessoa> modsGp) {
		this.modsGp = modsGp;
	}

	public List<Pessoa> getMembrosGrp() {
		return membrosGrp;
	}

	public void setMembrosGrp(List<Pessoa> membrosGrp) {
		this.membrosGrp = membrosGrp;
	}

	public List<Pessoa> getMembrosGrpRow() {
		return membrosGrpRow;
	}

	public void setMembrosGrpRow(List<Pessoa> membrosGrpRow) {
		this.membrosGrpRow = membrosGrpRow;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Grupo getPgGrupo() {
		return pgGrupo;
	}

	public void setPgGrupo(Grupo pgGrupo) {
		this.pgGrupo = pgGrupo;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public int getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}
	
}
