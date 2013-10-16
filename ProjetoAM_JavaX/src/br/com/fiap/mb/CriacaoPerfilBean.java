package br.com.fiap.mb;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import br.com.fiap.I18N.UtilsNLS;
import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.datamodel.EsporteDataModel;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;
import br.com.fiap.ws.Webservicecep;

@ManagedBean
@SessionScoped
public class CriacaoPerfilBean implements Serializable {

	private Pessoa pessoa;
	private Usuario usuario;
	private Endereco endereco;
	private Date dtNasc;
	private List<Esporte> esportes;
	private Esporte[] esportesSelecionados;
	private EsporteDataModel edm;
	private PessoaDAO pessoaDAO;
	private EsporteDAO esporteDAO;
	private EntityManager em;
	private LoginBean sessao;

	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		pessoaDAO = new PessoaDAOImpl(em);
		esporteDAO = new EsporteDAOImpl(em);

		//Obter a Pessoa da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		sessao = (LoginBean)map.get("loginBean");

		esportes = esporteDAO.buscarTodosEsportes();
		edm = new EsporteDataModel(esportes);

		pessoa = new Pessoa();
		usuario = new Usuario();
		endereco = new Endereco();
		dtNasc = new Date();
		pessoa.setNome(sessao.getNome());
		usuario.setEmail(sessao.getEmail());
		pessoa.setUsuario(usuario);

	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Esporte> getEsportes() {
		return esportes;
	}
	public void setEsportes(List<Esporte> esportes) {
		this.esportes = esportes;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDtNasc() {
		return dtNasc;
	}
	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}
	public Esporte[] getEsportesSelecionados() {
		return esportesSelecionados;
	}
	public void setEsportesSelecionados(Esporte[] esportesSelecionados) {
		this.esportesSelecionados = esportesSelecionados;
	}
	public EsporteDataModel getEdm() {
		return edm;
	}
	public void setEdm(EsporteDataModel edm) {
		this.edm = edm;
	}

	/**
	 * Cadastra a Pessoa conforme as informações inseridas
	 * 
	 * @author Ariel Molina
	 */
	public String cadastrar(){
		
		String retorno = "";
		
		FacesContext fc = FacesContext.getCurrentInstance();

		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "impossibleInsert",
						null, fc.getViewRoot().getLocale());

		FacesMessage fm = new FacesMessage(mensagem);
		
		List<Esporte> esps = new ArrayList<Esporte>();
		for(Esporte esp : esportesSelecionados){
			esps.add(esp);
		}

		pessoa.setEsportes(esps);
		pessoa.setEndereco(endereco);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtNasc);
		pessoa.setDtNasc(cal);
		
		retorno = "home.xhtml";
		
		try{
			pessoa = pessoaDAO.insertEntity(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
			fc.addMessage("", fm);
			retorno = "";
		}

		sessao.setPessoa(pessoa);

		return retorno;

	}

	/**
	 * Buscar o CEP no WebService através do CEP inserido
	 * 
	 * @author Ariel Molina
	 */
	public void buscarCep(){

		FacesContext fc = FacesContext.getCurrentInstance();

		endereco.setPais("Brasil");

		String mensagem =
				UtilsNLS.getMessageResourceString("nls.mensagem", "invalid_cep",
						null, fc.getViewRoot().getLocale());

		FacesMessage fm = new FacesMessage(mensagem);

		if(!endereco.getCep().equals("")){

			try {
				JAXBContext jc = JAXBContext.newInstance(Webservicecep.class);

				Unmarshaller u = jc.createUnmarshaller();
				URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + endereco.getCep() + "&formato=xml");
				Webservicecep cep = (Webservicecep) u.unmarshal(url);

				if(cep.getResultado_txt().contains("não encontrado")){
					fc.addMessage("", fm);
				} else {
					endereco.setEstado(cep.getUf());
					endereco.setCidade(cep.getCidade());
					endereco.setBairro(cep.getBairro());
					endereco.setRua(cep.getTipo_logradouro() + " " + cep.getLogradouro());
				}

			} catch (JAXBException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			fc.addMessage("", fm);
		}

	}
	
	
	/**
	 * Realizado a inserção da imagem do perfil
	 * @param event
	 * @author Ariel Molina
	 */
	public void realizarUpload(FileUploadEvent event) {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			pessoa.setImgPerfil(IOUtils.toByteArray(event.getFile().getInputstream()));
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