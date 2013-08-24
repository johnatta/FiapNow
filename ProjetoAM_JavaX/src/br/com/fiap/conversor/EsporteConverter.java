package br.com.fiap.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.fiap.entity.Esporte;
import br.com.fiap.mb.GrupoBean;

@FacesConverter(value="esporteConverter")
public class EsporteConverter implements Converter {

	//Executado no momento em que a página é submetida.
	public Object getAsObject(FacesContext fc, UIComponent component, String value) {
//		if (value.equals("") || value == null) {
//			return null;
//		}
//		Esporte esporte =
//				(Esporte)fc.getViewRoot().getViewMap().get("grupoBean");
//		
//		ClienteTO cliente = new ClienteTO();
//		for (ClienteTO cli : pedidoBean.getClientes()) {
//			if(cli.getNmCliente().toLowerCase().equals(value.toLowerCase())) {
//				cliente = cli;
//			}
//		}
//		return cliente;
		
		GrupoBean grupoBean = (GrupoBean)fc.getViewRoot().getViewMap().get("grupoBean");
		
		return grupoBean.getEsporte(); 
	}
	//Executado no momento em que a página é renderizada e a cada retorno de uma requisição.
	public String getAsString(FacesContext fc, UIComponent component, Object value) {

		if (((Esporte) value).getNome() == null || ((Esporte) value).getNome().equals("")) {
			return "";
		} else {
			return ((Esporte) value).getNome();
		}
	}

}
