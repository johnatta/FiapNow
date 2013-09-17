package br.com.fiap.models;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.fiap.entity.MensagemGrupo;

public class MensagemGrupoModel extends ListDataModel<MensagemGrupo> implements SelectableDataModel<MensagemGrupo> {


	public MensagemGrupoModel(){ }

	public MensagemGrupoModel(List<MensagemGrupo> data){
		super(data);
	}


	@Override  
	public MensagemGrupo getRowData(String rowKey) {  
		//In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

		List<MensagemGrupo> msgsGrupo = (List<MensagemGrupo>) getWrappedData();  

		for(MensagemGrupo mg : msgsGrupo) {  
			if(mg.getCodMensagem() == Integer.parseInt(rowKey));  
				return mg;
		}  

		return null;  
	}  

	@Override  
	public Object getRowKey(MensagemGrupo mensagemGrupo) {  
		return mensagemGrupo.getCodMensagem();  
	}	


}
