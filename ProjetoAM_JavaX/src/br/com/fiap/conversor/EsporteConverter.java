package br.com.fiap.conversor;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.mb.CriacaoGrupoBean;

@FacesConverter(value="esporteConverter")
public class EsporteConverter implements Converter {
	//Executado no momento em que a p�gina � submetida.
	public Object getAsObject(FacesContext fc, UIComponent component, String value) {
		//CriacaoGrupoBean criacaoGrupoBean = (CriacaoGrupoBean)fc.getViewRoot().getViewMap().get("criacaoGrupoBean");
		EsporteDAO esporteDAO = new EsporteDAOImpl(EntityManagerFactorySingleton.getInstance().createEntityManager());
		return esporteDAO.buscarPorNome(value);
		//return criacaoGrupoBean.getEsporte();
	}

	//Executado no momento em que a p�gina � renderizada e a cada retorno de uma requisi��o.
	public String getAsString(FacesContext fc, UIComponent component, Object value) {
		if (((Esporte) value).getNome() == null || ((Esporte) value).getNome().equals("")) {
			return "";
		} else {
			return ((Esporte) value).getNome();
		}
	}

}
