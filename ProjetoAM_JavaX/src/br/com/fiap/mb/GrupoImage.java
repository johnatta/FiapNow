package br.com.fiap.mb;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.Grupo;

@ManagedBean
@ApplicationScoped
public class GrupoImage {

	private GrupoDAO grupoDAO = new GrupoDAOImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());
	
	public StreamedContent getImage(){
		 FacesContext context = FacesContext.getCurrentInstance();

	        if (context.getRenderResponse()) {
	            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
	            return new DefaultStreamedContent();
	        }
	        else {
	            // So, browser is requesting the image. Get ID value from actual request param.
	            String id = context.getExternalContext().getRequestParameterMap().get("id");
	            Grupo grupo = grupoDAO.searchByID(Integer.valueOf(id));
	            return new DefaultStreamedContent(new ByteArrayInputStream(grupo.getImgGrupo()));
	        }
	}
}
