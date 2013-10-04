package br.com.fiap.mb;

import java.io.ByteArrayInputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.entity.Evento;

@ManagedBean
@ApplicationScoped
public class EventoImage {

	private EntityManager em;
	private EventoDAO eventoDAO;
	
	@PostConstruct
	public void onInit(){
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		eventoDAO = new EventoDAOImpl(em);
	}
	
	public StreamedContent getImage(){
		 FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getRenderResponse()) {
	            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	            // So, browser is requesting the image. Get ID value from actual request param.
	            String id = context.getExternalContext().getRequestParameterMap().get("id");
	            Evento evento = eventoDAO.searchByID(Integer.valueOf(id));
	            return new DefaultStreamedContent(new ByteArrayInputStream(evento.getImgEvento()));
	        }
	}
}
