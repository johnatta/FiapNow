package br.com.fiap.mb;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.ModeradorEvento;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class EnvioMensagemBean implements Serializable {

	private EntityManager em;
	private String titulo;
	private String descricao;
	
	private boolean enviaMsgGrupo;
	private Grupo grupo;
	private GrupoDAO grupoDAO;
	private MensagemGrupo msgGrupo;
	private MensagemGrupoDAO msgGrupoDAO;
	
	private boolean enviaMsgEvento;
	private Evento evento;
	private EventoDAO eventoDAO;
	private MensagemEvento msgEvento;
	private MensagemEventoDAO msgEventoDAO;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		//Obter a Pessoa da sess�o
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> map = context.getExternalContext().getSessionMap();
		
		if(enviaMsgGrupo){
			GrupoBean grupoBean = (GrupoBean)map.get("grupoBean");
			grupo = grupoBean.getGrupo();
			grupoDAO = new GrupoDAOImpl(em);
			msgGrupoDAO = new MensagemGrupoDAOImpl(em);
		} else if (enviaMsgEvento){
			//EventoBean eventoBean = (EventoBean)map.get("eventoBean");
			//evento = eventoBean.getEvento();
			eventoDAO = new EventoDAOImpl(em);
			msgEventoDAO = new MensagemEventoDAOImpl(em);
		}
		
	}

	public boolean isEnviaMsgGrupo() {
		return enviaMsgGrupo;
	}
	public void setEnviaMsgGrupo(boolean enviaMsgGrupo) {
		this.enviaMsgGrupo = enviaMsgGrupo;
	}
	public boolean isEnviaMsgEvento() {
		return enviaMsgEvento;
	}
	public void setEnviaMsgEvento(boolean enviaMsgEvento) {
		this.enviaMsgEvento = enviaMsgEvento;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public MensagemGrupo getMsgGrupo() {
		return msgGrupo;
	}
	public void setMsgGrupo(MensagemGrupo msgGrupo) {
		this.msgGrupo = msgGrupo;
	}
	public MensagemEvento getMsgEvento() {
		return msgEvento;
	}
	public void setMsgEvento(MensagemEvento msgEvento) {
		this.msgEvento = msgEvento;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	
	public void enviaMsg(){
		if(enviaMsgGrupo){
			
			msgGrupo = new MensagemGrupo();
			msgGrupo.setTitulo(titulo);
			msgGrupo.setDescricao(descricao);
			
			for(Pessoa pessoa : grupoDAO.buscarMembrosDoGrupo(grupo.getCodGrupo())){
				msgGrupo.setPessoa(pessoa);
				msgGrupoDAO.insert(msgGrupo);
			}
			
		} else if (enviaMsgEvento){
			
			msgEvento = new MensagemEvento();
			msgEvento.setTitulo(titulo);
			msgEvento.setDescricao(descricao);
			
			for(Pessoa pessoa : eventoDAO.buscarMembrosPorEvento(evento.getCodEvento())){
				msgEvento.setPessoa(pessoa);
				msgEventoDAO.insert(msgEvento);
			}
			
		}
	}
	
}
