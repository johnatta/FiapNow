package br.com.fiap.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.MensagemEvento;

public class MensagemEventoDataModel extends ListDataModel<MensagemEvento> implements SelectableDataModel<MensagemEvento> {


	public MensagemEventoDataModel(){ }

	public MensagemEventoDataModel(List<MensagemEvento> data){
		super(data);
	}


	@Override  
	public MensagemEvento getRowData(String rowKey) {  
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

		List<MensagemEvento> msgsEvento = (List<MensagemEvento>) getWrappedData();  

		for(MensagemEvento mg : msgsEvento) {  
			if(mg.getCodMensagem() == Integer.parseInt(rowKey)){
				return mg;
			}
		}  

		return null;  
	}  

	@Override  
	public Object getRowKey(MensagemEvento mensagemEvento) {  
		return mensagemEvento.getCodMensagem();  
	}	


}
