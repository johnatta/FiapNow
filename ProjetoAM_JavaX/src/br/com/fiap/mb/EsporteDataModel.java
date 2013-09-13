package br.com.fiap.mb;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import br.com.fiap.entity.Esporte;

public class EsporteDataModel extends ListDataModel<Esporte> implements SelectableDataModel<Esporte> {

	public EsporteDataModel() {	}

	public EsporteDataModel(List<Esporte> esportes) {
		super(esportes);
	}

	@Override
	public Esporte getRowData(String rowKey) {
		
		List<Esporte> esps =  (List<Esporte>) getWrappedData();

		for(Esporte e : esps){
			int cod = Integer.parseInt(rowKey);
			if( e.getCodEsporte() == cod)
			return e;
		}

		return null;	
	}

	@Override
	public Object getRowKey(Esporte e) {
		return e.getCodEsporte();
	}

}
